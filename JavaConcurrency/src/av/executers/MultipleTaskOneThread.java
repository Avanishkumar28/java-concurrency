package av.executers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MultipleTaskOneThread {
	
	static int counter = 0;
	
	
	public static void threadTaskOne() {
		System.out.println("Task One start by: "+Thread.currentThread().getName());
		for(int i = 0; i<=100000; i++) {
			counter++;
		}
		
		System.out.println("Task one completed. ");
	}

	public static void threadTaskTwo() {
		System.out.println("Task Two start by: "+Thread.currentThread().getName());
		for(int i = 0; i<=100; i++) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
		
		System.out.println("Task two completed. ");
	}
	
	public static void threadTaskThree() {
		System.out.println("Task Three start by: "+Thread.currentThread().getName());
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Task Three completed. ");
	}
	
	public static void main(String[] args) {
		System.out.println("Main Thread start.");
		
		ExecutorService executorService = null;
		try {
			executorService = Executors.newSingleThreadExecutor();
			executorService.execute(MultipleTaskOneThread :: threadTaskOne);
			executorService.execute(MultipleTaskOneThread :: threadTaskTwo);
			executorService.execute(MultipleTaskOneThread :: threadTaskThree);
			
		}finally {
			executorService.shutdown();
		}
		System.out.println("Try to submit new task after shutdown.");
		executorService.execute(() -> {
			System.out.println("Hello, This is new Task");
			return;
		});
		
		while(counter < 90000) {
			System.out.println("Still waiting for counter to reach 90000 mark.");
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Main Thread End.");
	}

}
