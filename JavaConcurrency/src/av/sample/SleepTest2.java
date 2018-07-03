package av.sample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepTest2 {
	
	private static final Object classLock = new Object();

	public static void main(String[] args) {
		SleepTest2 obj_one =  new SleepTest2();
		SleepTest2 obj_two =  new SleepTest2();
		Runnable task_one = obj_one :: commanTask;
		Runnable task_two = obj_two :: commanTask;

		ExecutorService executorService = null;
		try {
			executorService = Executors.newFixedThreadPool(2);
			executorService.execute(task_one);
			executorService.execute(task_two);
			
			executorService.shutdown();
			executorService.awaitTermination(5, TimeUnit.MINUTES);
		} catch (Exception e) {
			if(executorService != null && !executorService.isTerminated())
				executorService.shutdownNow();
			e.printStackTrace();
		}
	}
	
	public void commanTask() {
		System.out.println(Thread.currentThread().getName()+" in Comman Task.");
		synchronized (classLock) {
			System.out.println(Thread.currentThread().getName()+ " IN synchronized Block");
			sleep(5000);
			System.out.println(Thread.currentThread().getName()+ " leaving synchronized Block");
		}
		System.out.println(Thread.currentThread().getName()+" Exiting Comman Task. . .");
	}
	
	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
