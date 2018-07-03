package av.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ObjectSynchornized {

	public static void main(String[] args) {
		ObjectSynchornized obj = new ObjectSynchornized();

		Runnable task_one = obj :: m_one;
		Runnable task_two = obj :: m_two;
		
		ExecutorService executorService = null;
		try {
			executorService = Executors.newFixedThreadPool(2);
			executorService.execute(task_one);
			executorService.execute(task_two);
			
			executorService .shutdown();
			executorService.awaitTermination(5, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}finally {
			if(executorService != null && !executorService.isShutdown())
				executorService.shutdownNow();
		}
	}
	
	
	public synchronized void m_one() {
		System.out.println("in m_one");
		sleep(1000);
		System.out.println("exiting m_one....");
		
	}
	
	public synchronized void m_two() {
		System.out.println("in m_two");
		sleep(1000);
		System.out.println("exiting m_two....");
		
	}
	
	
	/*public synchronized void m_one() {
		System.out.println("in m_one");
		sleep(1000);
		System.out.println("exiting m_one....");
		
	}
	
	public synchronized void m_two() {
		synchronized (this) {
			System.out.println("in m_two");
			sleep(1000);
			System.out.println("exiting m_two....");
		}
		
	}*/
	
	
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
