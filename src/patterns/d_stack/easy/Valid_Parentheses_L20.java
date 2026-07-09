package patterns.d_stack.easy;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Valid_Parentheses_L20 {
    static class Solution {

        public boolean usingStringMeths(String s) {
            while (s.contains("()") || s.contains("[]") || s.contains("{}")) {
                s = s.replace("()", "");
                s = s.replace("[]", "");
                s = s.replace("{}", "");
            }
            return s.isEmpty();
        }

        public boolean usingStack(String s){
            Map<Character, Character> map = new HashMap<>();
            map.put(')','(');
            map.put(']','[');
            map.put('}','{');

            Stack<Character> oBS = new Stack<>();
            for(int i=0; i<s.length(); i++){
                char ch = s.charAt(i);
                if(map.containsKey(ch)){
                    if(!oBS.isEmpty() && oBS.peek()==map.get(ch)){
                        oBS.pop();
                    }else{
                        return false;
                    }
                }else{
                    oBS.push(ch);
                }
            }
            return oBS.isEmpty();
        }

        public boolean usingStackOptimal(String s){
            Stack<Character> stack = new Stack<>();
            for(char ch : s.toCharArray()){
                if(ch=='('){
                    stack.push(')');
                }else if(ch=='['){
                    stack.push(']');
                }else if(ch=='{'){
                    stack.push('}');
                }else{
                    if(!stack.isEmpty() && stack.peek()==ch){
                        stack.pop();
                    }else{
                        return false;
                    }
                }
            }
            return stack.isEmpty();
        }

        public boolean isValid(String s) {
            return usingStackOptimal(s);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.isValid("([])"));
    }
}
