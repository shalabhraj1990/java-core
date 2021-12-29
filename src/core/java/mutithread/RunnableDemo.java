package core.java.mutithread;

public class RunnableDemo {
	public static void main(String[] args) {
		Runnable task = new Runnable() {

			@Override
			public void run() {
				System.out.println("This task is performed by new thread :" + Thread.currentThread().getName());

			}
		};

		Thread thread = new Thread(task);
		thread.start();
		System.out.println("Main thread :" + Thread.currentThread().getName());

	}
}
