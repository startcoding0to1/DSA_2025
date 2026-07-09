package patterns.l_dynamicprogramming.problems;

import java.util.Arrays;

public class UniquePaths_LeetCode62 {

    public int findUniquePathsUsingRecursion2(int m, int n){
        if(m==1 || n==1){
            return 1;
        }
        return findUniquePathsUsingRecursion2(m-1, n)+findUniquePathsUsingRecursion2(m, n-1);
    }

    public int findUniquePathsUsingRecursion2AndDP(int m, int n, int [][] memoization){
        if(m==0 || n==0){
            return 1;
        }
        if (memoization[m][n] == 0){
            memoization[m][n] = findUniquePathsUsingRecursion2AndDP(m-1, n, memoization)+findUniquePathsUsingRecursion2AndDP(m, n-1, memoization);
        }
        return memoization[m][n];
    }

    public int findUniquePaths1(int m, int n){
        int [] [] dpMatrix = new int [m][n];
        for(int j=0;j<m;j++){
            dpMatrix[0][j] = 1;
        }
        for(int j=1;j<n;j++){
            dpMatrix[j][0] = 1;
        }
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                dpMatrix[i][j] = dpMatrix[i-1][j]+dpMatrix[i][j-1];
            }
        }
        return dpMatrix[m-1][n-1];
    }

    //⚡ Space Optimized Java (1D DP) — Interview Bonus
    public int findUniquePaths1_so(int m, int n){
        int [] dp =  new int [n];
        Arrays.fill(dp, 1);

        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                dp[j] = dp[j-1]+dp[j];
            }
        }
        return dp[n-1];
    }

    public static void main(String[] args) {
        int row = 3;
        int column = 3;
        UniquePaths_LeetCode62 solution = new UniquePaths_LeetCode62();
        System.out.println("findUniquePaths1: "+solution.findUniquePaths1(row,column));
        System.out.println("findUniquePathsUsingRecursion2: "+solution.findUniquePathsUsingRecursion2(row,column));
        System.out.println("findUniquePathsUsingRecursion2AndDP: "+solution.findUniquePathsUsingRecursion2AndDP(row-1,column-1,new int[row][column]));
        System.out.println("findUniquePaths1_so: "+solution.findUniquePaths1(row,column));
    }
}
