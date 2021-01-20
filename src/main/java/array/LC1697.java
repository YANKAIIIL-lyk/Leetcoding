package array;

public class LC1697 {
    public static void main(String[] args) {
        int[] nums = {3, 1, 3, 4, 3};
        int k = 6;
        int res = maxOperations(nums, k);
        System.out.println(res);
    }

    public static int maxOperations(int[] nums, int k) {
        int[] record = new int[k];
        for(int i : nums){
            record[i]++;
        }
        int res = 0;
        for(int i = 1;i < k / 2;i++){
            int a = record[i];
            int b = record[k - i];
            if(i != k - i){
                res += Math.min(a, b);
            }else{
                res += record[i] / 2;
            }
        }
        return res;
    }
}
