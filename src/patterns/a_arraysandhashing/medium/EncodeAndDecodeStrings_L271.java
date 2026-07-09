package patterns.a_arraysandhashing.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeAndDecodeStrings_L271 {
    static class OptimalSolution {

        public String encode(List<String> strs) {
            //Input = ["Hello","World"]
            StringBuilder sb = new StringBuilder();
            for(String str : strs){
                sb.append(str.length()+"#"+str);
            }
            //Output = 5#Hellow5#World
            return sb.toString();
        }

        public List<String> decode(String str) {
            //Input = 5#Hellow5#World
            List<String> result = new ArrayList<>();
            int i=0;
            while(i<str.length()){
                int j = i;
                while(str.charAt(j)!='#'){
                    j++;
                }
                int length = Integer.parseInt(str.substring(i,j));
                i = j+1;
                j = i+length;
                result.add(str.substring(i,j));
                i = j;
            }
            //Output = ["Hello","World"]
            return result;
        }
    }
    static class Solution {
        public String encode(List<String> strs) {
            //Input = ["Hello","World"]
            List<Integer> sizes = new ArrayList<>();
            String temp = "";
            for (String str : strs) {
                sizes.add(str.length());
                temp += str;
            }
            StringBuilder sb = new StringBuilder();
            for (Integer size : sizes) {
                sb.append(size+",");
            }
            sb.append("#");
            sb.append(temp);
            //Output = 5,5#HelloWorld
            return sb.toString();
        }

        public List<String> decode(String str) {
            List<Integer> sizes = new ArrayList<>();
            int i=0;
            while(str.charAt(i)!='#'){
                StringBuilder curStrSize = new StringBuilder();
                while(str.charAt(i)!=','){
                    curStrSize.append(str.charAt(i));
                    i++;
                }
                int length = Integer.decode(curStrSize.toString());
                sizes.add(length);
                i++;
            }
            i++;
            List<String> result = new ArrayList<>();
            for(Integer size : sizes){
                result.add(str.substring(i,i+size));
                i =  i+size;
            }
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String [] input = {"Hello","World"};
        String str = solution.encode(Arrays.asList(input));
        System.out.println("Encode:"+ str);
        System.out.println("Decode:"+ solution.decode(str));

        OptimalSolution optimalSolution = new OptimalSolution();
        String str2 = optimalSolution.encode(Arrays.asList(input));
        System.out.println("Encode:"+ str2);
        System.out.println("Decode:"+ optimalSolution.decode(optimalSolution.encode(Arrays.asList(input))));
    }
}
