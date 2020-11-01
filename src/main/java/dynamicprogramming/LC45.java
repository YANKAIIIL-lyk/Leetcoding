package dynamicprogramming;

import java.util.Arrays;


public class LC45 {
    /**
     *  Check from the back to the front of this array. Integer.MAX_VALUE means that
     *  current position is invalid. Keep finding the minimum count and plus 1.
     */
    public int jump(int[] nums) {
        if(nums == null || nums.length == 0){
            return 0;
        }
        int[] dp = new int[nums.length];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[dp.length - 1] = 0;
        for(int i = dp.length - 2;i >= 0;i--){
            for(int j = 1;j <= nums[i];j++){
                if(i + j >= dp.length - 1){
                    dp[i] = 1;
                    break;
                }else if(dp[i + j] != Integer.MAX_VALUE){
                    dp[i] = Math.min(dp[i], dp[i + j] + 1);
                }
            }
        }
        return dp[0];
    }
}
