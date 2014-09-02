package algorithm_Graph;

public class GraphException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public GraphException(int id){
		String error = "";
		switch(id){
		case 0:
			error = "Cycle Detected";
			break;
		case 1: 
			error = "Negative Edge Detected";
			break;
		default:
			break;
		}
		System.err.println(error);
	}

}
