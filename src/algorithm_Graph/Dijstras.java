package algorithm_Graph;
import java.util.*;
public class Dijstras {
	/* Using PriorityQueue to select the nearest vertex to the source node */
	public static void shortestPath(Vertex source, Set<Vertex> nodes, ArrayList<Edge> edges) throws GraphException{
		int N = nodes.size();

		Set<Vertex> settled = new HashSet<Vertex>();
		PriorityQueue<Vertex> queue = new PriorityQueue<Vertex>(N, new Comparator<Vertex>(){

			@Override
			public int compare(Vertex o1, Vertex o2) {
				return o1.dist - o2.dist;
			}
		});

		for(Vertex v:nodes){
			queue.add(v);
		}

		/* cnt stores the iteration times, if the queue does not become empty when all nodes are visited,
		 * negative weights detected*/
		int cnt = 0;
		while(queue.size()>0){
			if(cnt >= nodes.size()){
				throw new GraphException(1);
			}
			/* Select the nearest node and update its neighbors */
			Vertex nearest = queue.poll();
			settled.add(nearest);
			for(Edge e:nearest.edges){
				Vertex target = e.getTarget(nearest);
				if(target.dist > nearest.dist+e.weight){
					queue.remove(target);
					target.dist = nearest.dist+e.weight;
					queue.add(target);
				}
			}
			cnt++;
		}


	}
	/* if source node is not given, we can find it */
	public Vertex findSourceNode( Set<Vertex> remain, ArrayList<Edge> edges) throws GraphException{
		for(Vertex v:remain){
			if(v.edges.size() == 1){
				return v;
			}
		}
		throw new GraphException(0);


	}
}
