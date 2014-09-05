package algorithm_sorting;

public class QuickSortWithRepeated {
	public static void quickSortWithRepeat(int[] ar, int start, int end){
		if(start<end){
			int[] index = quickPartitionWithRepeat( ar, start, end);
				quickSortWithRepeat(ar, start, index[0]-1);
				quickSortWithRepeat(ar, index[1]+1, end);
			
		}
	}
	public static int[] quickPartitionWithRepeat(int[] ar, int start, int end){
		int i = start, j = start, n = end-1;
		int pivot = ar[end];
		while(j<n){
			if(ar[j] < pivot){
				int tmp = ar[i];
				ar[i] = ar[j];
				ar[j] = tmp;
				i++;j++;
			}
			else{
				if(ar[j]>pivot){
					int tmp = ar[j];
					ar[j] = ar[n];
					ar[n] = tmp;
					n--;
				}
				else{
					j++;
				}
			}
		}
		int tmp = ar[end];
		ar[end] = ar[n];
		ar[n]  = tmp;
		int[] result = {i, n};
		
		return result;
	}
}
