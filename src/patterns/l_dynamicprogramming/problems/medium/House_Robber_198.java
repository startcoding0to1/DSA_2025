package patterns.l_dynamicprogramming.problems.medium;

import java.util.HashMap;
import java.util.Map;

public class House_Robber_198 {

    static class Solution {

        // Cache for memoization
        Map<Integer, Integer> memoization;

        // Top-Down DP (Memoization)
        public int dfs(int[] nums, int i) {

            // No houses left
            if (i >= nums.length) {
                return 0;
            }

            // Return cached result
            if (memoization.containsKey(i)) {
                return memoization.get(i);
            }

            // Pick current house
            int consider = nums[i] + dfs(nums, i + 2);

            // Skip current house
            int skip = dfs(nums, i + 1);

            int val = Math.max(consider, skip);

            memoization.put(i, val);

            return val;
        }

        public int topDownApproach(int[] nums) {
            memoization = new HashMap<>();
            return dfs(nums, 0);
        }

        // Bottom-Up DP (Tabulation)
        public int bottomUpApproach(int[] nums) {

            int n = nums.length;

            if (n == 1) {
                return nums[0];
            }

            // dp[i] = max money that can be robbed from houses [0...i]
            int[] tabulation = new int[n];

            tabulation[0] = nums[0];
            tabulation[1] = Math.max(nums[0], nums[1]);

            for (int i = 2; i < n; i++) {

                // Rob current house
                int consider = nums[i] + tabulation[i - 2];

                // Skip current house
                int skip = tabulation[i - 1];

                tabulation[i] = Math.max(consider, skip);
            }

            return tabulation[n - 1];
        }

        // Bottom-Up DP with O(1) Space
        public int spaceOptimizedDP(int[] nums) {

            int n = nums.length;

            if (n == 1) {
                return nums[0];
            }

            // dp[i-2]
            int previous2 = nums[0];

            // dp[i-1]
            int previous = Math.max(nums[0], nums[1]);

            for (int i = 2; i < n; i++) {

                int consider = nums[i] + previous2;
                int skip = previous;

                int current = Math.max(consider, skip);

                previous2 = previous;
                previous = current;
            }

            return previous;
        }

        public int rob(int[] nums) {
            return spaceOptimizedDP(nums);
        }
    }

    public static void main(String[] args) {

        Solution solution = new Solution();

        int[] nums = {1, 2, 3, 4};

        System.out.println("Top-Down      : " + solution.topDownApproach(nums));
        System.out.println("Bottom-Up     : " + solution.bottomUpApproach(nums));
        System.out.println("SpaceOptimized: " + solution.spaceOptimizedDP(nums));
    }
}