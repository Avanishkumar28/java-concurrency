package av.sample;

public class JMMTestOne_ProcessorCache {
	
	private static  int num = 0;
	private static boolean flag = true;
	
	
	Runnable runner_1 = this :: thread1Task;
	Runnable runner_2 = this :: thread2Task;

	public static void main(String[] args) {
		System.out.println("Start...");
		
		JMMTestOne_ProcessorCache jmm_pc = new JMMTestOne_ProcessorCache();
		new Thread(jmm_pc.runner_1).start();
		new Thread(jmm_pc.runner_2).start();
		
		System.out.println("Ending...");

	}
	
	
	public void thread1Task() {
		while(flag) {
			num = 40;
			System.out.printf("thread1Task Thread %s says, Num was: %s \n", Thread.currentThread().getName(), num);
		}
	}
	
	public void thread2Task() {
			if(num == 40) {
				//System.out.printf("thread2Task Thread %s says, Num was: %s \n", Thread.currentThread().getName(), num);
				System.exit(0);
			}
	}

}
