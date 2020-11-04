package dynamicprogramming;

import java.util.*;

public class LC140 {
    /**
     *  This question is asking us to find out all the sentences that can be formed with
     *  the words within the dictionary. Since we are finding each of the result rather
     *  than merely the count, it would be better to use backtracking rather than dynamic
     *  programming. Besides, to further optimize the performance, we can use memorization
     *  to reduce repeated calculation for the same case.
     *
     *  In method wordBreak, it is the entry point of the backtracking. Memo is mapping the
     *  index within the array to a list that is maintaining all the partitions.
     *  In another word, <Integer, List<List<String>>>>, each of the entries means that starting
     *  from index i(key), regarding the substring (i, s.length()), what is all the partitions.
     *
     *  After the backtracking, we can pick out the entry at position 0, the value is all the
     *  partitions. Using String.join to rebuild the string
     */
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        Map<Integer, List<List<String>>> memo = new HashMap<>();
        dfs(memo, set, s, 0);
        List<String> result = new ArrayList<>();
        List<List<String>> lists = memo.get(0);
        for(List<String> list : lists){
            String res = String.join(" ", list);
            result.add(res);
        }
        return result;
    }

    /**
     *  Starting from pos to the end of the string, if pos is already calculated, we can directly
     *  return the result within the memo.
     *  Otherwise, we need to calculate the corresponding partitions.
     */
    private List<List<String>> dfs(Map<Integer, List<List<String>>> memo, Set<String> set, String s, int pos){

        if(!memo.containsKey(pos)){
            //Build the value
            List<List<String>> res = new ArrayList<>();
            // preprocessing in case it reached the end, it is the base case.
            // Need to provide this for future for-each loop
            if(pos == s.length()){
                res.add(new ArrayList<>());
            }
            for(int i = pos;i <= s.length();i++){
                /**
                 * Pick out the pattern, adding the pattern to existing dictionary
                 */
                String pattern = s.substring(pos, i);
                if(set.contains(pattern)){
                    List<List<String>> nexts = dfs(memo, set, s, i);
                    for(List<String> next : nexts){
                        List<String> copy = new ArrayList<>(next);
                        copy.add(0, pattern);
                        res.add(copy);
                    }
                }
            }
            memo.put(pos, res);
        }
        return memo.get(pos);
    }
}
