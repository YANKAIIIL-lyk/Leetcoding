package backtracking;
import java.util.*;

public class LC721 {
    /**
     * Building a graph first. For the List<List<String>> accounts, all the address should have an
     * edge to other addresses within the same list.
     * Then, maintain a result list and a map.
     * For each of the accounts, the account[0] will be the name. So, lets do dfs starting from the
     * account[1]. Keep traversing through the accounts that you had never met before. Sort it before
     * adding to the result set.
     * return res
     */
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, List<String>> map = getMap(accounts);
        List<List<String>> res = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        for(List<String> account : accounts){
            List<String> temp = new ArrayList<>();
            dfs(temp, account.get(1), visited, map);
            Collections.sort(temp);
            if(temp.size() != 0){
                temp.add(0, account.get(0));
                res.add(temp);
            }
        }
        return res;
    }

    /**
     * String email is the current position. Adding it to the result list and visited set.
     * Traverse all the neighbors with dfs
     */
    private void dfs(List<String> temp, String email, Set<String> set, Map<String, List<String>> map){
        if(set.contains(email)){
            return;
        }
        set.add(email);
        temp.add(email);
        List<String> neighbors = map.get(email);
        for(String neighbor : neighbors){
            dfs(temp, neighbor, set, map);
        }
    }

    /**
     * Undirected map.
     */
    private Map<String, List<String>> getMap(List<List<String>> accounts){
        Map<String, List<String>> map = new HashMap<>();
        for(List<String> account : accounts){
            String key = account.get(1);
            if(!map.containsKey(key)){
                map.put(key, new ArrayList<>());
            }
            for(int i = 2;i < account.size();i++){
                String value = account.get(i);
                if(!map.containsKey(value)){
                    map.put(value, new ArrayList<>());
                }
                map.get(key).add(value);
                map.get(value).add(key);
            }
        }
        return map;
    }
}
