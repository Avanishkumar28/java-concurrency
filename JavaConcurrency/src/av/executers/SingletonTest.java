package av.executers;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest {
	
	private static int count = 1;
	
	private static void createSingletonEmployeeInstance() {
		
		SingletonEmployee.getInstance(count, "Obj:"+count).getName();
        SingletonEmployee.getInstance(count, "Obj:"+count).getName();
	
	}

	public static void main(String[] args) {
		ExecutorService se =  Executors.newFixedThreadPool(50);
		
		Runnable task = SingletonTest :: createSingletonEmployeeInstance;

		for (int i = 0; i < 1000; i++) {
			se.submit(task);
		}
	}

}
