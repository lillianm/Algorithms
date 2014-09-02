package algorithm_Graph;

public class Edge {
	
	public Vertex node1;
	public Vertex node2;
	public int weight = 1;
	
	public Edge(Vertex node1, Vertex node2){
		this.node1 = node1;
		this.node2 = node2;
	}
	public Vertex getTarget(Vertex v){
		if(v == node1) return node2;
		return node1;
	}
}
