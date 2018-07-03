package av.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MatrixCalutator {

	static int[][] matrix = InputMatrix.matrix;
	
	static Integer sumArray(int[] arr) {
		return Arrays.stream(arr).sum();
	}
	
	public static void main(String[] args) {
		long start = System.nanoTime();
		
		ExecutorService executorService = null;
		List<Future<Integer>> futuresResult = new ArrayList<>();
		try {
			//executorService = Executors.newFixedThreadPool(matrix.length);
			executorService = Executors.newCachedThreadPool();
			
			for(int[] inn_matrix : matrix) {
				futuresResult.add(executorService.submit(() -> MatrixCalutator.sumArray(inn_matrix)));
			}
					
			executorService.shutdown();
			//executorService.awaitTermination(5, TimeUnit.MINUTES);
		} finally {
			if (executorService != null && !executorService.isTerminated()) {
				executorService.shutdownNow();
			}
		}
		int result = 0;
		for(Future<Integer> future : futuresResult) {
			try {
				result = Integer.sum(result, future.get());
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		long end = System.nanoTime();
		System.out.println(result+" Time: "+(end-start));
		
		//System.out.println(Runtime.getRuntime().availableProcessors());
		

	}
	

}
