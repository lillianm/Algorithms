package algorithm_sorting;

import java.util.Random;

public class RadixSort {
	public static int[] radixSort(int[] a){
		int base = 10;
		int len = a.length;
		int[] bucket = new int[base];
		int[] tmp = new int[len];
		int maxVal = Integer.MIN_VALUE;
		int mask = 1;
		for(int i = 0;i<len;i++){
			maxVal = Math.max(a[i], maxVal);
		}
		while(maxVal/mask >0){
			tmp = new int[len];
			bucket = new int[base];
			for(int i =0;i<len;i++){
				int index = (a[i]/mask)%10;
				bucket[index] ++;
			}
			for(int i =1;i<base;i++){
				bucket[i] += bucket[i-1];
			}
			for(int j = len-1;j>=0;j--){
				int index = (a[j]/mask)%10;
				tmp[--bucket[index]] = a[j];
			}
			mask = mask*base;
			a = tmp;
						
		}
		return tmp;
	}
	public static void main(String[] args){

		int[] ar = new int[100];
		for(int i = 0;i<100;i++){
			ar[i] = (i*i+1)/2;
		}
		Test.shuffleArray(ar);
		Test.printArray(ar);
		ar = RadixSort.radixSort(ar);
		Test.printArray(ar);
	}


		

}
