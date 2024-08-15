package epsilongtmyon;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UncheckedIOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class PreventDoubleLaunchingMain {

	public static void main(String[] args) throws Exception {

		var main = new PreventDoubleLaunchingMain();
		main.start4();
	}

	// このやり方だと.lockファイルを外から消せてしまう。
	// ファイルが空いてないから？？
	private void start1() {
		Path lockFilePath = Path.of(".lock").toAbsolutePath();
		System.out.println(lockFilePath);

		try (FileChannel channel = FileChannel.open(lockFilePath, StandardOpenOption.CREATE, StandardOpenOption.READ,
				StandardOpenOption.WRITE);) {
			System.out.println("ロック取得開始");
			FileLock lock = channel.tryLock();
			if (lock == null) {
				System.out.println("ロック失敗");
				return;
			}

			System.out.println("ロック取得完了");
			// 待機
			System.in.read();
			System.out.println("チャネル閉じる");

		} catch (IOException e) {
			System.out.println("ロック失敗");
		}

	}

	// RandomAccessFileなら.lockファイルの削除も防げる
	private void start2() {
		Path lockFilePath = Path.of(".lock").toAbsolutePath();

		boolean hasLock = false;
		try (RandomAccessFile f = new RandomAccessFile(lockFilePath.toFile(), "rw");
				FileChannel channel = f.getChannel()) {

			FileLock lock = channel.tryLock();
			if (lock == null) {
				System.out.println("ロック失敗");
				return;
			}
			hasLock = true;
			System.out.println("ロック取得完了");
			System.in.read();
			System.out.println("チャネル閉じる");
		} catch (IOException e) {
			System.out.println("ロック失敗");
		} finally {
			if (hasLock && Files.exists(lockFilePath)) {
				try {
					Files.delete(lockFilePath);
				} catch (IOException e) {
					throw new UncheckedIOException(e);
				}
			}
		}

	}

	// ラムダ式内はロックした状態
	private void start3() throws Exception {
		PreventDoubleLaunchingHelper p = new PreventDoubleLaunchingHelper();
		p.execute(".lock", () -> {
			System.in.read();
			return null;
		});
	}

	// 明示的にロックを取得させる。
	private void start4() throws Exception {
		try (ApplicationLock tryLock = ApplicationLock.tryLock(".lock")) {
			if (tryLock == null) {
				System.out.println("ロック失敗");
				return;
			}

			System.in.read();
		}
	}
}
