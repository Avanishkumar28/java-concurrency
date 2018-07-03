package av.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ClassLevelSynchonized {

	public static void main(String[] args) {
		Runnable task_one = ClassLevelSynchonized :: m_one;
		Runnable task_two = ClassLevelSynchonized :: m_two;
		
		ClassLevelSynchonized obj = new ClassLevelSynchonized();
		Runnable task_three = obj :: m_three;
		
		ExecutorService executorService = null;
		try {
			executorService = Executors.newFixedThreadPool(3);
			executorService.execute(task_one);
			executorService.execute(task_two);
			executorService.execute(task_three);
			
			executorService .shutdown();
			executorService.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			if(executorService != null && !executorService.isShutdown())
				executorService.shutdownNow();
		}
	}
	
	public static synchronized void m_one() {
		System.out.println("in m_one");
		sleep(1000);
		System.out.println("exiting m_one....");
		
	}
	
	public static synchronized void m_two() {
		System.out.println("in m_two");
		sleep(1000);
		System.out.println("exiting m_two....");
		
	}
	
	public synchronized void m_three() {
		System.out.println("in m_three");
		sleep(100);
		System.out.println("exiting m_three....");
		
	}
	
	public static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
