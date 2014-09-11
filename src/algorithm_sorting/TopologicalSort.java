package algorithm_sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import algorithm_Graph.GraphException;

public class TopologicalSort {
	
	public static List<Character> topologicalSort(ArrayList<char[]> edges){

		HashMap<Character, HashSet<Character>> in  = new HashMap<Character, HashSet<Character>>();
		HashMap<Character, HashSet<Character>> out  = new HashMap<Character, HashSet<Character>>();
		HashSet<Character> nodes = new HashSet<Character>();

		for(char[] edge:edges){
			if(in.containsKey(edge[1])){
				in.get(edge[1]).add(edge[0]);
			}
			else{
				HashSet<Character> set = new HashSet<Character>();
				set.add(edge[0]);
				in.put(edge[1],set);
			}

			if(out.containsKey(edge[0])){
				out.get(edge[0]).add(edge[1]);
			}
			else{
				HashSet<Character> set = new HashSet<Character>();
				set.add(edge[1]);
				out.put(edge[0],set);
			}

			if(!nodes.contains(edge[0])){nodes.add(edge[0]);}
			if(!nodes.contains(edge[1])){nodes.add(edge[1]);}

		}

		ArrayList<Character> result =new ArrayList<Character>();
		HashSet<Character> tmp = new HashSet<Character>(nodes);
		if(in.size() == nodes.size()){
			try {
				// Cycle Detected
				throw new GraphException(0);
			} catch (GraphException e) {
				e.printStackTrace();
			}
		}
		while(!nodes.isEmpty()){
			for(Character node:nodes){
				if(!in.containsKey(node) || in.get(node).size() == 0){
					result.add(node);
					if(out.containsKey(node)){
						for(Character neighbor: out.get(node)){
							in.get(neighbor).remove(node);
							if(in.get(neighbor).size() == 0)
								in.remove(neighbor);
						}
					}
					tmp.remove(node);
					break;
				}
			}

			nodes = tmp;
		}
		return result;
	}

	public static void main(String[] args){
		ArrayList<char[]> input = new ArrayList<char[]>();
		char[][] a = {{'a','b'},{'b','c'},{'a','c'},{'d','c'},{'d','b'},{'a','d'},{'c','e'},{'f','d'},{'f','a'},{'e','g'}};
		for(int i = 0;i<a.length;i++){
			input.add(a[i]);
		}
		System.out.println(topologicalSort(input));

	}
}
