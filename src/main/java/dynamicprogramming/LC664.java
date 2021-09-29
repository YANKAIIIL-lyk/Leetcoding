package dynamicprogramming;

public class LC664 {
    /**
     * We can either solve by recursion + memorization or dynamic programming
     * Recursion + memorization is more intuitive than another
     */
    /**
     * We need to optimize by each of the intervals. Consider when we are optimizing [i, j]
     * We can always hold out the last character at j. Then memo[i, j] = memo[i, j - 1] + 1
     * At the worst case, we can print all the characters one by one and +1 for the index j.
     * Then, we can keep spliting [i, j - 1] at position k. If char(k) == char(j), then we
     * know we can print char k with the right part of the split.
     * Then, memo[i][j] = memo[i][k - 1] + memo[k + 1][j], when char(k) == char(j).
     * Using recursion with memorization to reduce redundant calculation.
     */
    public int strangePrinter1(String s) {
        int n = s.length();
        int[][] memo = new int[n][n];
        return turn(s.toCharArray(), 0, n - 1, memo);
    }

    private int turn(char[] c, int i, int j, int[][] memo){
        if(i > j){
            return 0;
        }
        if(memo[i][j] != 0){
            return memo[i][j];
        }
        int res = turn(c, i, j - 1, memo) + 1;
        for(int k = i;k < j;k++){
            if(c[k] == c[j]){
                res = Math.min(res, turn(c, i, k - 1, memo) + turn(c, k + 1, j, memo));
            }
        }
        return memo[i][j] = res;
    }

    /**
     * We can also use dynamic programming to solve this question. When the length of the
     * string, the answer should be 1. Then, we can keep increasing the length at optimize
     * each of the intervals. This is 3D-dp. We need to enumerate 3 dimensions.
     * (1) Length of string
     * (2) Starting point
     * (3) Cut among the whole length
     *
     * [i, j] refers to the interval we are processing. For each of the distance len, we
     * can start from i and split at all the position.
     * If the distance is len and starting from i, the ending index should be i + len - 1.
     * We can denote it as j. Similarly, we can have dp[i][j] = dp[i][j - 1] + 1
     * Then, hold out the character at j and cut from [i, j - 1]. At position k, if the
     * character k is the same as j, we can minimize by 1 since k can be printed together
     * with j.
     * dp[i][j] = dp[i][k] + dp[k + 1][j]       For k != j
     *          = dp[i][k] + dp[k + 1][j] - 1   For k == j
     */
    public int strangePrinter(String s) {
        if(s == null || s.length() == 0){
            return 0;
        }
        int n = s.length();
        int[][] dp = new int[n][n];
        for(int i = 0;i < n;i++){
            dp[i][i] = 1;
        }
        for(int len = 2;len <= n;len++){
            for(int i = 0;i + len - 1 < n;i++){
                int j = i + len - 1;
                dp[i][j] = dp[i][j - 1] + 1;
                for(int k = i;k < j;k++){
                    int temp = dp[i][k] + dp[k + 1][j];
                    dp[i][j] = Math.min(dp[i][j], s.charAt(k) == s.charAt(j) ? temp - 1 : temp);
                }
            }
        }
        return dp[0][dp.length - 1];
    }

}
