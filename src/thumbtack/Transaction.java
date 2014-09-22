package thumbtack;

import java.util.HashMap;
import java.util.HashSet;

public class Transaction{
	/*
	 * @param originMap is the original <key, value> pair if the pair is modified in the current transaction 
	 * @param originInverseCount is the original <value, count> pair if the current modification change the value 
	 * @param newMap is the newly added <key, value> pair in this block that does not exist in original db
	 *  (actually only need a hashset here hashmap can be extended to rollback any commond in the future 
	 * @param newInverseCnt is the newly added value that does not exist in original db
	 * */
	public HashMap<String, Integer> originMap;
	public HashMap<Integer, Integer> originInverseCnt;
	public HashMap<String, Integer> newMap;
	public HashMap<Integer, Integer> newInverseCnt;

	public Transaction(){
		newMap = new HashMap<String, Integer>();
		originMap = new HashMap<String, Integer>();
		originInverseCnt = new HashMap<Integer, Integer>();
		newInverseCnt = new HashMap<Integer, Integer>();
	}
	
	
	public void addOrgMap(String key, int value){
		originMap.put(key, value);
	}
	public void addOrgCnt(int value, int cnt){
		originInverseCnt.put(value, cnt);
	}
	public void addNewMap(String key, int value){
		newMap.put(key, value);
	}
	public void addNewInverseCnt(int value, int cnt){
		newInverseCnt.put(value, cnt);
	}
	
	public void rollback(Database db){
		for(String key:newMap.keySet()){
			db.remove(key);
		}
		for(int newValue:newInverseCnt.keySet()){
			db.remove(newValue);
		}
		for(String key:originMap.keySet()){
			db.revert(key, originMap.get(key));
		}
		for(int value:originInverseCnt.keySet()){
			db.revert(value, originInverseCnt.get(value));
		}
	}



}
