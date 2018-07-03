package av.forkjoin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Task extends RecursiveTask<Integer>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4929033381336413017L;
	
	private final int[][] matrix;
	private static final int THRESHOLD = 10;
	
	public Task(int[][] matrix) {
		this.matrix = matrix;
	}

	@Override
	protected Integer compute() {
		if(matrix.length>THRESHOLD) {
			Task left = createSubTask(matrix, 0, matrix.length / 2);
			Task right = createSubTask(matrix, matrix.length / 2, matrix.length);
			
			invokeAll(left,right);
		}
		
		return sumMatrix(matrix);
	}

	private Task createSubTask(int[][] matrix, int start, int end) {
		
		return new Task(Arrays.copyOfRange(matrix, 0, matrix.length / 2)); //first half task
	}
	
	/*private Collection<Task> createSubTask() {
		List<Task> subTasks = new ArrayList<>();
		subTasks.add(new Task(Arrays.copyOfRange(matrix, 0, matrix.length / 2))); //first half task
		
		subTasks.add(new Task(Arrays.copyOfRange(matrix, matrix.length / 2, matrix.length))); //second half task
		
		return subTasks;
	}*/

	private Integer sumMatrix(int[][] matrix) {
		
		//return Arrays.stream(matrix).parallel().flatMapToInt(array -> Arrays.stream(array)).sum();
		int result = 0;
		for(int[] inn_matrix : matrix) {
			result = Integer.sum(result, MatrixCalutator.sumArray(inn_matrix));
		}
		
	
		
		return result;
		
	}
	
	static Integer sumArray(int[] arr) {
		return Arrays.stream(arr).sum();
	}
}
