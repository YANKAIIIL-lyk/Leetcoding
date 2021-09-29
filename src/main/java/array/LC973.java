package array;

public class LC973 {
    /**
     * Instead of using a heap, we can also use quick-select to sort part of the matrix and pick the top K elements
     * In K Closest, we are using the index to denote current status
     */
    public int[][] kClosest(int[][] points, int K) {
        //sort the points by increasing order
        int[][] res = new int[K][2];
        //K elements indicates that the terminating suffix should be k - 1
        findK(0, points.length - 1, K - 1, points);
        for(int i = 0;i < K;i++){
            res[i] = points[i];
        }
        return res;
    }

    private void findK(int left, int right, int K, int[][] points){
        /**
         * Partition the matrix and get the pivot.
         * If pivot < K, it means the first pivot numbers of points are sorted. We still need to sort (pivot + 1, right]
         * Otherwise, pivot is larger than K, we need to sort left to pivot - 1
         */
        int pivot = partition(left, right, points);
        if(pivot < K){
            findK(pivot + 1, right, K, points);
        }else if(pivot > K){
            findK(left, pivot - 1, K, points);
        }
    }

    private int partition(int left, int right, int[][] points){
        /**
         * We must be aware the fact that partition should return l, NOT THE PIVOT!!!
         */
        int pivot = left + (int)(Math.random() * (right - left + 1));
        int distance = dist(points, pivot);
        int l = left;
        int r = right - 1;
        swap(points, right, pivot);
        while(l <= r){
            if(dist(points, l) <= distance){
                l++;
            }else{
                swap(points, l, r--);
            }
        }
        swap(points, l, right);
        return l;
    }

    private int dist(int[][] points, int i){
        int[] cord = points[i];
        int row = cord[0];
        int col = cord[1];
        return row * row + col * col;
    }

    private void swap(int[][] points, int i, int j){
        int[] temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }
}
