package patterns.b_twopointers_E1_M4_H0.medium;

public class ContainerWithMostWater_L11 {
    static class Solution {


        public int usingTwoPointerApp(int[] height){
            int left = 0;
            int right = height.length-1;
            int ans = 0;
            while(left<right){
                int areaOfWater;
                if(height[left]<height[right]){
                    areaOfWater = height[left]*(right-left);
                    left++;
                }else{
                    areaOfWater = height[right]*(right-left);
                    right--;
                }
                ans = Math.max(ans, areaOfWater);
            }
            return ans;
        }

        public int usingBruteForceApp(int[] height){ //(Time Limit Exceeded)
            int ans = 0;
            for(int leftP=0; leftP<height.length; leftP++){
                for(int rightP=leftP+1; rightP<height.length; rightP++){
                    int min = Math.min(height[leftP], height[rightP]);
                    int areaOfWater = min*(rightP-leftP);
                    ans = Math.max(ans, areaOfWater);
                }
            }
            return ans;
        }

        public int maxArea(int[] height) {
            return usingTwoPointerApp(height);
        }
    }

    public static void main(String[] args) {
        Solution s = new Solution();
        int[] height = new int[]{1,8,6,2,5,4,8,3,7};
        System.out.println(s.maxArea(height));
    }
}
