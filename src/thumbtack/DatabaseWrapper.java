package thumbtack;

import java.util.Stack;

public class DatabaseWrapper {
	/*
	 * All modification are done in the database object.
	 * @param currentTransaction is the current block of between the nearst "BEGIN" and "END" 
	 * all modifications in this block are stored in the Transaction object, saved for rollback
	 * @param transactions is a stack to hold all nested Blocks */
	public static Database database;
	private Stack<Transaction> transactions;
	private Transaction currentTransaction;

	public DatabaseWrapper(){
		database = new Database();
		transactions = new Stack<Transaction>();
		currentTransaction = null;
	}
	
	/* push currentTransaction and open a new one */
	public void beginNewTransaction(){
		if(currentTransaction!=null){
			transactions.push(currentTransaction);
		}
		currentTransaction = new Transaction();
		if(Main.DEBUG) System.out.println(transactions.size());

	}
	
	/* revert all changes in the currentTransaction and pop the previous transaction */
	public void rollback(){
		if(currentTransaction == null){
			System.out.println("NO TRANSACTION");
		}
		else{
			currentTransaction.rollback(database);
			currentTransaction = transactions.isEmpty()?null:transactions.pop();
		}
	}
	
	/* commit should commit all the transactions, just clear the stack and currentTransaction */
	public void commit(){
		if(currentTransaction == null){
			System.out.println("NO TRANSACTION");
		}
		transactions.clear();
		currentTransaction = null;
	}

	public void exit(int status){
		System.out.println("BYE");
		System.exit(status);
	}

	/* if the key of the new <key, value> pair exists in the original database
	 * 		if (the original <key,value> pair is not recorded ) --> add to originMap, add to originInverseCount			
	 * else add to newMap
	 * 
	 * if the newValue has a common value with the original database
	 *  	add original <value, count> pair to originInverseCoun
	 * else add to newValue;
	 * */
	public void set(String key, int value){
		if(currentTransaction != null){
			if(!currentTransaction.originMap.containsKey(key)){
				if(database.get(key)!=null){
					int oldValue = database.get(key);
					currentTransaction.addOrgMap(key, oldValue);
					int oldCnt = database.numeqto(oldValue);
					currentTransaction.addOrgCnt(oldValue, oldCnt);
				}
				else{
					currentTransaction.addNewMap(key, value);
				}
			}
			if(!currentTransaction.originInverseCnt.containsKey(value)){
				if(database.numeqto(value)>0){
					currentTransaction.addOrgCnt(value, database.numeqto(value));
				}
				else{
					currentTransaction.addNewInverseCnt(value, 1);
				}

			}

		}
		database.set(key,value);
	}
	/* if the unset key is a newly added key --> remove from newMap
	 * else if the key exists in the original database
	 * 		if (the original <key,value> pair is not recorded ) --> add to originMap, add to originInverseCount			
	 */
	public void unset(String key){
		if(currentTransaction != null){
			if(currentTransaction.newMap.containsKey(key)){currentTransaction.newMap.remove(key);}
			else{
				if(database.get(key)!=null && currentTransaction.originMap.get(key)==null){
					int oldValue = database.get(key);
					currentTransaction.addOrgMap(key, oldValue);
					int oldCnt = database.numeqto(oldValue);
					currentTransaction.addOrgCnt(oldValue, oldCnt);
				}
			}
		}
		database.unset(key);
	}
	
	public Integer get(String key){
		return database.get(key) !=null ? database.get(key): null;
	}

	public int numeqto(int value){
		return database.numeqto(value);
	}


}
