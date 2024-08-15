package epsilongtmyon;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UncheckedIOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PreventDoubleLaunchingHelper {

	private static Logger logger = Logger.getLogger(PreventDoubleLaunchingHelper.class.getName());

	public <T> T execute(String lockFileName, Callable<T> task) throws Exception {

		Path lockFilePath = Path.of(lockFileName).toAbsolutePath();

		boolean hasLock = false;
		try (RandomAccessFile f = new RandomAccessFile(lockFilePath.toFile(), "rw");
				FileChannel channel = f.getChannel()) {

			FileLock lock = channel.tryLock();
			if (lock == null) {
				logger.info("既に起動されています。");
				return null;
			}
			
			hasLock = true;
			logger.info("タスクを実行します。");
			T result = task.call();
			logger.info("タスクが完了しました。");
			lock.release();
			return result;
		} catch (IOException e) {
			logger.log(Level.SEVERE, "ロックに失敗", e);
			throw new UncheckedIOException(e);
		} finally {
			if (hasLock && Files.exists(lockFilePath)) {
				try {
					Files.delete(lockFilePath);
				} catch (IOException e) {
					logger.log(Level.SEVERE, "ロックファイルの削除に失敗", e);
					throw new UncheckedIOException(e);
				}
			}
		}
	}
}
