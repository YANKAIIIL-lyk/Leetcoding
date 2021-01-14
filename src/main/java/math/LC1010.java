package math;

public class LC1010 {
    /**
     * Similar to LC1497, use the remainder
     * Only difference is that we need to consider when t = 0 and t = 30
     */
    public int numPairsDivisibleBy60(int[] time) {
        int[] remainder = new int[60];
        for(int i : time){
            remainder[i % 60]++;
        }
        int res = 0;
        if(remainder[0] > 1){
            res += remainder[0] * (remainder[0] - 1) / 2;
        }
        if(remainder[30] > 1){
            res += remainder[30] * (remainder[30] - 1) / 2;
        }
        for(int i = 1;i <= 29;i++){
            int counti = remainder[i];
            int count60i = remainder[60 - i];
            // int all = counti * count60i;
            res += counti * count60i;
        }
        return res;
    }
}
