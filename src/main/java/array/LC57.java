package array;
import java.util.*;

public class LC57 {
    /**
     *  The first while loop pick out all the intervlals ended earlier than new Interval.
     *  The second loop needs to merge all the possible intervals, if it is possible to
     *  merge, the end of new interval must be larger than start of internal. Pay attention
     *  to the condition.
     *  The third loop appended all the unmerged intervals.
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        int i = 0;
        while(i < intervals.length && intervals[i][1] < newInterval[0]){
            res.add(intervals[i]);
            i++;
        }
        while(i < intervals.length && newInterval[1] >= intervals[i][0]){
            int[] interval = intervals[i];
            newInterval[0] = Math.min(newInterval[0], interval[0]);
            newInterval[1] = Math.max(newInterval[1], interval[1]);
            i++;
        }
        res.add(newInterval);
        while(i < intervals.length){
            res.add(intervals[i]);
            i++;
        }
        int[][] result = new int[res.size()][2];
        for(int j = 0;j < res.size();j++){
            result[j] = res.get(j);
        }
        return result;
    }
}
