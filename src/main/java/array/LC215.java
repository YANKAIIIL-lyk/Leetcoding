package array;

public class LC215 {
    /**
     * Using quick-select is better than using heap. The time complexity for using heap is always O(nlogn),
     * but for quick-select, it becomes O(n) for average. For worst case, O(n^2)
     *
     * For quick-select, we know that we are looking for the value of position nums.length - k.
     *
     * Approach 1:
     * Using tail recursion. Pass the range for quick select, if the pivot is larger than target, then we
     * should go left, otherwise go right.
     * The method partition is the same as that for quicksort.
     *
     * Approach 2:
     * Tail recursion can always be converted to a non-recursive way
     *     public int findKthLargest(int[] nums, int k) {
     *         int target = nums.length - k;
     *         int left = 0;
     *         int right = nums.length - 1;
     *         while(true){
     *             int index = partition(nums, left, right);
     *             if(index == target){
     *                 return nums[target];
     *             }else if(index <= target){
     *                 left = index + 1;
     *             }else{
     *                 right = index - 1;
     *             }
     *         }
     *     }
     * We still need the left and right boundary. But instead of using tail recursion, we can use while loop here.
     * If the result of partition is the target, we can return the result. Otherwise, either go right or go left
     * within the if statement.
     */
    public int findKthLargest(int[] nums, int k) {
        int start = 0;
        int end = nums.length - 1;
        int target = nums.length - k;
        return kth(start, end, target, nums);
    }

    private int kth(int start, int end, int target, int[] nums){
        int pivot = partition(start, end, nums);
        if(pivot == target){
            return nums[pivot];
        }else if(pivot < target){
            return kth(pivot + 1, end, target, nums);
        }else{
            return kth(start, pivot - 1, target, nums);
        }
    }


    private int partition(int start, int end, int[] nums){
        int position = start + (int)(Math.random() * (end - start));
        int value = nums[position];
        swap(nums, position, end);
        int left = start;
        int right = end - 1;
        while(left <= right){
            if(nums[left] <= value){
                left++;
            }else{
                swap(nums, left, right--);
            }
        }
        swap(nums, left, end);
        return left;
    }

    private void swap(int[] nums, int left, int right){
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
