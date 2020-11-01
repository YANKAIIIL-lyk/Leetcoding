package array;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class LC349 {
    public int[] intersection(int[] nums1, int[] nums2) {
            Set<Integer> set = new HashSet<>();
            for(int i : nums1){
                set.add(i);
            }
            Set<Integer> resSet = new HashSet<>();

            for(int i : nums2){
                if(set.contains(i)){
                    resSet.add(i);
                }
            }

            int[] res = new int[resSet.size()];
            Iterator<Integer> itor = resSet.iterator();
            for(int i = 0;i < res.length;i++){
                res[i] = itor.next();
            }
            return res;
    }
}
