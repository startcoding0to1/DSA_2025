package patterns.a_arraysandhashing.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LongestConsecutiveSequence_L128 {
    static class Solution {

        public int usingSorting(int [] nums){
            if(nums.length == 0){
                return 0;
            }
            Arrays.sort(nums);
            int currCount = 1;
            int longestSubsequence = 1;
            for(int i = 1; i < nums.length; i++){
                if(nums[i]==nums[i-1]){
                    continue;
                }else if(nums[i]==nums[i-1]+1){
                    currCount++;
                }else{
                    currCount = 1;
                }
                longestSubsequence = Math.max(longestSubsequence, currCount);
            }
            return longestSubsequence;
        }

        public int usingHasSet(int [] nums){
            int longestSubsequence = 0;
            Set<Integer> set = new HashSet<>();
            for(int i = 0; i < nums.length; i++){
                set.add(nums[i]);
            }
            Iterator<Integer> iterator = set.iterator();
            while (iterator.hasNext()){
                int curr = iterator.next();
                if(!set.contains(curr-1)){
                    int currCount = 1;
                    while(set.contains(curr+1)){
                        currCount++;
                        curr = curr+1;
                    }
                    longestSubsequence = Math.max(longestSubsequence, currCount);
                }
            }
            return longestSubsequence;
        }

        public int longestConsecutive(int[] nums) {
            return usingHasSet(nums);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int []  nums = {1,2,3,4,6};
        System.out.println("Longest Consecutive Sequence: "+solution.longestConsecutive(nums));
    }
}
