package patterns.divideandconquer;

public class MaximumSubArraySum {
    /*
     * ============================
     * DIVIDE AND CONQUER APPROACH
     * ============================
     *
     * Idea:
     * -----
     * For any subarray [left..right], the maximum subarray sum can be:
     * 1️⃣ Entirely in the left half
     * 2️⃣ Entirely in the right half
     * 3️⃣ Crossing the mid (some part in left + some part in right)
     *
     * We recursively compute all three and take the maximum.
     *
     * Time Complexity: O(n log n)
     * Space Complexity: O(log n) due to recursion stack
     */
    public int divideAndConquer(int[] nums, int left, int right) {

        // Base case: only one element → that element itself is the max sum
        if (left == right) {
            return nums[left];
        }

        // Find mid index safely (avoids overflow)
        int mid = left + (right - left) / 2;

        // Case 1: Max subarray lies completely in left half
        int maxValFromLeft = divideAndConquer(nums, left, mid);

        // Case 2: Max subarray lies completely in right half
        int maxValFromRight = divideAndConquer(nums, mid + 1, right);

        // Case 3: Max subarray crosses the mid
        int maxValByCrossingMid =
                calculateCrossMidVal(nums, left, mid, right);

        // Return the best of all three cases
        return Math.max(
                maxValByCrossingMid,
                Math.max(maxValFromLeft, maxValFromRight)
        );
    }

    /*
     * Calculates maximum subarray sum that crosses the mid index
     *
     * Strategy:
     * ---------
     * 1️⃣ From mid → left, keep adding elements and track maximum sum
     * 2️⃣ From mid+1 → right, keep adding elements and track maximum sum
     * 3️⃣ Combine both (must include mid and mid+1)
     *
     * This guarantees the subarray crosses mid.
     */
    public int calculateCrossMidVal(int[] nums, int left, int mid, int right) {

        int sum = 0;
        int leftMaxSum = Integer.MIN_VALUE;

        // Traverse left side from mid to left
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            leftMaxSum = Math.max(leftMaxSum, sum);
        }

        sum = 0;
        int rightMaxSum = Integer.MIN_VALUE;

        // Traverse right side from mid+1 to right
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            rightMaxSum = Math.max(rightMaxSum, sum);
        }

        // Combine both sides
        return leftMaxSum + rightMaxSum;
    }

    /*
     * ============================
     * KADANE'S ALGORITHM
     * (You named it knapSackAlgo, but logically this is Kadane)
     * ============================
     *
     * Idea:
     * -----
     * At every index, decide:
     * - Extend the previous subarray
     * - OR start a new subarray from current element
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1)
     */
    public int knapSackAlgo(int[] nums) {

        // currentSum → best subarray ending at current index
        // maxSum     → global maximum subarray sum so far
        int currentSum = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < nums.length; i++) {

            // Either extend previous subarray or start new one
            currentSum = Math.max(currentSum + nums[i], nums[i]);

            // Update global maximum
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    /*
     * Entry method called by LeetCode
     *
     * Prefer Kadane (O(n)) over Divide & Conquer (O(n log n))
     */
    public int maxSubArray(int[] nums) {

        // Divide & Conquer solution
        // return divideAndConquer(nums, 0, nums.length - 1);

        // Optimized Kadane’s Algorithm
        return knapSackAlgo(nums);
    }
}
