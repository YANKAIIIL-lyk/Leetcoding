package dynamicprogramming;

public class LC256 {
    /**
     * The next level is solely based on the previous level with different color.
     */
    public int minCost(int[][] costs) {
        if(costs == null || costs.length == 0){
            return 0;
        }
        int a = costs[0][0];
        int b = costs[0][1];
        int c = costs[0][2];
        for(int i = 1;i < costs.length;i++){
            int aa = Math.min(b, c) + costs[i][0];
            int bb = Math.min(a, c) + costs[i][1];
            int cc = Math.min(a, b) + costs[i][2];
            a = aa;
            b = bb;
            c = cc;
        }
        return Math.min(a, Math.min(b, c));
    }

    /**
     * DFS is also possible
     */
    public int minCost1(int[][] costs) {
        if(costs == null || costs.length == 0){
            return 0;
        }
        return Math.min(dfs(0, 0, costs), Math.min(dfs(0, 1, costs), dfs(0, 2, costs)));
    }

    private int dfs(int n, int color, int[][] costs){
        int curr = costs[n][color];
        if(n == costs.length - 1){
            return curr;
        }
        if(color == 0){
            return curr + Math.min(dfs(n + 1, 1, costs), dfs(n + 1, 2, costs));
        }else if(color == 1){
            return curr + Math.min(dfs(n + 1, 0, costs), dfs(n + 1, 2, costs));
        }else{
            return curr + Math.min(dfs(n + 1, 1, costs), dfs(n + 1, 2, costs));
        }
    }
}
