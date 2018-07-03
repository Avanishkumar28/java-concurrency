package av.sample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SleepTest {
	
	private final Object lock = new Object();

	public static void main(String[] args) {
		SleepTest sleepTest =  new SleepTest();
		Runnable task_one = sleepTest :: commanTask;
		Runnable task_two = sleepTest :: commanTask;

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
		/*for(int loop_i = 0; loop_i <= 1_00_00_00_000; loop_i ++) {
			//do nothing..
		}*/
		synchronized (lock) {
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
