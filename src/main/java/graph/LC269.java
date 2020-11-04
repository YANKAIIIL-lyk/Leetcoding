package graph;
import java.util.*;

public class LC269 {
    /**
     *  Finding the order of the characters, so we can use topological sort. The
     *  graph is formed implicitly, and can be a little bit abstract. However,
     *  since the words are arranged lexicographically, we can compare them one
     *  by one and form the graph, then do topological sort.
     *
     *  One thing to notice is that, we may add the same edge multiple times. For
     *  example, the sequence a -> d can emerge multiple times since it can exist
     *  in mupltiple word combinations. Using a list instead of Set.
     *
     *  Complexity analysis:
     *  N is the number of strings.
     *  C is the count of all characters within the input list.
     *  U is the count of unique characters within the input list.
     *  Time complexity will be tricky.
     *  During the initialization of graph(with out filling it), it will iterate
     *  through all the characters within the words array, so the time complexity
     *  in this stage will be O(c). c is the sum of all chars.
     *
     *  When building up the graph, same time complexity. Since all the characters
     *  were visited exactly once.
     *
     *  When traversing the graph, we need to go through each of the edges and vertices.
     *  Each time we compare two strings, it will generate 1 edge only. The upper limit
     *  of edges is U^2, since it is impossible to have an edge between all nodes. So the
     *  time complexity of edge count will be max(N - 1, U^2). Thus, for the traversing
     *  part, time complexity will be O(n) = O(C + max(N - 1, U^2))
     *
     */
    public String alienOrder(String[] words) {
        Map<Character, Integer> count = new HashMap<>();
        Map<Character, List<Character>> map = new HashMap<>();
        for(String word : words){
            for(char c : word.toCharArray()){
                count.put(c, 0);
                map.put(c, new ArrayList<>());
            }
        }

        for(int i = 0;i < words.length - 1;i++){
            String word1 = words[i];
            String word2 = words[i + 1];
            if(word1.length() > word2.length() && word1.indexOf(word2) == 0){
                return "";
            }
            for(int j = 0;j < word1.length() && j < word2.length();j++){
                if(word1.charAt(j) != word2.charAt(j)){
                    char c1 = word1.charAt(j);
                    char c2 = word2.charAt(j);
                    count.put(c2, count.get(c2) + 1);
                    map.get(c1).add(c2);
                    break;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        Queue<Character> queue = new ArrayDeque<>();
        for(Map.Entry<Character, Integer> entry : count.entrySet()){
            if(entry.getValue() == 0){
                queue.offer(entry.getKey());
            }
        }
        while(!queue.isEmpty()){
            char c = queue.poll();
            sb.append(c);
            for(char p : map.get(c)){
                count.put(p, count.get(p) - 1);
                if(count.get(p) == 0){
                    queue.offer(p);
                }
            }
        }
        return sb.length() == count.size() ? sb.toString() : "";
    }
}
