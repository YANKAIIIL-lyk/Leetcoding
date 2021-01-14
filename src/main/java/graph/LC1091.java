package graph;

import java.util.ArrayDeque;
import java.util.Queue;

public class LC1091 {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int step = 1;
        Queue<Pair> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[grid.length][grid.length];
        visited[0][0] = true;
        if(grid[0][0] == 1 || grid[grid.length - 1][grid.length - 1] == 1){
            return -1;
        }
        queue.offer(new Pair(0, 0));
        while(!queue.isEmpty()){
            int size = queue.size();
            while(size > 0){
                Pair curr = queue.poll();
                int x = curr.x;
                int y = curr.y;
                for(int i = x - 1;i <= x + 1;i++){
                    for(int j = y - 1;j <= y + 1;j++){
                        if(i >= 0 && j >= 0 && i < grid.length && j < grid.length && grid[i][j] == 0 && !visited[i][j] && !(i == x && j == y)){
                            if(i == grid.length - 1 && j == grid.length - 1){
                                return step + 1;
                            }
                            queue.offer(new Pair(i, j));
                            visited[i][j] = true;
                        }
                    }
                }
                size--;
            }
            step++;
        }
        return -1;
    }
}

class Pair{
    int x;
    int y;
    Pair(int x, int y){
        this.x = x;
        this.y = y;
    }
}
