package thumbtack;

import java.util.Stack;

public class DatabaseWrapper {
	public static Database database;
	private Stack<Transaction> transactions;
	private Transaction currentTransaction;

	public DatabaseWrapper(){
		database = new Database();
		transactions = new Stack<Transaction>();
		currentTransaction = null;
	}

	public void beginNewTransaction(){
		if(currentTransaction!=null){
			transactions.push(currentTransaction);
		}
		currentTransaction = new Transaction();
		System.out.println(transactions.size());

	}
	public void rollback(){
		if(currentTransaction == null){
			System.out.println("No rollback Allowed");
		}
		else{
			currentTransaction.rollback(database);
			currentTransaction = transactions.isEmpty()?null:transactions.pop();
		}
	}
	/* commit should commit all the transactions */
	public void commit(){
		transactions.clear();
		currentTransaction = null;
	}

	public void exit(int status){
		System.out.println("Bye");
		System.exit(status);
	}


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
