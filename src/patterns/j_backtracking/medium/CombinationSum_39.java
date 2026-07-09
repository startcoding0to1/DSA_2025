package patterns.j_backtracking.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSum_39 {

    static class  Solution {

        public void recursiveFun(int[] nums, int i, int target, List<List<Integer>> res, List<Integer> combination) {
            if(i==nums.length){
                return;
            }
            if (target == 0) {
                res.add(new ArrayList<>(combination));
                return;
            }
            int currElement = nums[i];
            if(currElement <= target){
                combination.add(currElement);
                recursiveFun(nums, i,target-currElement, res, combination);
                combination.remove(combination.size()-1);
            }
            recursiveFun(nums, i+1, target, res, combination);
        }

        private List<List<Integer>> solution1(int[] nums, int target) {
            List<List<Integer>> res = new ArrayList<>();
            recursiveFun(nums, 0, target, res, new ArrayList<>());
            return res;
        }

        private void solution2_opt(int[] nums, int target, int index, int total, List<List<Integer>> res, List<Integer> combination) {
            if(index == nums.length){
                return;
            }
            if (target == total) {
                res.add(new ArrayList<>(combination));
                return;
            }
            for(int i=index; i<nums.length; i++){
                if(total+nums[i]>target){ //skip further numbers
                    return;
                }
                combination.add(nums[i]);
                solution2_opt(nums, target, i, total+nums[i], res, combination);
                combination.remove(combination.size()-1);
            }
        }

        public List<List<Integer>> combinationSum(int[] nums, int target) {
//            return solution1(nums, target);
            List<List<Integer>> res = new ArrayList<>();
            Arrays.sort(nums);
            solution2_opt(nums, target, 0, 0, res, new ArrayList<>());
            return res;
        }
    }

    public static void main(String[] args) {
        int[] nums= {2,3,6,7};
        List<List<Integer>> res = new Solution().combinationSum(nums, 7);
        System.out.println(res);
    }
}
