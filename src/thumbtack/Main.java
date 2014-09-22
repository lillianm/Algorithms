package thumbtack;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	public static boolean DEBUG = false;
	public static void main(String[] args){
		Scanner in = new Scanner(System.in);
		System.out.println("Please Select Input Mode (type 0/1): \n STDIN: 0; Read from test file: 1");
		int mode = Integer.parseInt(in.nextLine());
		DatabaseWrapper db = new DatabaseWrapper();
		System.out.println("Debug Mode (output database entries)? N0:  0 ; YES:  1");
		DEBUG = Integer.parseInt(in.nextLine()) ==1?true:false;

		if(mode == 0){

			while(in.hasNext()){
				parseHelper(db, in.nextLine());
			}
		}
		else{
			System.out.println("Please input file name (input <absolute path> if can not find file)\n default test file:\"<path>/test.txt\"");
			String fn = in.nextLine();
			FileReader fr;
			try {
				fr = new FileReader(fn);
				BufferedReader br = new BufferedReader(fr);
				String thisline = null;
				while((thisline = br.readLine())!=null){
					parseHelper(db,thisline);
				}
			} catch (FileNotFoundException e) {

				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}
	public static void parseHelper(DatabaseWrapper db, String input){
		input = input.replaceAll("(?:/\\*(?:[^*]|(?:\\*+[^*/]))*\\*+/)|(?://.*)","").trim().toUpperCase();
		switch(input){
		case "BEGIN": db.beginNewTransaction(); break;
		case "END": db.exit(0); break;
		case "ROLLBACK": db.rollback(); break;
		case "COMMIT": db.commit(); break;
		case "EXIT": db.exit(0);break;
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
					if(params.length !=2){ ErrorInfo.error("UNSET"); break;}
					String key = params[1];
					db.unset(key);
				}
				else{
					if(input.startsWith("GET")){
						String[] params = input.split(" ");
						if(params.length !=2){ ErrorInfo.error("GET");break;}
						String key = params[1];
						System.out.println(db.get(key));
					}
					else{
						if(input.startsWith("NUMEQTO")){
							String[] params = input.split(" ");
							if(params.length !=2){ ErrorInfo.error("NUMEQTO");break;}
							int value = Integer.parseInt(params[1]);
							System.out.println(db.numeqto(value));
						}
						else{
							System.out.println("INVALID COMMAND");
						}
					}
				}
			}
		}
	}


}
