package algorithm_sorting;

import java.util.Random;

public class Test {
	public static void main(String[] args){
		int[] ar = new int[100];
		for(int i = 0;i<100;i++){
			ar[i] = i/2;
		}
		shuffleArray(ar);
		printArray(ar);
		QuickSortWithRepeated.quickSortWithRepeat(ar, 0, ar.length-1);
		printArray(ar);
	}
	// Implementing Fisher–Yates shuffle
	  static void shuffleArray(int[] ar)
	  {
	    Random rnd = new Random();
	    for (int i = ar.length - 1; i > 0; i--)
	    {
	      int index = rnd.nextInt(i + 1);
	      // Simple swap
	      int a = ar[index];
	      ar[index] = ar[i];
	      ar[i] = a;
	    }
	  }
	  
	  static void printArray(int[] ar) {
	         for(int n: ar){
	            System.out.print(n+" ");
	         }
	           System.out.println("");
	      }

}
