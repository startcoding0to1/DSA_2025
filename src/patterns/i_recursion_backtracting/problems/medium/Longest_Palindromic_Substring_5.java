package patterns.i_recursion_backtracting.problems.medium;

public class Longest_Palindromic_Substring_5 {

    static class Solution {

        // Time Complexity: O(n)
        // Space Complexity: O(n)
        public int[] manacherImpl(String s) {
            StringBuilder sb = new StringBuilder("#");
            for (char ch : s.toCharArray()) {
                sb.append(ch).append("#");
            }

            int n = sb.length();
            int l = 0, r = 0;
            int[] p = new int[n];

            for (int i = 0; i < n; i++) {
                p[i] = (i < r) ? Math.min(r - i, p[l + (r - i)]) : 0;

                while (i + p[i] + 1 < n &&
                        i - p[i] - 1 >= 0 &&
                        sb.charAt(i + p[i] + 1) == sb.charAt(i - p[i] - 1)) {
                    p[i]++;
                }

                if (i + p[i] > r) {
                    l = i - p[i];
                    r = i + p[i];
                }
            }

            return p;
        }

        // Time Complexity: O(n)
        // Space Complexity: O(n)
        public String manacher(String s) {
            int[] p = manacherImpl(s);

            int resLen = 0;
            int centerIdx = 0;

            for (int i = 0; i < p.length; i++) {
                if (p[i] > resLen) {
                    resLen = p[i];
                    centerIdx = i;
                }
            }

            int resIdx = (centerIdx - resLen) / 2;
            return s.substring(resIdx, resIdx + resLen);
        }

        // Time Complexity: O(n²)
        // Space Complexity: O(1)
        public String usingTwoPointers(String s) {
            int resInd = 0, resLen = 0;
            int n = s.length();

            for (int i = 0; i < n; i++) {

                // Odd length palindrome (e.g., "abcba")
                int l = i;
                int r = i;

                while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                    if (resLen < (r - l + 1)) {
                        resLen = r - l + 1;
                        resInd = l;
                    }
                    l--;
                    r++;
                }

                // Even length palindrome (e.g., "abba")
                l = i;
                r = i + 1;

                while (l >= 0 && r < n && s.charAt(l) == s.charAt(r)) {
                    if (resLen < (r - l + 1)) {
                        resLen = r - l + 1;
                        resInd = l;
                    }
                    l--;
                    r++;
                }
            }

            return s.substring(resInd, resInd + resLen);
        }

        // Time Complexity: O(n²)
        // Space Complexity: O(n²)
        public String usingBottomUPDP(String s) {
            int n = s.length();
            int resLen = 0;
            int resInd = 0;

            boolean[][] dp = new boolean[n][n];

            for (int i = n - 1; i >= 0; i--) {
                for (int j = i; j < n; j++) {

                    if (s.charAt(i) == s.charAt(j) &&
                            ((j - i <= 2) || dp[i + 1][j - 1])) {

                        dp[i][j] = true;

                        if (resLen < (j - i + 1)) {
                            resLen = j - i + 1;
                            resInd = i;
                        }
                    }
                }
            }

            return s.substring(resInd, resInd + resLen);
        }

        // Time Complexity: O(n³)
        // Space Complexity: O(1)
        public String usingBruteForce(String s) {
            int resLen = 0;
            String res = "";

            for (int i = 0; i < s.length(); i++) {
                for (int j = i; j < s.length(); j++) {

                    int l = i;
                    int r = j;

                    while (l < r && s.charAt(l) == s.charAt(r)) {
                        l++;
                        r--;
                    }

                    if (l >= r && resLen < (j - i + 1)) {
                        resLen = j - i + 1;
                        res = s.substring(i, j + 1);
                    }
                }
            }

            return res;
        }

        // Time Complexity: O(n)
        // Space Complexity: O(n)
        public String longestPalindrome(String s) {
            // return usingBruteForce(s);
            // return usingBottomUPDP(s);
            // return usingTwoPointers(s);
            return manacher(s);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        String[] testCases = {
                "babad",
                "cbbd",
                "a",
                "ac",
                "abba",
                "racecar",
                "forgeeksskeegfor",
                "aaaa",
                "abcde"
        };

        for (String s : testCases) {
            System.out.println("Input : " + s);
            System.out.println("Output: " + solution.longestPalindrome(s));
            System.out.println("--------------------------------");
        }
    }
}