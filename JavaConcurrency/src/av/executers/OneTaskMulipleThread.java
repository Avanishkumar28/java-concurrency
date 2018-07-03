package av.executers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

public class OneTaskMulipleThread {
	
	static int counter = 0;
	
	public static void threadCounter() {
		System.out.println("Current Thread: "+Thread.currentThread().getName()+ " counter::>> "+counter);
		while (counter<=9999) {
			if(counter%100==0) {
				System.out.println(counter);
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				
			
			++counter;
		}
		
		getSum();
		System.out.println("Current Thread: "+Thread.currentThread().getName()+ " counter ::: "+counter);
	}
	
	public static void getSum() {
		System.out.println("By Thread: "+Thread.currentThread().getName()+"\n"+
				IntStream.iterate(0, e -> e+1)
						 .limit(1000)
						 .filter(e -> e%5 == 0)
						 
						 .sum()
				);
	}

	public static void main(String[] args) {
		ExecutorService executorService = null;
		
		try {
			executorService = Executors.newFixedThreadPool(5);
			executorService.submit(OneTaskMulipleThread :: threadCounter);
		}finally {
			executorService.shutdown();
		}
		
		
		

	}

}
