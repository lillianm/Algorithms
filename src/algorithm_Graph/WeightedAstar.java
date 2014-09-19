package algorithm_Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Set;

public class WeightedAstar {
	public static void dynamicAstart(Set<Vertex> nodes, ArrayList<Edge> edges, Vertex source, final float epsilon){
		int N = 1;
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(nodes.size(), new Comparator<Vertex>(){
			@Override
			public int compare(Vertex v1, Vertex v2){
				return (int) ((1-epsilon*v1.depth/N)*v1.heuristic + v1.dist - (1-epsilon*v2.depth/N)*v1.heuristic + v2.dist)));
			}
		});
	}
	public void update 
	

}
