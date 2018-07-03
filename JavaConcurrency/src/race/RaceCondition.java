package race;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RaceCondition {

	private int value_1 = 0;
	//private boolean flag_one = true;
	
	private int value_2 = 0;
	//private boolean flag_two = true;
	
	
	
	public static void main(String[] args) {
		RaceCondition rc = new RaceCondition();
		Runnable task_1 = () -> { 
			rc.taskOne();
		};
		
		Runnable task_2 = () -> {
			rc.taskTwo();
		};
		
		ExecutorService eService = Executors.newFixedThreadPool(2);
		
		eService.submit(task_1);
		eService.submit(task_2);

		eService.shutdown();
		
	}
	
	private void taskOne() {
		while(value_2 != 5) {
			value_1++;
			System.out.println("Task One: "+value_1+" Thread: "+Thread.currentThread().getName());
			sleep(1000);
			//flag_two = false;
		}
	}
	
	private void taskTwo() {
		while(value_1 != 5) {
			value_2++;
			System.out.println("Task Two: "+value_2+" Thread: "+Thread.currentThread().getName());
			sleep(1000);
			//flag_one = false;
		}
	}

	
	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
