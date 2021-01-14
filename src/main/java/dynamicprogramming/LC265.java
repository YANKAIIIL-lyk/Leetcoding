package dynamicprogramming;

public class LC265 {
    /**
     * O(n*k^2) solution
     * For each house n, the cost is only related to house(n - 1). Thus, we can use dp to solve the problem.
     * prev is holding whatever the last row is holding right now. So we have O(n) here
     * Then, for each of the following rows, we need to calculate each color one by one. During the calculation,
     * we need to scan the last row since we need to find out the minimum number. So we have O(k^2) here.
     * Thus, the time complexity is O(nk^2)
     */
    public int minCostII(int[][] costs) {
        if(costs == null || costs.length == 0){
            return 0;
        }
        int[] prev = new int[costs[0].length];
        for(int i = 0;i < prev.length;i++){
            prev[i] = costs[0][i];
        }
        for(int i = 1;i < costs.length;i++){
            int[] nextLevel = new int[prev.length];
            for(int k = 0;k < prev.length;k++){
                int value = costs[i][k];
                int min = Integer.MAX_VALUE;
                for(int j = 0;j < prev.length;j++){
                    if(j == k){
                        continue;
                    }
                    min = Math.min(prev[j], min);
                }
                nextLevel[k] = value + min;
            }
            prev = nextLevel;
        }
        int res = Integer.MAX_VALUE;
        for(int i : prev){
            res = Math.min(res, i);
        }
        return res;
    }

    /**
     * Only need to track the minimum and second minimum of the last row.
     */
}
