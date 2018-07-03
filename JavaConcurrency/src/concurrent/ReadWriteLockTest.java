package concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ReadWriteLockTest {
	
	private static List<Integer> numbers = new ArrayList<>();
	
	Runnable task_1 = this :: addItemToList;
	Runnable task_2 = this :: readListSize;

	public static void main(String[] args) {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		
		ReadWriteLockTest readWriteLockTest = new ReadWriteLockTest();
		try {
			for(int i=0; i<10; i++) {
				executorService.submit(readWriteLockTest.task_1);
				executorService.submit(readWriteLockTest.task_2);
			}
			executorService.shutdown();
		}finally {
			executorService.shutdownNow();
		}
		

	}
	
	public void addItemToList() {
		Random random = new Random();
		int rendomNumber = random.nextInt(100);
		System.out.println("Befor Add: "+numbers.size());
		numbers.add(rendomNumber);
		System.out.println("After Add: "+numbers.size());
	}
	
	public void readListSize() {
		System.out.println(numbers.size());
	}

}
