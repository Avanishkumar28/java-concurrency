package av.sample;

import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SampleTest {
	
	volatile int sharedValue = 0;
	
	public void threadWork() {
		System.out.println("Currnt Thread: "+Thread.currentThread().getName());
		Stream mystrem = Stream.iterate(1, e -> e+1)
							   .limit(100L)
							   .filter(e -> IntStream.rangeClosed(2, e/2)
									  .noneMatch(i -> e%i==0)
									  );
		mystrem.forEach(System.out :: println);
		System.out.println("Total count: "+mystrem.count());
			  
		System.out.println("Currnt Thread: "+Thread.currentThread().getName()+" finished there task.");	  
	}
	
	public int getNext(int num) {
		return num+1;
	}
	
	public void threadTask() {
		System.out.println("Current Thread: "+Thread.currentThread().getName());
		try {
			Thread.sleep(100);
			for(int i=1; i<=5; i++) {
				System.out.println("Current Thread: "+Thread.currentThread().getName()+" Current value: "+sharedValue+ ", now increase by one");
				sharedValue++;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Currnt Thread: "+Thread.currentThread().getName()+" finished there task.");	  
		
	}
	
	public static void main(String[] args) {
		SampleTest sampleTest = new SampleTest();
		/*Runnable runner = sampleTest :: threadWork;
		new Thread(runner).start();*/
		
		Runnable runnerTask = sampleTest :: threadTask;
		new Thread(runnerTask, "Thread-1").start();
		new Thread(runnerTask, "Thread-2").start();
		new Thread(runnerTask, "Thread-3").start();
		new Thread(runnerTask, "Thread-4").start();
		new Thread(runnerTask, "Thread-5").start();
		new Thread(runnerTask, "Thread-6").start();

	}

}
