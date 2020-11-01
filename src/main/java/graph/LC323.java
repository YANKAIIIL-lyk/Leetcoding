package graph;

import java.util.ArrayList;
import java.util.List;

public class LC323 {
    public static void main(String[] args) {
        LC323 lc323 = new LC323();
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        int res = lc323.countComponents(5, edges);
        System.out.println(res);
        System.out.println("LC323");
    }
    public int countComponents(int n, int[][] edges) {
        List<List<Integer>> edgeList = new ArrayList<>();
        int count = 0;
        for(int i = 0;i < n;i++){
            edgeList.add(new ArrayList<>());
        }
        for(int[] edge : edges){
            edgeList.get(edge[0]).add(edge[1]);
            edgeList.get(edge[1]).add(edge[0]);
        }
        boolean[] checked = new boolean[n];
        for(int i = 0;i < n;i++){
            if(!checked[i]){
                count++;
                dfs(i, edgeList, checked);
            }
        }
        return count;
    }


    private void dfs(int pos, List<List<Integer>> edges, boolean[] checked){
        checked[pos] = true;
        List<Integer> neighbors = edges.get(pos);
        for(Integer neighbor : neighbors){
            if(!checked[neighbor]){
                checked[neighbor] = true;
                dfs(neighbor, edges, checked);
            }
        }
    }
}
