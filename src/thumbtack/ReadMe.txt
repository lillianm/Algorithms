How to run the code:

java -jar simpledb.jar

step1:
	input 0 : read from STDIN
	input 1 : read from test file

step2:
	input 0 : silient mode
	input 1 : output inner hashmap entries

(optional)
step3:
	if use test file, input <path>/<filename>
	
Start to use simple database



Design of SimpleDatabase:


@class Database:
	The object that always keep the most up-to-date <key,value> pair 
	
	@param HashMap<String, Integer> database : store <key, value> pair
	@param HashMap<Integer, Integer> inverseCount: store how many entries has the value 
	regular set/unset/numeqto/get methods 
	
@class Transaction
	The object the store the difference since the nearest BEGIN command
	
	  @param HashMap<String, Integer> originMap : original <key, value> pair if the pair is modified in the current transaction 
	  @param HashMap<Integer, Integer> originInverseCount : original <value, count> pair if the current modification change the value 
	  @param HashMap<String, Integer> newMap is the newly added <key, value> pair in this block that does not exist in original db
	   (actually only need a hashset here hashmap can be extended to rollback any commond in the future 
	  @param HashMap<Integer, Integer> newInverseCnt is the newly added value that does not exist in original db
	
@class DatabaseWrapper
	The object that manage database and variance transactions
	Modify the database, and update the current Transaction to store all the difference
	Push and pop from stack to deal with BEGIN and ROLLBACK
	
	@param Transaction currentTransaction is the current block of between the nearwst "BEGIN" and "END" 
	 	 all modifications in this block are stored in the Transaction object, saved for rollback
	@param Stack<Transcation> transactions is a stack to hold all nested Blocks 

@Interface SimpleDatabase:
	Just to remind me of all methods that should be implemented
	
@class ErrorInfo
	Helper Class
	
@class Main
	Launcher Class
	