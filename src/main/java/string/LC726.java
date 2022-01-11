package string;

import java.util.*;

public class LC726 {
    public static void main(String[] args) {
        LC726 lc726 = new LC726();
        String res = lc726.countOfAtoms("Be32");
        System.out.println(res);
    }
    public String countOfAtoms(String formula) {
        Map<String, Integer> map = parse(formula);
        return flatten(map);
    }

    private String flatten(Map<String, Integer> map) {
        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys);
        StringBuilder sb = new StringBuilder();
        for (String key : keys) {
            sb.append(key);
            if(map.get(key) > 1){
                sb.append(map.get(key));
            }
        }
        return sb.toString();
    }

    private Map<String, Integer> parse(String formula) {
        Map<String, Integer> map = new HashMap<>();
        int i = 0;
        while (i < formula.length()) {
            StringBuilder sb = null;
            Map<String, Integer> inner = null;
            if (Character.isUpperCase(formula.charAt(i))) {
                sb = new StringBuilder();
                sb.append(formula.charAt(i++));
                while (i < formula.length() && Character.isLowerCase(formula.charAt(i))) {
                    sb.append(formula.charAt(i++));
                }
            } else if (formula.charAt(i) == '(') {
                int open = 1;
                int j = i + 1;
                while (j < formula.length() && open != 0) {
                    if (formula.charAt(j) == '(') {
                        open++;
                    } else if (formula.charAt(j) == ')') {
                        open--;
                    }
                    if(open == 0){
                        break;
                    }
                    j++;
                }
                String innerString = formula.substring(i + 1, j);
                inner = parse(innerString);
                i = j + 1;
            }
            StringBuilder digit = new StringBuilder();
            while (i < formula.length() && Character.isDigit(formula.charAt(i))) {
                digit.append(formula.charAt(i++));
            }
            int count = digit.length() == 0 ? 1 : Integer.parseInt(digit.toString());
            if (sb != null) {
                String key = sb.toString();
                map.put(key, map.getOrDefault(key, 0) + count);
            } else if (inner != null) {
                for (Map.Entry<String, Integer> entry : inner.entrySet()) {
                    String key = entry.getKey();
                    int value = entry.getValue();
                    map.put(key, map.getOrDefault(key, 0) + count * value);
                }
            }
        }
        return map;
    }
}
