package array;
import java.util.*;

public class LC621 {
    public static void main(String[] args) {
        char[] c = {'A', 'A', 'A', 'B', 'B', 'B'};
        int n = 2;
        LC621 lc621 = new LC621();
        int res = lc621.leastInterval(c, 2);
        System.out.println(res);


    }
    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = getFreq(tasks);
        int m = freq.size();
        List<Integer> nextValid = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        for(Map.Entry<Character, Integer> entry : freq.entrySet()){
            nextValid.add(1);
            res.add(entry.getValue());
        }
        int time = 0;
        for(int i = 0;i < tasks.length;i++){
            time++;
            int minNext = Integer.MAX_VALUE;
            for(int j = 0;i < nextValid.size();j++){
                if(res.get(j) != 0){
                    minNext = Math.min(minNext, nextValid.get(j));
                }
            }
            time = Math.max(time, minNext);
            int best = -1;
            for(int j = 0;j < res.size();j++){
                if(res.get(j) > 0 && nextValid.get(j) <= time){
                    if(best == -1 || res.get(j) > res.get(best)){
                        best = j;
                    }
                }
            }
            nextValid.set(best, time + n + 1);
            res.set(best, res.get(best) - 1);
        }
        return time;
    }

    private Map<Character, Integer> getFreq(char[] tasks){
        Map<Character, Integer> res = new HashMap<>();
        for(char task : tasks){
            res.put(task, res.getOrDefault(task, 0) + 1);
        }
        return res;
    }
}
