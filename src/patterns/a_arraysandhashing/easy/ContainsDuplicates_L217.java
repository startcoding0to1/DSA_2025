package patterns.a_arraysandhashing.easy;

import java.util.HashSet;
import java.util.Set;


public class ContainsDuplicates_L217 {
    static class Solution {

        public boolean usingHashing(int[] nums){
            Set<Integer> set = new HashSet<>();
            for(int i=0; i<nums.length; i++){
                if(set.contains(nums[i])){
                    return true;
                }
                set.add(nums[i]);
            }
            return false;
        }

        public boolean containsDuplicate(int[] nums) {
            return usingHashing(nums);
        }
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        int [] inputArray = {1,2,3,4,5,6,7,8,9,10,1};
        System.out.println("Contains Duplicates: "+sol.containsDuplicate(inputArray));
    }
}
