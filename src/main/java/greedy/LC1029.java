package greedy;

import java.util.Arrays;

public class LC1029 {
    /**
     Considering that we have two city Ai and Bi, Bi - Ai is the extra money
     that the company gonna pay if they send the employee to city B instead of city A
     suppose we have
     A   B   B - A
     10  20    10
     It means that the company will pay 10 extra dollar to send the employee to City B
     */

    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (row1, row2) -> (row1[1] - row1[0]) - (row2[1] - row2[0]));
        int res = 0;
        int half = costs.length / 2;
        for(int i = 0;i < half;i++){
            res += costs[i][1] + costs[half + i][0];
        }
        return res;
    }
}
