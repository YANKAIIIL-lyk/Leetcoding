package OOD;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LC460 {
    /**
     * Designing LFU needs to keep track of all the inserted node and the frequency of opertaion.
     * So, two maps are needed, one is called map and the other one is called freqMap.
     * When inserting a Node into the freqMap, we always inserting at the front. When removing node
     * because of reaching its capacity, we remove from the back.
     *
     * For the get method
     * (1) First check if the given key exists, return -1 if not exist.
     * (2) Remove the node from freqMap, get the return value and insert it back to the new position
     * within the freqMap.
     * (3) Update the minFreq as needed.
     *
     * For the put method
     * (1) Check if we reach the capacity. Only need to remove nodes when hit the max capacity
     * (2) Put the node into the map, update minFreq accordingly.
     * (3) Update the minFreq as needed.
     */
    Map<Integer, Node> map;
    Map<Integer, LinkedList<Node>> freqMap;
    int capacity;
    int minFreq;

    public LC460(int capacity) {
        this.map = new HashMap<>();
        this.freqMap = new HashMap<>();
        this.capacity = capacity;
        this.minFreq = Integer.MAX_VALUE;
    }

    public int get(int key) {
        if(capacity == 0 || !map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        int val = node.val;
        LinkedList<Node> remove = freqMap.get(node.freq);
        remove.remove(node);
        if(minFreq == node.freq && remove.size() == 0){
            minFreq++;
        }
        node.freq++;
        LinkedList<Node> add = freqMap.getOrDefault(node.freq, new LinkedList<>());
        add.addFirst(node);
        freqMap.put(node.freq, add);
        return val;
    }

    public void put(int key, int value) {
        if(capacity == 0){
            return;
        }
        if(!map.containsKey(key)){
            //capacity
            if(map.size() == capacity){
                LinkedList<Node> remove = freqMap.get(minFreq);
                Node node = remove.removeLast();
                map.remove(node.key);
            }
            Node insert = new Node(key, value, 1);
            map.put(key, insert);
            LinkedList<Node> add = freqMap.getOrDefault(insert.freq, new LinkedList<>());
            add.addFirst(insert);
            freqMap.put(insert.freq, add);
            minFreq = 1;
        }else{
            Node node = map.get(key);
            LinkedList<Node> remove = freqMap.get(node.freq);
            remove.remove(node);
            if(node.freq == minFreq && remove.size() == 0){
                minFreq++;
            }
            node.freq++;
            node.val = value;
            LinkedList<Node> add = freqMap.getOrDefault(node.freq, new LinkedList<>());
            add.addFirst(node);
            freqMap.put(node.freq, add);
        }
    }
}
class Node{
    int key;
    int val;
    int freq;
    Node(int key, int val, int freq){
        this.key = key;
        this.val = val;
        this.freq = freq;
    }
}
