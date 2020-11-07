package string;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC394 {
    public static String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while(i < s.length()){
            if(Character.isAlphabetic(s.charAt(i))){
                sb.append(s.charAt(i++));
            }else{
                int result = 0;
                while(Character.isDigit(s.charAt(i))){
                    result = 10 * result + (s.charAt(i++) - '0');
                }
                int open = 1;
                i++;
                int start = i;
                String temp = null;
                while(i < s.length()){
                    if(s.charAt(i) == '['){
                        open++;
                    }else if(s.charAt(i) == ']'){
                        open--;
                    }
                    if(open == 0){
                        temp = decodeString(s.substring(start, i));
                        i++;
                        break;
                    }
                    i++;
                }
                while(result > 0){
                    sb.append(temp);
                    result--;
                }
            }
        }
        return sb.toString();
    }
}
