package patterns.e_binarysearch.medium;

public class Find_Minimum_In_Rotated_Sorted_Array_L153 {

    static class Solution {

        public int usingBinearySearchOptimal(int[] nums) {
            int l = 0;
            int r = nums.length-1;
            while(l<r){
                int m = l+(r-l)/2;
                if(nums[m]>nums[r]){ //means min present inside right part
                    l = m+1;
                }else{
                    r = m;
                }
            }
            return nums[l];
        }

        public int usingBinearySearch(int[] nums) {
            int l = 0;
            int r = nums.length - 1;
            int res = nums[l];

            while (l <= r) {
                if (nums[l] < nums[r]) { //[4,5,6,7,0,1,2] to hanlde if right side part is sorded
                    return Math.min(res, nums[l]);
                }
                int mid = l + (r - l) / 2;
                res = Math.min(res, nums[mid]);
                if (nums[l] <= nums[mid]) {
                    l = mid + 1; //Confirm this part is sorted we can eleminate and search inside unsorted part
                } else {
                    r = mid - 1; //Confirm this part is sorted we can eleminate and search inside unsorted part
                }
            }

            return res;
        }

        public int findMin(int[] nums) {
            return usingBinearySearch(nums);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        System.out.println(s.usingBinearySearch(new int[]{3,4,5,1,2}));
    }
}
