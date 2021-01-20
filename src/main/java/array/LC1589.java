package array;
import java.util.*;

public class LC1589 {
    /**
     * By the nature of this problem, we know that we need to calculate the
     * frequencies of between the request range. To get the frequency quickly,
     * we can use the diff array.
     *
     * Consider diff array, if we need to update the range i to j with value k,
     * we can do diff[i] += k, diff[j + 1] -= k
     * Then, during the restore phase, all the number will be updated.
     *
     * By doing so, we can calculate the frequency very fast and get to the answer.
     */

    public int maxSumRangeQuery(int[] nums, int[][] requests) {
        int[] diff = new int[nums.length + 1];
        for(int[] request : requests){
            diff[request[0]]++;
            diff[request[1] + 1]--;
        }
        int[] freq = new int[nums.length];
        freq[0] = diff[0];
        for(int i = 1;i < freq.length;i++){
            freq[i] = freq[i - 1] + diff[i];
        }
        Arrays.sort(nums);
        Arrays.sort(freq);
        long res = 0;
        int module = 1000000007;
        for(int i = nums.length - 1;i >= 0 && freq[i] > 0;i--){
            res += (long)nums[i] * freq[i];
        }
        return (int)(res % module);
    }
}
