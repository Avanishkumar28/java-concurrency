package av.synchronize;

public class SynchronizedTest {

	public static void main(String[] args) {
		SynchronizedTest obj = new SynchronizedTest();
		Runnable task_one = SynchronizedTest :: m_one;
		Runnable task_two = obj :: m_two;
		Runnable task_three = obj :: m_three;
		Runnable task_four = obj :: m_four;
		
		Thread t1 = new Thread(task_one);
		Thread t2 = new Thread(task_two);
		Thread t3 = new Thread(task_three);
		Thread t4 = new Thread(task_four);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		

	}
	
	public static synchronized void m_one() { //class level lock
		System.out.println("m_one");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m_one Exit..");
	}
	
	public void m_two() {
		synchronized(this){ //object level lock
			System.out.println("m_two");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("m_two Exit..");
		}
		
	}
	
	public void m_three() {
		synchronized(SynchronizedTest.class){ //class level lock
			System.out.println("m_three");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("m_three Exit..");
		}
		
	}
	
	public void m_four() {
		System.out.println("m_four");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("m_four Exit..");
	}

	private void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
