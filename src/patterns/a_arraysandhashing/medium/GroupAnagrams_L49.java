package patterns.a_arraysandhashing.medium;

import java.util.*;

public class GroupAnagrams_L49 {
    static class Solution {
        public List<List<String>> usingHashing(String[] strs){
            Map<String, List<String>> map = new HashMap<>();
            for(int i=0; i<strs.length; i++){
                char [] charArr = strs[i].toCharArray();
                Arrays.sort(charArr);
                String key = new String(charArr);
                if(!map.containsKey(key)){
                    map.put(key,new ArrayList<>());
                }
                map.get(key).add(strs[i]);
            }
            return new ArrayList<>(map.values());
        }
        public List<List<String>> groupAnagrams(String[] strs) {
            return usingHashing(strs);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("Group Anagrams: "+s.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
    }
}
