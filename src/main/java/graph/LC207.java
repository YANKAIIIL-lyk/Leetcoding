package graph;
import java.util.*;

public class LC207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        /**
         * When we are using map to store the graph and in-degree, we need to explicitly add the nodes with 0 in degree.
         * Otherwise, all the nodes within the in-degree map will have an in-degree of not 0.
         */
        Map<Integer, Set<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> indegree = new HashMap<>();
        for(int i = 0;i < numCourses;i++){
            indegree.put(i, 0);
        }
        for(int[] prerequisite : prerequisites){
            int from = prerequisite[1];
            int to = prerequisite[0];
            if(!graph.containsKey(from)){
                graph.put(from, new HashSet<>());
            }
            graph.get(from).add(to);
            indegree.put(to, indegree.getOrDefault(to, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        Queue<Integer> queue = new ArrayDeque<>();
        for(Map.Entry<Integer, Integer> temp : indegree.entrySet()){
            if(temp.getValue() == 0){
                queue.offer(temp.getKey());
            }
        }

        while(!queue.isEmpty()){
            int curr = queue.poll();
            res.add(curr);
            Set<Integer> neighbors = graph.get(curr);
            if(neighbors != null){
                for(Integer neighbor : neighbors){
                    indegree.put(neighbor, indegree.get(neighbor) - 1);
                    if(indegree.get(neighbor) == 0){
                        queue.offer(neighbor);
                    }
                }
            }
        }
        return res.size() == numCourses;
    }
}
