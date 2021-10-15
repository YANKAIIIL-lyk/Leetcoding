package greedy;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * Greedy algorithm
 * Two observations:
 * 1. For the final answer, the leftmost character will be the smallest character in the input string from which all the
 * suffix will include all the letters.
 * Reason: The final answer needs to include all the existing strings. Alphabetically smallest answer requires the smallest
 * answer to show up as early as possible.
 *
 * 2. When we have character i, and character i + 1. If c[i] > c[i + 1], and at a later position k(k > i + 1) there is a
 * character c[i] == c[k], we can safely remove c[i].
 * Reason: Since there is c[k] also exists, we can always keep c[k] to ensure all the letters are existing. However, the
 * sequence c[i]c[i + 1] is larger than c[i + 1]c[k]. So picking c[i + 1]c[k] would be a better idea.
 */
public class LC316 {

    // Use observation 1
    public String removeDuplicateLetters(String s) {
        if(s == null || s.length() == 0){
            return s;
        }
        int[] bucket = new int[26];
        for(char c : s.toCharArray()){
            bucket[c - 'a']++;
        }
        int pos = 0;// The position of the leftmost character in the answer
        for(int i = 0;i < s.length();i++){
            // if there exists a character smaller(position i) than pos, then the leftmost character will be i
            // all the letters between [pos, i] must exist more than one time. Otherwise, it will be interrupted by break.
            if(s.charAt(i) < s.charAt(pos)){
                pos = i;
            }
            // If the character at position i only exists once, then it must be kept. Break here and the smallest character
            // is still at pos
            if(bucket[s.charAt(i) - 'a'] == 1){
                break;
            }
            bucket[s.charAt(i) - 'a']--;
        }
        // Pick out the character at pos, for the remaining string, remove all the character s[pos] to deduplicate.
        return s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll(""+s.charAt(pos), ""));
    }

    // Use observation 2
    public String removeDuplicateLetters2(String s) {
        if(s == null || s.length() == 0){
            return "";
        }
        int[] bucket = new int[26];
        for(char c : s.toCharArray()){
            bucket[c - 'a']++;
        }
        // visited is keeping track of all the characters within the stack
        Set<Character> visited = new HashSet<>();
        Deque<Character> stack = new ArrayDeque<>();
        for(int i = 0;i < s.length();i++){
            char c = s.charAt(i);
            if(!visited.contains(c)){
                // if current character doesn't exist in the stack, compare it with the stack top
                // if s[i] > s[top], then add it into the stack
                // otherwise, if s[i] < s[top], and there is more than one s[top] remaining, removing s[top] and add s[i]
                // can lead to a smaller string
                while(!stack.isEmpty() && stack.peekFirst() > c && bucket[stack.peekFirst() - 'a'] > 0){
                    char remove = stack.pollFirst();
                    visited.remove(remove);
                }
                visited.add(c);
                stack.offerFirst(c);
            }
            bucket[c - 'a']--;
        }
        StringBuilder sb = new StringBuilder();
        // we can either poll from the other end, or still poll the elements as a stack but reverse the order when return
        // the result.
        while(!stack.isEmpty()){
            sb.append(stack.pollLast());
        }
        return sb.toString();
    }
}
