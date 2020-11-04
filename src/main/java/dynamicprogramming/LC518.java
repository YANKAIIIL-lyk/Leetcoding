package dynamicprogramming;

public class LC518 {
    /**
     * This is a DP problem. Coin change 2.
     * Can be optimized several times.
     * @param amount
     * @param coins
     *
     * Unlimited knapsack problem, we can pick as much as we want
     */
    /**
     *  Use a dp[][] 2D array. dp[i][j] indicates the number of ways to get amount j
     *  with the first i coins.
     *  Thus dp[i][j] = sum(dp[i - 1][j - coins[i] * k]), k = 0,1,2,3.....
     *  In the dp function, we are only using the first i - 1 coins to form the total amount
     *  by adding coin[i] one by one, we can guarantee that there will be no duplicate.
     *  During the initialization, since we need to use the data of i - 1, we need to manually
     *  compute the cases when we only use one coin. Then, we can do dynamic programming.
     *
     *  Denote length of coins as N and amount as M.
     *  Time complexity:
     *  First for loop will be N
     *  Second for loop will be amount M
     *  For the third for loop, if we are processing 1-dollar coin, it will also be M
     *  So, O(n) = NM^2
     *
     *  Space complexity:
     *  Size of DP array, O(n) = MN
     */
    public int change(int amount, int[] coins) {
        if(amount == 0){
            return 1;
        }
        if(coins == null || coins.length == 0){
            return 0;
        }
        int[][] dp = new int[coins.length][amount + 1];
        for(int j = 0;j < dp[0].length;j++){
            if(j % coins[0] == 0){
                dp[0][j] = 1;
            }
        }
        for(int i = 1;i < dp.length;i++){
            for(int j = 0;j < dp[0].length;j++){
                for(int k = 0;j - k * coins[i] >= 0;k++){
                    dp[i][j] += dp[i - 1][j - k * coins[i]];
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
