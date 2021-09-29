package design;
import java.util.*;

public class LC380 {
    //key is the what we inserted, value is the position within the list
    Map<Integer, Integer> map;
    List<Integer> list;
    Random rand;

    /** Initialize your data structure here. */
    public LC380() {
        map = new HashMap<>();
        list = new ArrayList<>();
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }
        int lastIndex = list.size() - 1;
        int lastElement = list.get(lastIndex);
        int toRemoveIndex = map.get(val);

        list.set(toRemoveIndex, lastElement);
        list.remove(lastIndex);

        map.put(lastElement, toRemoveIndex);
        map.remove(val);

        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        return list.get(rand.nextInt(list.size()));
    }
}
