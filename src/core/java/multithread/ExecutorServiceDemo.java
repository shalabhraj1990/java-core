package core.java.multithread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceDemo {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService es = Executors.newFixedThreadPool(4);
		// 100 tasks - Runnable
//		for (int i = 0; i < 100; i++) {
//			final int j = i;
//			Runnable task = () -> {
//				System.out.println(j + " task is executed by thread :" + Thread.currentThread().getName());
//			};
//			es.execute(task);
//		}
		// 100 task callable
		for (int i = 0; i < 100; i++) {
			final int j = i;
			Callable<String> task = () -> {
				return "Retuned from using callable from child thread : " + j + " : "
						+ Thread.currentThread().getName();
			};
			Future<String> response = es.submit(task);
			while (!response.isDone()) {
				System.out.println("task in progress!!!");
			}

			String msg = response.get();
			System.out.println("response from future :" + msg);
		}
		es.shutdown();
		System.out.println("main thread :" + Thread.currentThread().getName()); // this will execute at last becuase
																				// future.get() is blocking api it will
																				// block the main thread
	}

}
