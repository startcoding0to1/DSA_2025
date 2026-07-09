package patterns.l_dynamicprogramming.problems.medium;

import java.util.Arrays;

public class House_Robber_II_213 {

    static class Solution {


        public int spaceOptimizedDPDFS(int [] nums, int start, int end){
            int  prev2 = nums[start];
            int prev = Math.max(nums[start], nums[start+1]);

            for(int i=start+2; i<=end; i++){
                int consider = nums[i] + prev2;
                int skip = prev;

                int current = Math.max(consider, skip);
                prev2 = prev;
                prev = current;
            }

            return prev;
        }

        public int spaceOptimizedDP(int [] nums){
            if(nums.length==1){
                return nums[0];
            }
            if(nums.length==2){
                return Math.max(nums[0], nums[1]);
            }
            return Math.max(spaceOptimizedDPDFS(nums,0, nums.length-2),spaceOptimizedDPDFS(nums, 1, nums.length-1));
        }


        public int bottomUpDPDFS(int [] nums, int start, int end){
            int [] tablation = new int [nums.length];
            Arrays.fill(tablation,-1);
            tablation[start] = nums[start];
            tablation[start+1] = Math.max(nums[start], nums[start+1]);
            for(int i=start+2; i<=end; i++){
                int consider = nums[i] + tablation[i-2];
                int skip = tablation[i-1];
                tablation[i] = Math.max(consider, skip);
            }
            return tablation[end];
        }

        public int bottomUpDP(int [] nums){
            if(nums.length==1){
                return nums[0];
            }
            if(nums.length==2){
                return Math.max(nums[0], nums[1]);
            }
            return Math.max(bottomUpDPDFS(nums,0, nums.length-2),bottomUpDPDFS(nums, 1, nums.length-1));
        }

        public int topDownDPDFS(int [] nums, int start, int end, int [] memoization){
            if(start>end){
                return 0;
            }
            if(memoization[start]!=-1){
                return memoization[start];
            }
            int val = Math.max(nums[start]+topDownDPDFS(nums, start+2, end, memoization), topDownDPDFS(nums, start+1, end, memoization));
            memoization[start] = val;
            return val;
        }

        public int topDownDP(int [] nums){
            int [] memoization = new int [nums.length];
            Arrays.fill(memoization,-1);
            return Math.max(topDownDPDFS(nums,0, nums.length-2, memoization),topDownDPDFS(nums, 1, nums.length-1, memoization));
        }

        public int rob(int[] nums) {
//            return spaceOptimizedDP(nums);
//            return bottomUpDP(nums);
            return topDownDP(nums);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums1 = {2, 3, 2};
        System.out.println("Result 1: " + solution.rob(nums1)); // 3

        int[] nums2 = {1, 2, 3, 1};
        System.out.println("Result 2: " + solution.rob(nums2)); // 4

        int[] nums3 = {1, 2, 3};
        System.out.println("Result 3: " + solution.rob(nums3)); // 3

        int[] nums4 = {200, 3, 140, 20, 10};
        System.out.println("Result 4: " + solution.rob(nums4)); // 340
    }
}
