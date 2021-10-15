package math;

/**
 * Count all the digits one by one
 */
public class LC233 {
    public int countDigitOne(int n) {
        if(n == 0){
            return 0;
        }
        return getCount(n, 1);
    }

    private int getCount(int n, int k) {
        int power = 1;
        int res = 0;
        for (; power <= n; power = power * 10) {
            int whole = (n / (10 * power)) * power;
            int remain = n % (10 * power);
            int cal = 0;
            if (remain < k * power) {
                cal = 0;
            } else if (remain >= (k + 1) * power) {
                cal = power;
            }else{
                cal = remain - power + 1;
            }
            res += whole + cal;
        }
        return res;
    }
}
