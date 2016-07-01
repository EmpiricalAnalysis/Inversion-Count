import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class InvCount {
	
	static long CountSplitInv(int[] both, int firstLength) {
		int[] B = new int[firstLength];
		int[] C = new int[both.length - firstLength];
		System.arraycopy(both, 0, B, 0, B.length);
		System.arraycopy(both, firstLength, C, 0, C.length);
		
		int i = 0, j = 0;
		long nInv = 0;

		/*
		System.out.println("B Length = "+B.length);
		System.out.println("C Length = "+C.length);

		for (int m = 0; m < B.length; m++) {
			System.out.println("B["+m+"] = "+B[m]);
		}

		for (int m = 0; m < C.length; m++) {
			System.out.println("C["+m+"] = "+C[m]);
		}
		*/
		
		for (int k = 0; k < B.length + C.length; k++) {
//			System.out.println("i = "+i+", j = "+j);
			
			if (j >= C.length) {
				both[k] = B[i];
				i++;
			}
			else if (i >= B.length) {
				both[k] = C[j];
				j++;
			}
			else if (B[i] <= C[j]) {
				both[k] = B[i];
				i++;
			}
			else {
				both[k] = C[j];
				j++;
				nInv = nInv + B.length - i;
			}
		}

		return nInv;
	}
	
	
	static long SortCount(int[] A, int length) {
		
		int firstLength, secondLength;
		int[] firstHalf, secondHalf;
		long firstNInv, secondNInv, NSplitInv;
		
		if (A.length == 1) {
			//System.out.println("Array A = "+A[0]+" has length 1");
			return 0;
		}
		else {
//			System.out.println("length = "+length);
//			System.out.println("ALength = "+A.length);
			
			firstLength = (A.length) / 2;
			secondLength = A.length - firstLength;
			
//			System.out.println("1stLength = "+firstLength);
//			System.out.println("2ndLength = "+secondLength);
			
			firstHalf = new int[firstLength];
			for (int i = 0; i < firstLength; i++) {
				firstHalf[i] = A[i];
				//System.out.println(firstHalf[i]);
			}
			
			secondHalf = new int[secondLength];
			for (int i = 0; i < secondLength; i++) {
				secondHalf[i] = A[firstLength + i];
				//System.out.println(secondHalf[i]);
			}		
			
			firstNInv = SortCount(firstHalf, firstLength);
			//System.out.println("1st NInv = "+firstNInv);
			secondNInv = SortCount(secondHalf, secondLength);
			//System.out.println("2nd NInv = "+secondNInv);
			
			int[] both = new int[firstLength + secondLength];
			System.arraycopy(firstHalf, 0, both, 0, firstLength);
			System.arraycopy(secondHalf, 0, both, firstLength, secondLength);
		
			NSplitInv = CountSplitInv(both, firstLength);
			/*
			System.out.println("Count Split NInv = "+NSplitInv);
			
			System.out.println("here comes content of both:");			
			for (int p = 0; p < both.length; p++) {
				System.out.println(both[p]);
			}	
			System.out.println("===================================");
			*/
			for (int n = 0; n < A.length; n++) {
				A[n] = both[n];
			}
		
			return firstNInv + secondNInv + NSplitInv;		
		
		}
		
	}
	
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
//		int[] arr = {1, 5, 3};
//		int[] arr = {1, 3, 5, 2, 4, 6};		=> 3
//		int[] arr = {1, 5, 3, 2, 4, 6}; 	=> 4

//		int[] arr = {9, 5, 6, 3, 2, 4, 8, 1, 7, 0};		=> 31
		
		Scanner scanner = new Scanner(new File("IntegerArray.txt"));
		int [] arr = new int [100000];
		int i = 0;
		while(scanner.hasNextInt()){
		   arr[i++] = scanner.nextInt();
		}
		
//		System.out.println("the first number is "+arr[0]);	
		
		int len = arr.length;
		//System.out.println("------------------------------");	
		System.out.println(SortCount(arr, len));	
		

	}

}
