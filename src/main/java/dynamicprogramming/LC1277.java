package dynamicprogramming;

public class LC1277 {
    /**
     *  This one is similar to LC221.
     *  In LC221, the dp matrix indicates the maximum length of the square's edges.
     *  For edge using (i, j) as the botton right corner, the maximum length of edge
     *  should be dp[i][j]
     *  Since for each square within the matrix, they must have a bottom-right corner.
     *  Then, the sum of app elements will have a 1 to 1 mapping to each of the squares.
     *  So, we can sum up all the numbers to get the final result.
     */
    public int countSquares(int[][] matrix) {
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        for(int i = 1;i < dp.length;i++){
            for(int j = 1;j < dp[0].length;j++){
                if(matrix[i - 1][j - 1] == 1){
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        int res = 0;
        for(int[] row : dp){
            for(int i : row){
                res += i;
            }
        }
        return res;
    }
}
