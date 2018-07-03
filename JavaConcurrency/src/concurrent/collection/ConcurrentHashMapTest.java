package concurrent.collection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Integer> concurrentMap = new ConcurrentHashMap<>();
		/**
		concurrentMap.put(null, 0);   //-- null key is not allow in ConcurrentHashMap. 
		concurrentMap.put("zero", null); //-- null value is not allow in ConcurrentHashMap. 
		
		//-- Run time error NullPointerException 
		System.out.println(concurrentMap.get("zero"));
		**/
		
		for(int i = 0; i<=10; i++){
			concurrentMap.put("Value_"+i, i);
		}
		
		System.out.println(concurrentMap);
		for(Map.Entry<String, Integer> entry : concurrentMap.entrySet()){
			
			/*if("Value_1".equals(entry.getKey())) {
				concurrentMap.remove(entry.getKey());
			}*/
			if("Value_3".equals(entry.getKey())) {
				concurrentMap.clear();
			}
			/*try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
			System.out.println(entry.getKey() +" -- "+entry.getValue());
		}
		System.out.println("*****************After******************");
		System.out.println(concurrentMap);

	}

}
