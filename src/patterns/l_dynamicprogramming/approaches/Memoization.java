package patterns.l_dynamicprogramming.approaches;

import java.util.Arrays;


/*
* To achieve this in our example we simply take an memo array initialized to -1. As we make a recursive call,
* we first check if the value stored in the memo array corresponding to that position is -1. The value - 1 indicates
* that we haven't calculated it yet and have to recursively compute it. The output must be stored in the memo array so that,
* next time, if the same value is encountered, it can be directly used from the memo array.
 * */

//Top-down
public class Memoization {

    public int recursiveFun(int n, int [] memo){
        if(n<=1){
            return n;
        }
        if(memo[n] != -1){
            return memo[n];
        }
        memo[n] = recursiveFun(n-1,memo)+recursiveFun(n-2,memo);
        return memo[n];
    }

    public int findNthFibonacciNum(int n){
        int [] memo = new int[n+1];
        Arrays.fill(memo, -1);
        return recursiveFun(n, memo);
    }

    public static void main(String[] args) {
        int n = 6;
        System.out.println(new Memoization().findNthFibonacciNum(n));
    }
}
