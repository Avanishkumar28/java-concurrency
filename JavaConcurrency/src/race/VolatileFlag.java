package race;

public class VolatileFlag implements Runnable {
	
	private boolean shouldStop;

	public void run() {
		while (!shouldStop) {
			System.out.println("Running...");
			sleep(5);
		}
		System.out.println("Stopped.");
	}

	void stop() {
		shouldStop = true;
	}

	public static void main(String[] args) throws InterruptedException {
		VolatileFlag flag = new VolatileFlag();
		Thread thread = new Thread(flag);
		thread.start();
		sleep(10);
		
		
		flag.stop();
		thread.join();
	}
	
	private static void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}