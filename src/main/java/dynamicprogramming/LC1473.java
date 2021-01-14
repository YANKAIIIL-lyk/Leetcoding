package dynamicprogramming;

import java.util.Arrays;

public class LC1473 {
    /**
     * hard
     */
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        int[][][] dp = new int[m][n + 1][target + 1];
        int t = 10000000;
        for(int[][] temp : dp){
            for(int[] row : temp){
                Arrays.fill(row, t);
            }
        }
        if(houses[0] != 0){
            dp[0][houses[0]][1] = 0;
        }else{
            for(int j = 1;j <= n;j++){
                dp[0][j][1] = cost[0][j - 1];
            }
        }
        for(int i = 1;i < m;i++){
            if(houses[i] == 0){
                //Need to paint a color
                for(int j1 = 1;j1 <= n;j1++){
                    //j1 is the color of row i
                    for(int k = 1;k < target + 1;k++){
                        for(int j2 = 1;j2 <= n;j2++){
                            //j2 is the color of row i - 1
                            if(j1 == j2){
                                dp[i][j1][k] = Math.min(dp[i][j1][k], dp[i - 1][j1][k] + cost[i][j1 - 1]);
                            }else{
                                dp[i][j1][k] = Math.min(dp[i][j1][k], dp[i - 1][j2][k - 1] + cost[i][j1 - 1]);
                            }
                        }
                    }
                }
            }else{
                for(int j1 = 1;j1 <= n;j1++){
                    //j1 is the color of last row
                    for(int k = 1;k < target + 1;k++){
                        if(j1 == houses[i]){
                            dp[i][houses[i]][k] = Math.min(dp[i][j1][k], dp[i - 1][j1][k]);
                        }else{
                            dp[i][houses[i]][k] = Math.min(dp[i][houses[i]][k], dp[i - 1][j1][k - 1]);
                        }
                    }
                }
            }
        }
        int res = t;
        for(int j = 1;j <= n;j++){
            res = Math.min(res, dp[m - 1][j][target]);
        }
        return res == t ? -1 : res;
    }
}
