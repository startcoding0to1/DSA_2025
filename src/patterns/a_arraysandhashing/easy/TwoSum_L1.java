package patterns.a_arraysandhashing.easy;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum_L1 {
    static class Solution {
        public int[] usingHashing(int[] nums, int target){
            Map<Integer, Integer> map = new HashMap<>();
            for(int i=0; i<nums.length; i++){
                int otherElement = target - nums[i];
                if(map.containsKey(otherElement)){
                    return new int[]{i,map.get(otherElement)};
                }
                map.put(nums[i],i);
            }
            return new int[]{};
        }
        public int[] twoSum(int[] nums, int target) {
            return usingHashing(nums, target);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println("Two indices of number: "+Arrays.toString(s.twoSum(new int[]{2, 7, 11, 15}, 9)));
    }
}
