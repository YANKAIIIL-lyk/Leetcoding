package backtracking;

public class LC60 {
    public static void main(String[] args) {
        LC60 lc60 = new LC60();
        String res = lc60.getPermutation(3, 3);
        System.out.println(res);
    }
    public String getPermutation(int n, int k) {
        boolean[] number = new boolean[n + 1];
        int[] factorial = getFactorial(9);
        StringBuilder sb = new StringBuilder();
        dfs(n, k, number, factorial, sb);
        return sb.toString();
    }

    private void dfs(int n, int k, boolean[] number, int[] factorial, StringBuilder sb){
        if(n == 0){
            return;
        }
        int batch = factorial[n - 1];
        for(int i = 1;i < number.length;i++){
            if(number[i]){
                continue;
            }
            if(k - batch > 0){
                k-= batch;
                continue;
            }
            sb.append(i);
            number[i] = true;
            dfs(n - 1, k, number, factorial, sb);
            return;
        }
    }


    private int[] getFactorial(int n){
        int[] res = new int[10];
        res[0] = 1;
        for(int i = 1;i < res.length;i++){
            res[i] = res[i - 1] * i;
        }
        return res;
    }
}
