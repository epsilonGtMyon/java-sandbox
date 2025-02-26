package epsilongtmyon.app.sandbox01;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import epsilongtmyon.app.common.misc.Sleeper;
import epsilongtmyon.app.common.misc.ThreadInfoHelper;

public class Sandbox01Main {

	private static final Logger logger = Logger.getLogger(Sandbox01Main.class.getName());

	public static void main(String[] args) throws Exception {
		// eclipseから起動用
		System.setProperty("java.util.logging.config.file", "src/main/resources/logging.properties");
		LogManager.getLogManager().readConfiguration();

		var main = new Sandbox01Main();
		main.handle01();
	}

	private void supplyAsync() {

		// 非同期で実行、またない
		CompletableFuture<Sandbox01Task> f1 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask("1", 4000L));

		// すぐにここに来る
		Sleeper.sleepMills(5000L);
	}

	private void get() throws InterruptedException, ExecutionException {

		CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask("1", 4000L))
				// そして結果の変換を同期的に
				.thenApply(t -> t.format())

		;

		ThreadInfoHelper.withThreadName("待機");
		var result = f1.get();
		ThreadInfoHelper.withThreadName("取得:" + result);
	}

	private void join() {
		CompletableFuture<Sandbox01Task> f1 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask("1", 2000L));
		CompletableFuture<Sandbox01Task> f2 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask("2", 3000L));

		logger.info("待機1");
		f1.join();
		logger.info("待機2");
		f2.join();
		logger.info("終了");

	}

	private void thenAcceptAsync() {

		CompletableFuture<?> f1 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask("1", 2000L))
				// そして結果の変換を非同期でする。(別スレッドになったり)
				.thenAcceptAsync(t -> Sandbox01Task.doTask("2", 2000L));

		CompletableFuture<?> f2 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask("1", 2000L))

				.thenAcceptAsync(t -> Sandbox01Task.doTask("2", 2000L));

		logger.info("待機1");
		f1.join();
		logger.info("待機2");
		f2.join();
		logger.info("終了");

	}

	private void allOf() {

		CompletableFuture<Sandbox01Task> f1 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask("1", 2000L));
		CompletableFuture<Sandbox01Task> f2 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask("2", 4000L));

		// 結果はVoidになる
		CompletableFuture<Void> cf = CompletableFuture.allOf(f1, f2);

		logger.info("待機");
		cf.join();
		logger.info("終了");
	}

	private void anyOf() {

		CompletableFuture<Sandbox01Task> f1 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask("1", 2000L));
		CompletableFuture<Sandbox01Task> f2 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask("2", 4000L));

		// 結果はObject
		CompletableFuture<Object> cf = CompletableFuture.anyOf(f1, f2);

		logger.info("待機");
		Object r = cf.join();
		logger.info("どっちかおわった:" + r);

		Sleeper.sleepMills(5000L);
	}

	private void get_join() {

		CompletableFuture<?> f1 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask("1", 2000L))

				.thenAccept(t -> {
					throw new RuntimeException("wao!");
				});

		try {

			// getはチェック例外, タイムアウトを指定する事もできる。
			//			f1.get();

			// joinは非チェック例外
			f1.join();

		} catch (Exception e) {
			// getなら：java.util.concurrent.ExecutionException: java.lang.RuntimeException: wao!
			// joinなら：java.util.concurrent.CompletionException: java.lang.RuntimeException: wao!
			System.out.println(e);
		}

	}

	private void whenComplete01() {
		String id = "exceptionId";
//		String id = "1";
		CompletableFuture<?> f1 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask(id, 2000L))

				.thenApply(t -> {
					if (t.getId().equals("exceptionId")) {
						throw new RuntimeException("task!");
					}
					return t.format();
				})

				// 前のタスクで例外が発生するとここは実行されない
				.thenApply(t -> {
					System.err.println(t);
					return t;
				})

				// ここは成功しようが失敗しようが実行
				.whenComplete((a, ex) -> {
					// exがCompletionExceptionにラップされている。
					
					System.err.println("---1");
					System.err.println(a);
					System.err.println(ex);
					// すでに例外がある時にここで例外をなげてもsupressedになる
					// 例外がまだおきてなかったらこの例外が使われる。
					throw new RuntimeException("aa");
				})

				// ここは成功しようが失敗しようが実行
				.whenComplete((a, ex) -> {
					System.err.println("---2");
					System.err.println(a);
					System.err.println(ex);
				});

		logger.info("待機");
		try {
			// whenCompleteで例外なげてなくても、ここで例外が発生する
			Object join = f1.join();
			logger.info("終了" + join);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "例外", e);
		}
	}
	

	private void handle01() {
		String id = "exceptionId";
//		String id = "1";
		CompletableFuture<?> f1 = CompletableFuture.supplyAsync(() -> Sandbox01Task.doTask(id, 2000L))

				.thenApply(t -> {
					if (t.getId().equals("exceptionId")) {
						throw new RuntimeException("task!");
					}
					return t.format();
				})
				
				.thenApply(t -> "--" + t)

				// ここは成功しようが失敗しようが実行
				.handle((a, ex) -> {
					// exがCompletionExceptionにラップされている。
					
					System.err.println("---1");
					System.err.println(a);
					System.err.println(ex);
//					if(ex != null) {
//						throw new RuntimeException("aaa", ex);
//					}
					// whenCompleteと違って戻り値を返せる
					// もし先行で例外が発生していても、joinの時に例外は発生しない。
					return "[" + a + "]";
				})

//				// ここは成功しようが失敗しようが実行
//				.handle((a, ex) -> {
//					System.err.println("---2");
//					System.err.println(a);
//					System.err.println(ex);
//					return "[" + a + "]";
//				})
				
				;
		

		logger.info("待機");
		try {
			// whenCompleteで例外なげてなくても、ここで例外が発生する
			Object join = f1.join();
			logger.info("終了" + join);
		} catch (Exception e) {
			logger.log(Level.SEVERE, "例外", e);
		}
		
		
	}
}
