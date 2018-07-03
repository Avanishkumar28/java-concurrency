package av.forkjoin;

import java.util.concurrent.RecursiveTask;

public class FibonacciComputation extends RecursiveTask<Integer> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 573172208746189199L;
	private final int number;
	
	public FibonacciComputation(int number) {
		
		this.number = number;
	}
	
	@Override
	protected Integer compute() {
		
		if(number < 2)
			return number;
		FibonacciComputation f1 = new FibonacciComputation(number - 1);
        f1.fork(); // send for farther split
        System.out.println("Current Therad Name = "+Thread.currentThread().getName());
        FibonacciComputation f2 = new FibonacciComputation(number - 2);
        Integer result = f2.compute(); //do not split, and compute task
        result += f1.join(); //wait for f1 to finish.
        return  result;
		
	}

	
}
