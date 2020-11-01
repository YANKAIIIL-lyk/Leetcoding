package string;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC524 {

    /**
     * Delete chars from s will generate a subsequence of s. We only need to check each
     * string in d to see whether it's a subsequence of s. To verify whether it is subsequence,
     * we can use dual pointers.
     */
    public String findLongestWord(String s, List<String> d) {
        String res = null;
        for(String str : d){
            if(verify(s, str)){
                if(res == null){
                    res = str;
                }else{
                    if(str.length() > res.length()){
                        res = str;
                    }else if(str.length() == res.length()){
                        res = res.compareTo(str) > 0 ? str : res;
                    }
                }
            }
        }
        return res == null ? "" : res;
    }

    private boolean verify(String s, String t){
        int sIndex = 0;
        int tIndex = 0;
        while(sIndex < s.length() && tIndex < t.length()){
            if(s.charAt(sIndex) == t.charAt(tIndex)){
                sIndex++;
                tIndex++;
            }else{
                sIndex++;
            }
        }
        return tIndex == t.length();
    }

    /**
     * Find all the subsequences. This way will TLE. Cost 2^n when finding all
     * the subsequences with backtracking.
     */
    public String findLongestWord1(String s, List<String> d) {
        Set<String> all = new HashSet<>();
        generateAll(0, new StringBuilder(), s, all);
        Collections.sort(d, (o1, o2) -> -(o1.length() - o2.length()));
        String res = null;
        for(int i = 0;i < d.size();i++){
            if(all.contains(d.get(i))){
                if(res == null){
                    res = d.get(i);
                }else{
                    if(d.get(i).length() < res.length()){
                        break;
                    }
                    res = d.get(i).compareTo(res) < 0 ? d.get(i) : res;
                }
            }
        }
        return res;
    }

    private void generateAll(int index, StringBuilder sb, String s, Set<String> res){
        if(index == s.length()){
            res.add(new String(sb));
            return;
        }
        sb.append(s.charAt(index));
        generateAll(index + 1, sb, s, res);
        sb.deleteCharAt(sb.length() - 1);
        generateAll(index + 1, sb, s, res);
    }

}
