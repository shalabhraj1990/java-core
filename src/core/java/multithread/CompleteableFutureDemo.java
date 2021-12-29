package core.java.multithread;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

public class CompleteableFutureDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 1) runAsync : for runnable task which do not return anything
//		CompletableFuture<Void> cf = CompletableFuture.runAsync(() -> {
//			System.out.println("task run by new thread " + Thread.currentThread().getName());
//		});
//		cf.get();

		// 2)supplyAsync : For callable which return something
//		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
//			try {
//				TimeUnit.SECONDS.sleep(5);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
		// }
		// return "task run by new thread " + Thread.currentThread().getName();
		// });

		// 3)thenApply : When you want to execute 2 Task in NON- Blocking way
//		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
//			return "task run by new thread " + Thread.currentThread().getName();
//		}).thenApply(msg -> {
//			return msg + " appended thenApply";
//		});
//		System.out.println(cf.get());

		// 4) thenRun : If you want 1st task to comeplete and the run 2nd task - no
		// dependency
//		CompletableFuture<Void> cf = CompletableFuture.supplyAsync(() -> {
//			return "task run by new thread " + Thread.currentThread().getName();
//		}).thenRun(() -> {
//			System.out.println(" statement -  thenRun");
//		});
//		System.out.println(cf.get());

		// 5 thenCompose : if there is some Dependency between the task 1 and task 2
//		CompletableFuture<User> task1 = getUserDetails("shalabh", 123455);
//		Function<User, CompletableFuture<String>> creditCheckFunction = user -> getCreditCheck(user);
//		CompletableFuture<String> result = task1.thenCompose(creditCheckFunction);
//		System.out.println(result.get());

		// 6 thenCombine/thenConbineAsyc when we want to run 2 task in separate thread
		// and then combine the results of task1 and task2 in separate thread
		CompletableFuture<String> t1 = task1();
		CompletableFuture<String> t2 = task2();
		CompletableFuture<String> result = t1.thenCombineAsync(t2, (r1, r2) -> {
			return r1 + "-" + r2;
		});
		System.out.println(result.get());
	}

	private static CompletableFuture<String> task1() {
		return CompletableFuture.supplyAsync(() -> {
			return "Switch off the Water Heater";
		});
	}

	private static CompletableFuture<String> task2() {
		return CompletableFuture.supplyAsync(() -> {
			return "Preapare the breakFast";
		});
	}

	private static CompletableFuture<String> getCreditCheck(User user) {
		// TODO Auto-generated method stub
		return CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			if (user.getUserId() == 12345)
				return "illegal user";
			else
				return "legal user";
		});
	}

	private static CompletableFuture<User> getUserDetails(String userName, int userId) {
		return CompletableFuture.supplyAsync(() -> {
			System.out.println(Thread.currentThread().getName());
			return new User(userName, userId);
		});
	}
}

class User {
	private String name;

	public String getName() {
		return name;
	}

	public User(String name, int userId) {
		super();
		this.name = name;
		this.userId = userId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	private int userId;
}
