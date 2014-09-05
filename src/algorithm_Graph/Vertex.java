package algorithm_Graph;

import java.util.ArrayList;

public class Vertex {
	public int id;
	public int value;
	public ArrayList<Edge> edges;
	public int dist;
	public Vertex precedent = null;
	
	public Vertex(int id){
		this.id = id;
		this.value = id;
		edges = new ArrayList<Edge>();
		dist = Integer.MAX_VALUE;
	}

	@Override
	public boolean equals(Object o){
		if(o instanceof Vertex){
			return ((Vertex) o).id == this.id;
		}
		if(o instanceof Integer){
			System.out.println("haha");
			return (int) o == this.id;
		}
		return false;
	}
	@Override
	public int hashCode(){
		return id;
	}
}
 