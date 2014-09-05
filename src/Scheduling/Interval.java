package Scheduling;

public class Interval {
	public int start;
	public int end;
	public int weight;
	public Interval(int start, int end){
		this.start = start;
		this.end = end;
		this.weight = 1;
	}
	public Interval(int start, int end, int weight){
		this.start = start;
		this.end = end;
		this.weight = weight;
	}
}
