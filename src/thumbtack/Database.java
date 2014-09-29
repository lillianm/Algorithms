package thumbtack;

import java.util.HashMap;
import java.util.Stack;

/*
 * @database is used to store the <key,value> pair
 * @inverseCnt is used to store the count of the values
 */
public class Database implements SimpleDatabase{

	private HashMap<String, Integer> database;
	private HashMap<Integer, Integer> inverseCount;
	
	public Database(){
		database = new HashMap<String, Integer>();
		inverseCount = new HashMap<Integer, Integer>();
	}
	
	/* remove the key pair in the database and update the value count*/
	public void unset(String key) {
		
		if(database.containsKey(key)){
			int value = database.get(key);
			database.remove(key);
			int cnt = inverseCount.get(value)-1;
			if(cnt == 0) 
				inverseCount.remove(value);
			else 
				inverseCount.put(value, cnt);
			
			if(Main.DEBUG) System.out.println(inverseCount);
		}
	}
	/* return the number of pairs with the same value*/
	public int numeqto(int value) {
		if(inverseCount.containsKey(value)){
			return inverseCount.get(value);
		}
		return 0;
	}

	/* update or insert a <key,value> pair */
	public void set(String key, int value) {
		if(database.containsKey(key)){
			int oldVal = database.get(key);
			int oldCnt = inverseCount.get(oldVal);
			if(inverseCount.containsKey(oldVal)){
				if(inverseCount.get(oldVal) == 1)
					inverseCount.remove(oldVal);		
				else
					inverseCount.put(oldVal, oldCnt-1);			
			}
		}
		database.put(key, value);
		int cnt = inverseCount.containsKey(value)? inverseCount.get(value):0;
		inverseCount.put(value, cnt+1);
		
		if(Main.DEBUG) System.out.println(inverseCount);
	}
	/* modify the inversedCount map*/
	public void modifyInverseCount(int value, int n) {
		int cnt = inverseCount.containsKey(value)?inverseCount.get(value):0;
		inverseCount.put(value,cnt+n);
	}

	/* return value associated with the key, return null if key does not exist */
	public Integer get(String key) {
		if(database.containsKey(key)){
			return database.get(key);
		}
		return null;
	}
	
	/* Helper functions to rollback to the previous version */
	public void revert(String key, int value){
		database.put(key, value);
	}
	public void revert(int value, int cnt){
		inverseCount.put(value, cnt);
	}

	public void remove(String key){
		if(database.containsKey(key))database.remove(key);
	}
	public void remove(int value){
		if(inverseCount.containsKey(value))inverseCount.remove(value);
	}
	 
}
