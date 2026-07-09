package patterns.l_dynamicprogramming.approaches;

//bottom-up
public class Tabulation {

    //TC = n, SC = n
    public int findNthFibonacciNum(int n){
        int [] table = new int [n+1];
        table[0] = 0;
        table[1] = 1;

        for(int i=2; i<=n; i++){
            table[i] = table[i-1] + table[i-2];
        }

        return table[n];
    }

    //TC=n, Sc=1
    public int optimizedSolution(int n){

        if(n<=1){
            return n;
        }

        int previousPrevious = 0;
        int previous = 1;
        int current = 1;

        for (int i=2; i<=n; i++){
            current =  previous+previousPrevious;
            previousPrevious = previous;
            previous = current;
        }

        return current;
    }

    public static void main(String[] args) {
        int n = 6;
        System.out.println(new Tabulation().optimizedSolution(n));
    }
}
