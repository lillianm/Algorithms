package thumbtack;

import java.util.HashMap;
import java.util.Stack;

/*
 * @database*/
public class Database implements SimpleDatabase{


	private HashMap<String, Integer> database;
	private HashMap<Integer, Integer> inverseCount;
	public Database(){
		database = new HashMap<String, Integer>();
		inverseCount = new HashMap<Integer, Integer>();
	}
	public void unset(String key) {
		if(database.containsKey(key)){
			int value = database.get(key);
			database.remove(key);
			int cnt = inverseCount.get(value)-1;
			if(cnt == 0) inverseCount.remove(value);
			else inverseCount.put(value, cnt);
			System.out.println(inverseCount);
		}
	}

	public int numeqto(int value) {
		if(inverseCount.containsKey(value)){
			return inverseCount.get(value);
		}
		return 0;
	}


	public void set(String key, int value) {
		if(database.containsKey(key)){
			int oldVal = database.get(key);
			int oldCnt = inverseCount.get(oldVal);
			if(inverseCount.containsKey(oldVal)){
				if(inverseCount.get(oldVal) == 1){
					inverseCount.remove(oldVal);
					}
				else{ 
					inverseCount.put(oldVal, oldCnt-1);
					}
			}
		}
		database.put(key, value);
		int cnt = inverseCount.containsKey(value)? inverseCount.get(value):0;
		inverseCount.put(value, cnt+1);
		System.out.println(inverseCount);
	}

	public void modifyInverseCount(int value, int n) {
		int cnt = inverseCount.containsKey(value)?inverseCount.get(value):0;
		inverseCount.put(value,cnt+n);
	}


	public Integer get(String key) {
		if(database.containsKey(key)){
			return database.get(key);
		}
		return null;
	}
	
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
