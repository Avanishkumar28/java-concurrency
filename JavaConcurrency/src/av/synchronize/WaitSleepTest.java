package av.synchronize;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class WaitSleepTest {
	
	private final Object monitor = new Object(); 
	
	public void threadWork() {
		System.out.println("Hello befor Lock: "+Thread.currentThread().getName());
		synchronized (monitor) {
			try {
				System.out.println("Going to Wait>>>: "+Thread.currentThread().getName());
				monitor.wait();
				System.out.println("Wait is Over: "+Thread.currentThread().getName()+". Finish remaning task");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			System.out.println("Bye: "+Thread.currentThread().getName());
		}
	}
	
	public void weakUpThreads() {
		synchronized (monitor) {
			System.out.println("Weak Up Thread kick off.");
			monitor.notify(); //weak up only thread, which come first in pool on this monitor.
			monitor.notifyAll(); //weak up all thread
		}
	}

	public static void main(String[] args) {
		WaitSleepTest wsTest = new WaitSleepTest();
		Runnable task = () -> wsTest.threadWork();
		Runnable weakupTask = () -> wsTest.weakUpThreads();
		
		Thread t1 = new Thread(task, "T-One");
		Thread t2 = new Thread(task, "T-Two");
		Thread t3 = new Thread(task, "T-Three");
		
		t1.start();
		t2.start();
		t3.start();
		
		Thread t4 = new Thread(weakupTask, "WeakUp Thread");
		t4.start();
		
		/*ExecutorService es = null;
		try {
			es = Executors.newFixedThreadPool(3); 
			es.submit(task);
			es.submit(task);
			es.submit(task);
			
			es.submit(task);
			es.submit(task);
			es.submit(task);
			
			es.shutdown();
			try {
				es.awaitTermination(5, TimeUnit.MINUTES);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			if(es != null && !es.isShutdown())
				es.shutdownNow();
		}*/
		
		
		

	}

}

