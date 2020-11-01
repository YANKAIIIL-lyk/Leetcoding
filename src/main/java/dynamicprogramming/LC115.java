package dynamicprogramming;

public class LC115 {
    /**
     * This question is looking for how many subsequences of s can match t. We can consider t as the pattern string
     * and s as the source string. We need to find out all subsequences of s that can match t.
     * Thus, we can use dynamic programming to break down this question. Consider a 2D array dp, at dp[i][j] is the
     * count of all ways to match the first i characters and j characters in string s and t.
     * If the character at i(in string s) is the same as the character at j(in string t). Then, we can either add
     * s.charAt(i) into the matching, or we can skip it. If we add the character at i, then the ith character in
     * string s is matched to the jth character in string t. dp[i][j] = dp[i - 1][j - 1]. If we don't add character
     * at i to the matching, then all the subsequences will be dp[i - 1][j].
     * Similarly, we can find the equation for s.charAt(i) != t.charAt(j).
     * The element at bottom-right will be the answer.
     */
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for(int i = 0;i < dp.length;i++){
            dp[i][0] = 1;
        }
        for(int i = 1;i < dp.length;i++){
            for(int j = 1;j <= i && j <= t.length();j++){
                if(s.charAt(i - 1) == t.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j];
                }else{
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
