package dynamicprogramming;

import java.util.*;

public class LC472 {

    /**
     *  Method 1:
     *
     *  Using dfs to find all combinations. Using all words to form a set.
     *  Then check each of the words with dfs.
     *  For each word, split the word at every index. For example, catdog -> cat, dog
     *  Then, cat should be in the set and dog should be in the set or can be splited.
     */
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> set = new HashSet<>(Arrays.asList(words));
        for (String word : words) {
            if (dfs(word, set)) {
                result.add(word);
            }
        }
        return result;
    }
    private boolean dfs(String word, Set<String> set) {

        for (int i = 1; i < word.length(); i++) {
            if (set.contains(word.substring(0, i))) {
                String tail = word.substring(i);
                if (set.contains(tail) || dfs(tail, set)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *  Method 2
     *  Obviously, a longer word can only be formed by concatenating several shorter words
     *  So, sort all the words with its length and maintain a set to record all the strings
     *  that can be formed. Traversing all the words one by one in the for loop.
     *  Since we are traversing the word array, in each loop, we put words[i] into the set,
     *  then check whether words[i] is valid.
     *  Use check() to check words[i]. For each position of words i, break it into 2 pieces.
     *  [0, i) and [i, end). If set contains [0, i), we only need to guarantee that tail is also
     *  in the set.
     */
    public List<String> findAllConcatenatedWordsInADict1(String[] words) {
        List<String> result = new ArrayList<>();
        if(words == null || words.length == 0){
            return result;
        }
        Arrays.sort(words, (s1, s2) -> s1.length() - s2.length());
        Set<String> set = new HashSet<>();
        set.add(words[0]);
        for(int i = 1;i < words.length;i++){
            set.add(words[i]);
            if(check(words[i], set)){
                result.add(words[i]);
            }
        }
        return result;
    }
    private boolean check(String word, Set<String> set){
        for(int i= 1;i < word.length();i++){
            if(set.contains(word.substring(0, i))){
                String tail = word.substring(i);
                if(set.contains(tail) || check(tail, set)){
                    return true;
                }
            }
        }
        return false;
    }
}
