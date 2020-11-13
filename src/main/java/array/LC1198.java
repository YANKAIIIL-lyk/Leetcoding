package array;

public class LC1198 {
    public static void main(String[] args) {
        System.out.println(5/6);
    }
    /**
     *  Compare multiple rows 1 by 1 within the while loop, until we hit the end of row
     *  or count == mat.length
     */
    public int smallestCommonElement(int[][] mat) {
        if (mat == null || mat.length <= 1) {
            return -1;
        }
        int[] ptrs = new int[mat.length];
        int max = mat[0][0];
        while (true) {
            int count = 0;
            int row = 0;
            while (row < ptrs.length) {
                while (mat[row][ptrs[row]] < max && ptrs[row] < mat[0].length) {
                    ptrs[row]++;
                }
                if (ptrs[row] >= mat[0].length) {
                    return -1;
                }

                if (mat[row][ptrs[row]] == max) {
                    count++;
                } else if (mat[row][ptrs[row]] > max) {
                    max = mat[row][ptrs[row]];
                    break;
                }
                row++;
            }
            if (count == mat.length) {
                return max;
            }
        }
    }
}
