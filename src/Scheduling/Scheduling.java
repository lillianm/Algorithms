package Scheduling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

public class Scheduling {
	
	/* Can not use greedy Algorithm as arbitrary weight will let greedy algorithm fail 
	 * Dynamic programming instead */
	public static int weightedScheduling(ArrayList<Interval> intervals){
		/* Sort list by finish time */
		Collections.sort(intervals, new Comparator<Interval>(){

			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.end - o2.end;
			}
			
		});

		int len = intervals.size();
		/* p[i] is the largest index of interval that is compatible with intervals[i]
		 * i.e. intervals[p[i]].end <=  intervals[i].start */
		int[] p = new int[len];
		/* S[i] is the sum of the subset ends at i */
		int[] S = new int[len];
		
				p[0] = 0;
		
		for(int i = 1;i<len;i++){
			p[i] = intervals.get(i).start >= intervals.get(i-1).end ? i-1:p[i-1];
		}
		S[0] = 0;
		for(int i = 1;i<=len;i++){
			S[i] = Math.max(S[i-1] , intervals.get(i).weight + S[p[i]]);
		}
		return S[len-1];
	}
	
	
	
	/* Greedy Algorithm */
	public static Set<Interval> maxSubset(ArrayList<Interval> intervals){
		Collections.sort(intervals, new Comparator<Interval>(){

			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.end - o2.end;
				}
			
		});
		Set<Interval> result = new HashSet<Interval>();
		for(int i = 0;i<intervals.size();i++){
			boolean valid = true;
			for(Interval itr:result){
				if(itr.end > intervals.get(i).start){
					valid = false;
					break;
				}
			}
			if(valid) result.add(intervals.get(i));
		}
		return result;
	}
	
	public static int scheduleAll(ArrayList<Interval> intervals){
		ArrayList<ArrayList<Interval>> rooms = new ArrayList<ArrayList<Interval>>();
		Collections.sort(intervals, new Comparator<Interval>(){

			@Override
			public int compare(Interval o1, Interval o2) {
				return o1.start - o2.start;
			}
			
		});
		
		for(int i = 0;i<intervals.size();i++){
			Interval cur = intervals.get(i);
			boolean found = false;
			for(ArrayList<Interval> room : rooms){
				if(room.isEmpty() || room.get(room.size()-1).end <= cur.start){
					room.add(cur);
					found = true;
					break;
				}
			}
			if(!found){
				ArrayList<Interval> n = new ArrayList<Interval>();
				n.add(cur);
				rooms.add(n);
			}
		}
		return rooms.size();
	}
	public static void main(String[] args){
		Interval i1 = new Interval(1,8);
		Interval i2 = new Interval(2,3);
		Interval i3 = new Interval(4,6);
		Interval i4 = new Interval(5,7);
		Interval i5 = new Interval (6, 8);
		ArrayList<Interval> list = new ArrayList<Interval>();
		list.add(i1);list.add(i2);list.add(i3);list.add(i4);list.add(i5);
		System.out.println(maxSubset(list));
		System.out.println(scheduleAll(list));
	}
	
}
