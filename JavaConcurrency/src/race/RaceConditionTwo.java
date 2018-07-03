package race;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RaceConditionTwo {
	
	 private boolean shoudStop;
	 private int counter;
	
	Runnable task_one = () -> {
		while (!shoudStop) {
			counter++;
			System.out.println("Running....at: "+counter);
			sleep(100);
		}
		
		System.out.println("Stoped");
	};
	
	
	Runnable task_two = () -> {
		while(true) {
			if(counter==5) {
				shoudStop = true;
				break;
			}
			System.out.println("task_two Running....at: "+counter);
			sleep(100);
		}
	};

	public static void main(String[] args) {
		
		RaceConditionTwo rcTwo = new RaceConditionTwo();
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		executorService.submit(rcTwo.task_one);
		executorService.submit(rcTwo.task_two);
		
		executorService.shutdown();

	}
	
	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
