package av.future;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.IntPredicate;
import java.util.stream.IntStream;

public class FutureTest {

	public static void main(String[] args) {
		ExecutorService executorService = null;
		try {
			executorService = Executors.newFixedThreadPool(5);
			
			Future<List<Integer>> futureResult = new CompletableFuture<List<Integer>>();
			futureResult = executorService.submit(() -> FutureTest.getPrimeNumber(1000000));
		
			futureResult.get().forEach(System.out :: println);
			
		} catch (InterruptedException | ExecutionException e) {
			
			e.printStackTrace();
		}finally {
			executorService.shutdown();
		}
		
		
		/*long startTime = System.currentTimeMillis();
		
		getPrimeNumber(1000000).stream()
						  .forEach(System.out :: println);
						  
		long endTime = System.currentTimeMillis();
		System.out.println("Total Time Taken: "+(endTime-startTime));*/
		
	}
	
	public static List<Integer> getPrimeNumber(int range) {
		List<Integer> result = IntStream.rangeClosed(1, range)
										 //.parallel()
										 .filter(FutureTest :: isPrime)
										 .collect(ArrayList :: new, List :: add, List :: addAll);
		
		return result;
	}
	
	public static boolean isEven(int number) {
		IntPredicate evenPredicate = n -> n%2 == 0;
		
		return evenPredicate.test(number);
	}
	
	public static boolean isPrime(int number) {
		
		IntPredicate primePredicate = n -> IntStream.rangeClosed(2, n/2)
													//.parallel()
													.noneMatch(i -> n%i == 0);
		return primePredicate.test(number);
		
		/*return !IntStream.rangeClosed(2, number/2)
						 .peek(e -> System.out.println("Peek Prime Test: "+e))
						 .anyMatch(i -> number%i == 0); */
	}

}
