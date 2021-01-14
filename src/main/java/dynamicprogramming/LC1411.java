package dynamicprogramming;

public class LC1411 {
    /**
     * For level n, the count is only related to the level n - 1.
     * So, we only need to discuss the case of (n - 1) to get level n
     */
    public int numOfWays(int n) {
        long aba = 6;
        long abc = 6;
        int constant = 1000000007;
        for(int i = 2;i <= n;i++){
            long newAba = 3 * aba % constant + 2 * abc % constant;
            long newAbc = 2 * aba % constant + 2 * abc % constant;
            aba = newAba;
            abc = newAbc;
        }
        return (int)((aba + abc) % constant);
    }
}
