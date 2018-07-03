package av.executers;

import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CoffeeThreads {
	
	static Stack<String> coffeeStack = new Stack<>();
	
	public static void takeCoffee() {
		System.out.println(coffeeStack.peek());
		coffeeStack.pop();
	}
	
	public static void addCoffee() {
		System.out.println(coffeeStack.size());
		if (coffeeStack.size()<=0) {
			for (int i = 1; i <= 10; i++) {
				coffeeStack.push("Coffee Cup-"+i);
			}
		}else {
			System.out.println("Coffee avalabel");
		}
		
	}

	public static void accessCoffee() {
		System.out.println("Current Thread: "+Thread.currentThread().getName());
		addCoffee();
		while (coffeeStack.size()>=1) {
			takeCoffee();
		}
		
	}
	
	public static void main(String[] args) {
		ExecutorService executorService = null;
		try {
			executorService = Executors.newFixedThreadPool(5);
			executorService.submit(CoffeeThreads :: accessCoffee);
		}finally {
			executorService.shutdown();
		}
		
	}

}
