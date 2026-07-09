package patterns.b_twopointers_E1_M4_H0.easy;

public class ValidPalindrome_L125 {
    static class Solution {

        public boolean usingTwoPointers(String s){
            s = s.replaceAll("[^a-zA-Z0-9]","").toLowerCase();;
            int left = 0;
            int right = s.length()-1;
            while(left<right){
                if(s.charAt(left)!=s.charAt(right)){
                    return false;
                }
                left++;
                right--;
            }
            return true;
        }

        public boolean usingTwoPointersOptimal(String s){
            int l = 0;
            int r = s.length()-1;
            while(l<r){
                while(l<r && !Character.isLetterOrDigit(s.charAt(l))){
                    l++;
                }
                while(l<r && !Character.isLetterOrDigit(s.charAt(r))){
                    r--;
                }
                if(Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))){
                    return false;
                }
                l++;
                r--;
            }
            return true;
        }

        public boolean isPalindrome(String s) {
            return usingTwoPointersOptimal(s);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(solution.isPalindrome("A man, a plan, a canal: Panbma"));

    }
}
