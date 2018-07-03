package av.executers;

public class SingletonEmployee {

	private int id;
	private String name;
	
	private static final Object monitor = new Object();
	public static  SingletonEmployee instance = null;
	private static int objCount = 0;
	
	private SingletonEmployee(int id, String name) {
		this.id = id;
		this.name = name;
		
		System.out.println("SingletonEmployee's objects in memory: "+ ++objCount);
	}

	public static SingletonEmployee getInstance(int id, String name) {
		if(instance == null) {
			synchronized (monitor) {
				if(instance == null)
					instance = new SingletonEmployee(id, name);
			}
		}
		return instance;
	}
	
	public int getId() {
		return id;
	}

	public String getName() {
		System.out.println("Name: "+instance.name);
		return name;
	}
	
}
