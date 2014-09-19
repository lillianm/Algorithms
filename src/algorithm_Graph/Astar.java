package algorithm_Graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import algorithm_Graph.Test.Factory;

public class Astar {
		public static void bidirectionalAstart(Set<Vertex> nodes, ArrayList<Edge> edges, Vertex source, Vertex target){
			Comparator<Vertex> pqComparator = new Comparator<Vertex>(){

				@Override
				public int compare(Vertex o1, Vertex o2) {
					return o1.dist + o1.heuristic - (o2.dist + o2.heuristic);				}
				
			};

			PriorityQueue<Vertex> pqSource = new PriorityQueue<Vertex>(nodes.size(), pqComparator);
			PriorityQueue<Vertex> pqTarget = new PriorityQueue<Vertex>(nodes.size(), pqComparator);
			Set<Vertex> settled_source = new HashSet<Vertex>();
			Set<Vertex> settled_target = new HashSet<Vertex>();
			while(!pqSource.isEmpty() && pqTarget.isEmpty()){
				Vertex v_source = pqSource.poll();
				for(Edge e:v_source.edges){
					Vertex neighbor = e.getTarget(v_source);
					if(settled_target.contains(neighbor)){
						
					}
									}
			}
		}
			
	
	public static void astar(Set<Vertex> nodes, ArrayList<Edge> edges, Vertex source, int epsilon){
		
		int size = nodes.size();
		HashMap<Vertex, Vertex> precedent  = new HashMap<Vertex, Vertex>();
		PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>(size, new Comparator<Vertex>(){
			@Override
			public int compare(Vertex o1, Vertex o2){
				return o1.dist+o1.heuristic - (o2.dist+o2.heuristic);
			}
		});
		pq.addAll(nodes);
		source.dist = 0;
		
		Set<Vertex> settled = new HashSet<Vertex>();
		while(settled.size()<size){
			Vertex current = pq.poll();
			for(Edge e:current.edges){
				Vertex neighbor = e.getTarget(current);
				if(settled.contains(neighbor)) continue;
				if(current.dist + e.weight < neighbor.dist){
					neighbor.dist = current.dist+e.weight;
					precedent.put(neighbor, current);
				}
			}
			settled.add(current);
		}
		
	}
	
	public static ArrayList<Vertex> reconstructPath(HashMap<Vertex, Vertex> precedent, Vertex source, Vertex target){
		ArrayList<Vertex> path = new ArrayList<Vertex>();
		Vertex cur = target;
		while(path.size() <= precedent.size()){
			path.add(cur);
			cur = precedent.get(cur);
		}
		path.add(source);
		return path;
	}
	public static void main(String[] args){
		Factory factory = new Factory();
		int[] nodes = {1,2,3,4,5,6,7,8,9,10};
		int[] heuristics = {7,100,2,3,5,6,1,1,19,10};
		int[][] edges = {{1,2},{2,3},{3,6},{3,4},{4,5},{3,5},{2,6},{5,7},{6,9},{9,8},{7,9}};
		
		Set<Vertex> vertices = factory.buildVertex(nodes, heuristics);
		ArrayList<Edge> es = factory.buildEdge(edges, vertices);
		/* get the source node*/
		Vertex v1 = null;
		Vertex v2 = null;
		for(Vertex v:vertices){if(v.id == 1) v1 = v; if(v.id == 2) v2 = v;}
		v1.dist = 0;
		v2.edges.get(1).weight = -1;
				
		//astar(vertices, es, v1);
	}
}
