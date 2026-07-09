package patterns.b_twopointers_E1_M4_H0.medium;

import java.util.*;

public class ThreeSum_L15 {
    static class Solution {
        public List<List<Integer>> usingTwoPointers(int[] nums) {
            Arrays.sort(nums);
            Set<List<Integer>> res = new HashSet<>();
            for(int i = 0; i < nums.length; i++){
                int j = i+1;
                int k = nums.length-1;
                while(j<k){
                    int sum = nums[i] + nums[j] + nums[k];
                    if(sum == 0){
                        res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                        j++;
                        k--;
                        while(j<k && nums[j] == nums[j-1]) j++;
                        while(j<k && nums[k] == nums[k+1]) k--;
                    }else if(sum > 0){
                        k--;
                    }else {
                        j++;
                    }
                }
            }
            return new ArrayList<>(res);
        }
        public List<List<Integer>> usingHashing(int[] nums) {
            Set<List<Integer>> ans = new HashSet<>();
            for(int i = 0; i < nums.length; i++){
                Set<Integer> set = new HashSet<>();
                for(int j = i+1; j < nums.length; j++){
                    int k = -(nums[i] + nums[j]); //nums[i]+num[j]+nums[k]==0
                    if(set.contains(k)){
                        List<Integer> list = Arrays.asList(nums[i], nums[j],k);
                        list.sort(Comparator.naturalOrder());
                        ans.add(list);
                    }
                    set.add(nums[j]);
                }
            }
            return new ArrayList<>(ans);
        }

        public List<List<Integer>> threeSum(int[] nums) {
            return usingTwoPointers(nums);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        List<List<Integer>> res = s.threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        System.out.println(res);
    }
}
