package algorithm_Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Test {
	/* Factory is used to build vertex and edge from array input */ 
	public static class Factory{
		public Set<Vertex> buildVertex(int[] nodes){
			Set<Vertex> vs = new HashSet<Vertex>();
			for(int i = 0;i<nodes.length;i++){
				Vertex nv = new Vertex(nodes[i]);
				vs.add(nv);
			}
			return vs;
		}
		
		public ArrayList<Edge> buildEdge(int[][] edges,Set<Vertex> nodes){
			ArrayList<Edge> es = new ArrayList<Edge>();
			for(int i = 0;i<edges.length;i++){
				int n1 = edges[i][0];
				int n2 = edges[i][1];
				int weight = edges[i].length <3? 1:edges[i][2];
				Vertex v1 = null;
				Vertex v2 = null;
				for(Vertex v:nodes){
					if(v.id == n1){v1 = v;}
					if(v.id == n2){v2 = v;}
					if(v1!=null && v2!=null){break;}
				}
				if(v1 == null){v1 = new Vertex(n1); nodes.add(v1);}
				if(v2 == null){v2 = new Vertex(n2); nodes.add(v2);}
				Edge edge = new Edge(v1,v2,weight);
				v1.edges.add(edge);
				v2.edges.add(edge);
				es.add(edge);
				
			}
			return es;
		}
	}
	public static void main(String[] args){
		
		Factory factory = new Factory();
		int[] nodes = {1,2,3,4,5,6,7,8,9,10};
		int[][] edges = {{1,2},{2,3},{3,6},{3,4},{4,5},{3,5},{2,6},{5,7},{6,9},{9,8},{7,9}};
		
		Set<Vertex> vertices = factory.buildVertex(nodes);
		ArrayList<Edge> es = factory.buildEdge(edges, vertices);
		/* get the source node*/
		Vertex v1 = null;
		Vertex v2 = null;
		for(Vertex v:vertices){if(v.id == 1) v1 = v; if(v.id == 2) v2 = v;}
		v1.dist = 0;
		v2.edges.get(1).weight = -1;
		/* calculate Dijstras shortestPath*/
		try {
			Dijstras.shortestPath(v1, vertices,es);
			for(Vertex v:vertices){
				System.out.println(v.dist);
			}

		} catch (GraphException e){}
			}
	
}
