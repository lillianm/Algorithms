package thumbtack;
import java.util.Scanner;

public class Test {
	@SuppressWarnings("resource")
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		DatabaseWrapper db = new DatabaseWrapper();
		while(in.hasNext()){
			parseHelper(db, in.nextLine());
		}
	}
	public static void parseHelper(DatabaseWrapper db, String input){
		input = input.toUpperCase();
		switch(input){
		case "BEGIN": db.beginNewTransaction(); break;
		case "END": db.exit(0); break;
		case "ROLLBACK": db.rollback(); break;
		default: 
			
			if(input.startsWith("SET")){
				String[] params = input.split(" ");
				if(params.length !=3){ ErrorInfo.error("SET"); break;}
				String key = params[1];
				int value = Integer.parseInt(params[2]);
				db.set(key, value);
			}
			else{
				if(input.startsWith("UNSET")){
					String[] params = input.split(" ");
					if(params.length !=2){ ErrorInfo.error("SET"); break;}
					String key = params[1];
					db.unset(key);
				}
				else{
					if(input.startsWith("GET")){
						String[] params = input.split(" ");
						if(params.length !=2){ ErrorInfo.error("SET");break;}
						String key = params[1];
						System.out.println(db.get(key));
					}
					else{
						if(input.startsWith("NUMEQTO")){
							String[] params = input.split(" ");
							if(params.length !=2){ ErrorInfo.error("SET");break;}
							int value = Integer.parseInt(params[1]);
							System.out.println(db.numeqto(value));
						}
					}
				}
			}
		}
	}


}
