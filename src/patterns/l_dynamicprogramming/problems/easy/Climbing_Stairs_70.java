package patterns.l_dynamicprogramming.problems.easy;

import java.util.Arrays;

public class Climbing_Stairs_70 { //Hint Fibonacci series

    class Solution {

        public int usingRecursion(int n){
            if(n<=1){
                return 1;

            }
            return usingRecursion(n-2)+usingRecursion(n-1);
        }

//        Using DP      //
        public int topDown(int n, int [] memoization){ //recursion + memoization
            if(n<=1){
                return 1;
            }
            if(memoization[n]!=0){
                return memoization[n];
            }
            memoization[n] = topDown(n-1, memoization)+topDown(n-2, memoization);
            return memoization[n];
        }

        public int bottomUp(int n){
            int [] res = new int [n+1];
            res[0] = 1;
            res[1] = 1;
            for(int i=2;i<=n;i++){
                res[i] = res[i-1] + res[i-2];
            }
            return res[n];
        }

        public int usingDP(int n){
//            return topDown(n, new int[n+1]);
            return bottomUp(n);
        }

        public int spaceOptimized(int n){
            int previous1 = 1; // 0
            int previous2 = 1; // 1
            for(int i=2; i<=n; i++){
                int current =  previous1+previous2;
                previous2 = previous1;
                previous1 = current;
            }
            return previous1;
        }

        public int climbStairs(int n) {
//            return usingRecursion(n);
//            return usingDP(n);
            return spaceOptimized(n);
        }
    }

    public static void main(String[] args) {
        int n = 4;
        Climbing_Stairs_70 obj = new Climbing_Stairs_70();
        Solution solution = obj.new Solution();
        int result = solution.climbStairs(n);
        System.out.println(result);
    }
}
