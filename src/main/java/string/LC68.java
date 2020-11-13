package string;

import java.util.ArrayList;
import java.util.List;

public class LC68 {
    /**
     *  For each of the rows, it can be divided into two cases. It should be the last line of text
     *  or the intermediate line of text.
     *
     *  In fullJustify, use start and end to annotate the range of lines we are processing, start
     *  is inclusive and end is exclusive. [start, end). Once we have the range of text we want,
     *  pass into reorder and add the return value into res
     */
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<>();
        int start = 0;
        while(start < words.length){
            int count = words[start].length();
            int end = start + 1;
            while(end < words.length){
                String temp = words[end];
                if(count + temp.length() + 1 > maxWidth){
                    break;
                }else{
                    count += temp.length() + 1;
                    end++;
                }
            }
            String partial = reorder(words, start, end, maxWidth);
            res.add(partial);
            start = end;
        }
        return res;
    }

    /**
     *  We are rebuilding each line of the result here. If it should be the last line,
     *  we use lastRow to process it separately. Otherwise, we calculate several variable.
     *  gap is the number slots that can take whitespces. It equals to (the count of words - 1)
     *  remainingWhitespace is the count of empty cells that we need to fill with whitespace
     *
     *  Consider we have 7 whitespace and 3 slots, the optimal solution is 3 + 2 + 2 = 7
     *  7 / 3 = 2 ...... 1. For each of the slots, we should insert 2 whitespaces, and there is
     *  still 1 remaining. All the remaining whitespaces should be inserted to each slots, from
     *  left to right, one by one.
     *
     *  Then, since it is possible that we inserted excessive whitespace after the last word, we
     *  only return the first maxWidth characters.
     */
    private String reorder(String[] words, int start, int end, int maxWidth){
        if(end == words.length){
            return lastRow(words, start, end, maxWidth);
        }
        StringBuilder sb = new StringBuilder();
        int charCount = 0;
        for(int i = start;i < end;i++){
            charCount += words[i].length();
        }
        int gap = end - start - 1;//count of whitespace
        int remainingWhitespace = maxWidth - charCount;
        int quotient = 0;
        int remaining = 0;
        if(gap != 0){
            quotient = remainingWhitespace / gap;
            remaining = remainingWhitespace % gap;
        }
        for(int i = start;i < end;i++){
            sb.append(words[i]);
            for(int j = 0;j < quotient;j++){
                sb.append(" ");
            }
            if(remaining > 0){
                sb.append(" ");
                remaining--;
            }
        }
        while(sb.length() < maxWidth){
            sb.append(" ");
        }
        return sb.toString().substring(0, maxWidth);
    }

    /**
     *  Idea is similar to reorder, only difference is that we are processing the last line.
     */
    private String lastRow(String[] words, int start, int end, int maxWidth){
        StringBuilder sb = new StringBuilder();
        for(int i = start;i < end;i++){
            sb.append(words[i] + " ");
        }
        while(sb.length() < maxWidth){
            sb.append(" ");
        }
        return sb.toString().substring(0, maxWidth);
    }

    public static void main(String[] args) {
        LC68 lc68 = new LC68();
        String[] input = {"This", "is", "an", "example", "of", "text", "justification."};
        List<String> res = lc68.fullJustify(input, 16);
        System.out.println(res.toString());
    }
}
