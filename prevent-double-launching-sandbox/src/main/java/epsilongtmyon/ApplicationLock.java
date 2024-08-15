package epsilongtmyon;

import java.io.Closeable;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.UncheckedIOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * アプリケーションの重複起動防止に使用する。
 */
public class ApplicationLock implements Closeable {

	/** ロガー */
	private static Logger logger = Logger.getLogger(ApplicationLock.class.getName());

	/** ロックファイルのパス */
	private final Path lockFilePath;

	/** ロックファイル */
	private final RandomAccessFile lockFile;

	/** ロックファイルのチャネル */
	private final FileChannel lockFileChannel;

	/** ロックファイルのロック */
	private final FileLock fileLock;

	/**
	 * コンストラクタ
	 * @param lockFilePath ロックファイルのパス
	 * @param lockFile ロックファイル
	 * @param lockFileChannel ロックファイルのチャネル
	 * @param fileLock ロックファイルのロック
	 */
	public ApplicationLock(Path lockFilePath, RandomAccessFile lockFile, FileChannel lockFileChannel,
			FileLock fileLock) {
		super();
		this.lockFilePath = lockFilePath;
		this.lockFile = lockFile;
		this.lockFileChannel = lockFileChannel;
		this.fileLock = fileLock;
	}

	/**
	 * ロックを取得します。
	 * 
	 * @param lockFileName ロックファイル名
	 * @return ロック ロックとれなければnull
	 */
	public static ApplicationLock tryLock(String lockFileName) {
		Path lockFilePath = Path.of(lockFileName).toAbsolutePath();

		RandomAccessFile lockFile = null;
		FileChannel channel = null;
		FileLock lock = null;

		try {
			logger.info("ロックの取得を行います。:" + lockFilePath);
			
			try {
				lockFile = new RandomAccessFile(lockFilePath.toFile(), "rw");
				channel = lockFile.getChannel();
				lock = channel.tryLock();
				
				if (lock == null) {
					logger.warning("ロックが取得できませんでした。既に起動されています。");
					return null;
				}
				
				// ロックファイルにPIDを書き込んでおき、ロックを持っているのがどのプロセスなのか特定できるようにしておく
				long pid = ProcessHandle.current().pid();
				lockFile.writeChars(Long.toString(pid));

				logger.info("ロックの取得に成功しました。");
				return new ApplicationLock(lockFilePath, lockFile, channel, lock);
			} catch (IOException ex) {
				logger.log(Level.SEVERE, "ロックを試みましたが例外が発生しました。", ex);

				try {
					if (channel != null && channel.isOpen()) {
						channel.close();
					}
				} finally {
					if (lockFile != null) {
						lockFile.close();
					}
				}
				throw ex;
			}
			
		} catch (IOException ex) {
			throw new UncheckedIOException(ex);
		}

	}

	@Override
	public void close() throws IOException {

		try {
			fileLock.release();
		} finally {
			try {
				if (lockFileChannel.isOpen()) {
					lockFileChannel.close();
				}
			} finally {
				try {
					lockFile.close();
				} finally {
					Files.delete(lockFilePath);
				}

			}
		}
	}

}
