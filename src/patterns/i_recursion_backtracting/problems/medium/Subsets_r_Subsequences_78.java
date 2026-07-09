package patterns.i_recursion_backtracting.problems.medium;

import java.util.ArrayList;
import java.util.List;

public class Subsets_r_Subsequences_78 {
    class Solution {

        List<List<Integer>> ans = new ArrayList<>();

        public void solve(int[] nums, int indx, List<Integer> currentSet){
            for(int i=indx; i<nums.length; i++){
                currentSet.add(nums[i]);
                ans.add(new ArrayList<>(currentSet));
                solve(nums, i+1, currentSet);
                currentSet.remove(currentSet.size()-1);
            }
        }
        public List<List<Integer>> subsets(int[] nums) {
            ans.add(new ArrayList<>());
            solve(nums, 0, new ArrayList<>());
            return ans;
        }
    }
}








//
//// Class containing the solution logic
//class Solution {
//    // Recursive helper to find all subsets
//    public void findSubsets(int ind, int[] nums, List<Integer> ds, Set<List<Integer>> result) {
//        // Base case: if all elements are considered, add the subset to the result set
//        if (ind == nums.length) {
//            result.add(new ArrayList<>(ds));
//            return;
//        }
//
//        // Choice 1: Include the current element
//        ds.add(nums[ind]);
//        findSubsets(ind + 1, nums, ds, result);
//        // Backtrack by removing the element to explore the other path
//        ds.remove(ds.size() - 1);
//
//        // Choice 2: Do not include the current element
//        findSubsets(ind + 1, nums, ds, result);
//    }
//
//    // Main function to get all unique subsets
//    public List<List<Integer>> subsetsWithDup(int[] nums) {
//        Set<List<Integer>> result = new HashSet<>();
//        // Sort the array to handle duplicates consistently
//        Arrays.sort(nums);
//        findSubsets(0, nums, new ArrayList<>(), result);
//
//        // Convert the set to a list for the final output
//        return new ArrayList<>(result);
//    }
//}
