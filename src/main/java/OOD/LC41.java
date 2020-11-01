package OOD;

public class LC41 {
    public static int firstMissingPositive(int[] nums) {
        //range from 1 to nums.length
        if(nums == null || nums.length == 0){
            return 1;
        }

        for(int i = 0;i < nums.length;i++){
            if(nums[i] <= 0){
                nums[i] = Integer.MAX_VALUE;
            }
        }
        for(int i = 0;i < nums.length;i++){
            if(Math.abs(nums[i]) > 0 && Math.abs(nums[i]) <= nums.length){
                int index = Math.abs(nums[i]) - 1;
                nums[index] = -nums[index];
            }
        }
        for(int i = 0;i < nums.length;i++){
            if(nums[i] > 0){
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void main(String[] args) {
        int[] input = {1, 1};
        int result = firstMissingPositive(input);
        System.out.println(result);
    }
}
