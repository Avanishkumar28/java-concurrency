package av.future;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FutureWithRunnable {
	
	static int myValue = 5;
	
	public static void myTask() {
		int result = 0;
		System.out.println(Thread.currentThread().getName());
		
		for(int i = 0; i<=1000; i++) {
			if(i%5 == 0) {
				result += i;
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		result += myValue;
				
		System.out.println("Final Result: "+result);
	}

	public static void main(String[] args) {
		//myTask();
		
		ExecutorService executorService;
		
			executorService = Executors.newFixedThreadPool(5);
			executorService.execute(FutureWithRunnable :: myTask);
			executorService.execute(FutureWithRunnable :: myTask);
			executorService.execute(FutureWithRunnable :: myTask);
			executorService.execute(FutureWithRunnable :: myTask);
			
			/*executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					FutureWithRunnable.myTask();
					
				}
			});*/
		
		executorService.shutdown();
	}

}
