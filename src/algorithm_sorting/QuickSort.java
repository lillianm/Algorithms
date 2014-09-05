package algorithm_sorting;

public class QuickSort {
	public static void quickSort(int[] ar, int start, int end){
		if(start < end){
			int split = quickPartition(ar, start, end);
			quickSort(ar, start, split-1);
			quickSort(ar, split+1, end);
		}
	}
	public static int quickPartition(int[] ar, int start, int end){
		/* can randomly choose pivot and swap it with right element */
		int pivot = ar[end];
		int cur = start;
		int prev = start;
		while(cur<end){
			if(ar[cur]<pivot){
				if(prev!=cur){
					int tmp = ar[prev];
					ar[prev] = ar[cur];
					ar[cur] = tmp;
				}
				prev++;
			}
			cur++;
		}
		/* move pivot to the final position */
		int tmp = ar[end];
		ar[end] = ar[prev];
		ar[prev] = tmp;
		return prev;
	}
	
	
}
