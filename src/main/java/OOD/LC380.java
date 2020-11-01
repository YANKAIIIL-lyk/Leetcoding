package OOD;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.*;

public class LC380{

}

/**
 * To get O(1) for add and delete, we will need a hashmap. The implementation of hashset
 * is similar to that of hashmap.
 * To get O(1) for randomize, hashmap is not applicable since it didn't implement the RandomAccess interface.
 * We can consider using arraylist instead.
 *
 * Built up one map and one list. The map is storing all the mappings from an inserted value to its position
 * within list. For the list, store the corresponding value at the given position.
 *
 * (1) Inserting(int val)
 * if map contains the val(as key), return false. Otherwise, put the val into the map, the value of the entry
 * should be the position to be inserted within the list, so it is list.size()
 * (2) Removing(int val)
 * Retrieve the index of the element to delete within the map. Retrieve the index and value of the last element
 * within the array. Move it to the place of the element to be deleted.
 * Delete the last element
 */
class RandomizedSet {

    Map<Integer, Integer> map;
    List<Integer> list;
    Random rand;

    /** Initialize your data structure here. */
    public RandomizedSet() {
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