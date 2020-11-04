package dynamicprogramming;

import java.util.Arrays;

public class LC322 {
    //Coin Change 1
    /*

        Don't use Integer.MAX_VALUE as placeholder since it may lead to overflow and
        mess up the Math.min() function
     */
    public static int coinChange(int[] coins, int amount) {
        if(coins == null || coins.length == 0 || amount < 0){
            return -1;
        }
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for(int i = 1;i < dp.length;i++){
            for(int j = 0;j < coins.length;j++){
                if(i - coins[j] >= 0){
                    dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
                }
            }
        }
        return dp[dp.length - 1] == amount + 1 ? -1 : dp[dp.length - 1];
    }

    public static void main(String[] args) {
        int[] coins = {2};
        int amount = 3;
        int result = coinChange(coins, amount);
        System.out.println(result);
    }
}
