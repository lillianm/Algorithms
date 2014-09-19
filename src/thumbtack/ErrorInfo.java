package thumbtack;

public class ErrorInfo {
	public static void error(String mode){
		switch(mode){
		case "SET":
			System.out.println("Parameter Incorrect;  Please input \"SET <key> <value> \"");break;
		case "UNSET":
			System.out.println("Parameter Incorrect; Please input \"UNSET <key>\"");break;

		case "NUMEQTO":
			System.out.println("Parameter Incorrect; Please input \"NUMEQTO <value> \"");break;

		case "GET":
			System.out.println("Parameter Incorrect; Please input \"GET <key>\"");break;
		default:System.out.println("Not Valid Command\n"); break;

		}
	}
}
