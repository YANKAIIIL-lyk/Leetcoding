package math;

public class LC1497 {
    /**
     * Calculate the remaining, suppose we have remaining a and b, it should add up to k and
     * the count of a and b should be the same.
     * The count of remaining 0 should be an even number to form pairs.
     */
    public boolean canArrange(int[] arr, int k) {
        if(k == 0){
            return false;
        }
        int[] count = new int[k];
        for(int i : arr){
            count[((i % k) + k) % k]++;
        }
        for(int i = 1;i <= count.length/2;i++){
            if(count[i] != count[count.length - i]){
                return false;
            }
        }
        return count[0] % 2== 0;
    }
}
