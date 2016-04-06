import java.io.*;
import java.util.*;

public class Knapsack {
	
    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
		int testCases;
		Scanner in = new Scanner(System.in);
		System.out.print("Enter the number of test cases: ");
		testCases = in.nextInt();
		int optval[][][] = new int[testCases][][];             	// One n+1 by k+1 matrix for memoization for each testcase
		int sizeSum[][] = new int[testCases][2];				// One 1x2 matrix for number or elements in array and expected sum for each testcase 
		int arr[][] = new int[testCases][];						// One 1xn array for each test case
		
		for(int i = 0;i<testCases;i++){    
			System.out.print("Enter the size and expected sum of " + i + " th array seperated by space: ");
			for(int j=0;j<2;j++){
					sizeSum[i][j] = in.nextInt();
			}
			System.out.println("You entered size =" + sizeSum[i][0]+ " and sum = " + sizeSum[i][1] );
			arr[i] = new int[sizeSum[i][0]];
			optval[i] = new int[sizeSum[i][0]][sizeSum[i][1]];		//Creating memoization array for ith testcase with size as nxk
			System.out.print("Enter the input array for "+ i+"th testcase: ");
			for(int k=0;k<sizeSum[i][0];k++){
			arr[i][k]=in.nextInt();
			}
		}
		
		for(int i = 0;i<testCases;i++){
				optimum(arr[i],sizeSum[i][0]-1,sizeSum[i][1],optval[i]);
				System.out.println("Optimum sum for "+i+" th testcase is :"+optval[i][sizeSum[i][0]-1][sizeSum[i][1]-1]);
			//	for(int j=sizeSum[i][0]-1;j>0;j--){
			//		for(int k = sizeSum[i][1]-1;k>0;k--){
			//				System.out.println("Testcase :"+i+" ,"+j+"th elements,weigth: "+k+": "+optval[i][j][k]);
			//		}
			//	}
		}	
	}
		
	
	public static int max(int a,int b,int c){
		return a>b?(a>c?a:c):b>c?b:c;
		
	}
	
	public static int optimum(int arr[],int last,int weigth,int ans[][]){
		if (last < 0) 											// base case 
			return 0;					
		else if ((weigth - arr[last]) == 0){					//if expected sum or remaining sum reached return ai value
				ans[last][weigth-1] =  weigth;
				return weigth;
			}
		else if (ans[last][weigth-1]!=0)						//use memoized value if already written
			return ans[last][weigth-1];
		else if ((weigth - arr[last]) >0){						//if ai can be added to knapsack make recursive calls
				ans[last][weigth-1] = max(optimum(arr,last-1,weigth,ans),optimum(arr,last-1,weigth-arr[last],ans)+arr[last],optimum(arr,last,weigth-arr[last],ans)+arr[last]);
				return ans[last][weigth-1];	
			}
		else{
				ans[last][weigth-1] = optimum(arr,last-1,weigth,ans);
				return ans[last][weigth-1];
			}			
	}
}