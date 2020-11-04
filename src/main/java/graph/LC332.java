package graph;

import java.util.*;

public class LC332 {
    /**
     * This is a graph problem and we can traverse the whole graph starting from
     * "JFK". Once we find a route, we can return it since it is guaranteed to be
     * the result.
     *
     * Since we only need to find one route and we are finding it in a greedy way,
     * we can use a boolean variable found to denote whether we have a result.
     * When traversing the graph with DFS, the termination condition will be the time
     * that all tickets are used, which should be tickets.size() + 1
     */
    int target = 0;
    List<String> res;
    boolean found = false;

    public List<String> findItinerary(List<List<String>> tickets) {
        target = tickets.size();
        Map<String, List<String>> graph = new HashMap<>();
        //build up the graph
        for(List<String> ticket : tickets){
            List<String> neighbors = graph.getOrDefault(ticket.get(0), new ArrayList<>());
            neighbors.add(ticket.get(1));
            graph.put(ticket.get(0), neighbors);
        }
        List<String> route = new ArrayList<>();
        //Sort the neighbors to alphabetical order
        for(Map.Entry<String, List<String>> entry : graph.entrySet()){
            List<String> neighbors = entry.getValue();
            Collections.sort(neighbors, (s1, s2) -> s1.compareTo(s2));
        }
        dfs("JFK", graph, route);
        return res;
    }

    /**
     *  start is the city I am in, this is where we start traversing in this round.
     *  graph is the same as last one
     *  route is the route we have flew with
     */
    private void dfs(String start, Map<String, List<String>> graph, List<String> route){
        if(found){
            return;
        }
        /**
         * If route size is equal to target, then we have reached the destination.
         * Add it and return
         */
        if(route.size() == target){
            route.add(start);
            res = new ArrayList<>(route);
            found = true;
            return;
        }
        /**
         * Otherwise, add the start position to the route. If there is no neighbors,
         * then we are on the wrong route, return to last stage. If there exists neighbors,
         * traverse the neighbors one by one to find out the target.
         */
        route.add(start);
        List<String> neighbors = graph.get(start);
        if(neighbors != null){
            for(int i = 0;i < neighbors.size();i++){
                String next = neighbors.get(i);
                neighbors.remove(i);
                dfs(next, graph, route);
                neighbors.add(i, next);
            }
        }
        route.remove(route.size() - 1);
    }
}
