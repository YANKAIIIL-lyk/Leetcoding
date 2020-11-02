package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class LC253 {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0){
            return 0;
        }
        if(intervals.length == 1){
            return 1;
        }
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] row1, int[] row2){
                if(row1[0] < row2[0]){
                    return -1;
                }else if(row1[0] > row2[0]){
                    return 1;
                }
                return row1[1] < row2[1] ? -1 : 1;
            }
        });
        PriorityQueue<int[]> meetings = new PriorityQueue<>(new Comparator<int[]>(){
            @Override
            public int compare(int[] row1, int[] row2){
                return row1[1] - row2[1];
            }
        });
        int res = 0;
        for(int[] interval : intervals){
            if(meetings.isEmpty()){
                meetings.offer(interval);
            }else{
                while(!meetings.isEmpty() && meetings.peek()[1] < interval[0]){
                    meetings.poll();
                }
                meetings.offer(interval);
            }
            res = Math.max(res, meetings.size());
        }
        return res;
    }
}
