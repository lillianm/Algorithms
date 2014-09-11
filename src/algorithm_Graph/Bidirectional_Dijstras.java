package algorithm_Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import algorithm_Graph.Test.Factory;


public class Bidirectional_Dijstras {
	public static int bidirect_dijstras(Vertex source, Vertex target
			, Set<Vertex> nodes, ArrayList<Edge> edges){
		int N = nodes.size();
		/* Visited Set for source node and target node */
		Set<Vertex> set_source = new HashSet<Vertex>();
		Set<Vertex> set_target = new HashSet<Vertex>();
		Comparator<Vertex> pqComparator = new Comparator<Vertex>(){

			@Override
			public int compare(Vertex o1, Vertex o2) {
				return o1.dist - o2.dist;
			}
		};
		PriorityQueue<Vertex> pq_source = new PriorityQueue<Vertex>(N, pqComparator);
		PriorityQueue<Vertex> pq_target = new PriorityQueue<Vertex>(N, pqComparator);
		
		/*init*/
		source.dist = 0;
		target.dist = 0;
		set_source.add(source);
		set_target.add(target);
		pq_source.add(source);
		pq_target.add(target);
		boolean found = false;
		
		/* Apply Single Dijstras Algorithm iteratively from source and target
		 * Stop when any node exist in both Dijstras set */
		while(!found && set_source.size() + set_target.size() < N){
			/* Dijstra from source */
			Vertex v1 = pq_source.poll();

			for(Edge e:v1.edges){

				Vertex t1 = e.getTarget(v1);
				if(set_source.contains(t1)){continue;}

				if( set_target.contains(t1)){ found = true; break;}
				if(t1.dist > v1.dist+e.weight){
					t1.dist = v1.dist +e.weight;
					t1.precedent = v1;
				}

				set_source.add(t1);
				pq_source.add(t1);
			}
			/* Dijstra from target */
			Vertex v2 = pq_target.poll();
			for(Edge e:v2.edges){
				Vertex t2 = e.getTarget(v2);
				if(set_target.contains(t2)) continue;
				if(set_source.contains(t2)){found = true; break;}
				if(t2.dist > v2.dist+e.weight){
					t2.dist = v2.dist + e.weight;
					t2.precedent = v2;
				}
				set_target.add(t2);
				pq_target.add(t2);
			}
		}

		/* Revisit two set to find minimum weight , the connect node does not exist in Shortest
		 * path necessarily */
		int minPath = Integer.MAX_VALUE;
		Edge bridge = null;
		Set<Vertex> visited = new HashSet<Vertex>();
		for(Vertex v:set_source){
			if(!visited.contains(v)){
				for(Edge e:v.edges){
					Vertex neighbor = e.getTarget(v);
					if(!set_source.contains(neighbor) && set_target.contains(neighbor)){
						/* The shortest path is the dist(source to node1) + bridge + dist(target + node2)*/
						if(minPath > v.dist + e.weight+neighbor.dist){
							minPath = v.dist + e.weight + neighbor.dist;
							bridge = e;
						}
					}
				}
			}
		}
		printPath(reconstructPath(bridge, set_source, set_target));
		return minPath;
	}
	/* reconstruct the path from source to target */
	public static ArrayList<Vertex> reconstructPath(Edge e, Set<Vertex> set1, Set<Vertex> set2){
		ArrayList<Vertex> path= new ArrayList<Vertex>();
		Vertex cur = e.node1;
		while(cur != null){
			path.add(cur);
			cur = cur.precedent;
		}
		cur = e.node2;
		while(cur != null){
			path.add(0,cur);
			cur = cur.precedent;
		}
		return path;
	}
	
	public static void printPath(ArrayList<Vertex> path){
		for(Vertex v:path){
			System.out.println(v.id);
		}
	}
	/* Uncomment for Unit Test */
	
	public static void main(String[] args){

		Factory factory = new Factory();
		int[] nodes = {1,2,3,4,5,6,7,8,9,10};
		int[][] edges = {{1,2, 2},{2,3},{3,6},{3,4},{4,5},{3,5},{2,6, 5},{5,7},{6,9},{9,8},{7,9}};

		Set<Vertex> vertices = factory.buildVertex(nodes);
		ArrayList<Edge> es = factory.buildEdge(edges, vertices);
		// get the source node
		Vertex v1 = null;
		Vertex v9 = null;
		for(Vertex v:vertices){if(v.id == 1) v1 = v; if(v.id == 9) v9 = v;}
		v1.dist = 0;

		//calculate Dijstras shortestPath
		System.out.println(Bidirectional_Dijstras.bidirect_dijstras(v1, v9, vertices, es));
	}
	
}
