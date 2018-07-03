package av.forkjoin;

import java.util.concurrent.ForkJoinPool;

public class ForkJoinTest {

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		
		long start = System.nanoTime();
		Task task = new Task(InputMatrix.matrix);
		Integer result = pool.invoke(task);
		long end = System.nanoTime();
		
		System.out.println(result+" Time: "+(end-start)+" nonSec");

	}

}
