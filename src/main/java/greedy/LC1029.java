package greedy;

import java.util.Arrays;

public class LC1029 {
    /**
     Considering that we have two city Ai and Bi, Ai - Bi is the extra money
     that the company gonna pay if they send the employee to city A instead of city B.
     Suppose we have
     A   B   A - B
     20  10   10
     It means that the company will pay 10 extra dollar to send the employee to city A.
     Thus, the smaller A - B is, the more necessary for company to send the employee to
     city A
     */
    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (row1, row2) -> (row1[0] - row1[1]) - (row2[0] - row2[1]));
        int res = 0;
        int half = costs.length  / 2;
        for(int i = 0;i + half < costs.length;i++){
            res += costs[i][0] + costs[i + half][1];
        }
        return res;
    }
}
