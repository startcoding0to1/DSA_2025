package patterns.a_arraysandhashing.easy;

import java.util.HashMap;
import java.util.Map;


public class ValidAnagram_L242 {
    static class Solution {

        public boolean usingHashing(String s, String t) {
            if(s.length() != t.length()){
                return false;
            }
            Map<Character, Integer> sMap = new HashMap<>();
            Map<Character, Integer> tMap = new HashMap<>();
            for(int i=0; i<s.length(); i++){
                char sc = s.charAt(i);
                sMap.put(sc, sMap.getOrDefault(sc,0)+1);
                char tc = t.charAt(i);
                tMap.put(tc, tMap.getOrDefault(tc,0)+1);
            }
            return sMap.equals(tMap);
        }

        public boolean isAnagram(String s, String t) {
            return usingHashing(s, t);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("Is Anagram: " + s.usingHashing("abcd", "abcd"));
    }
}
