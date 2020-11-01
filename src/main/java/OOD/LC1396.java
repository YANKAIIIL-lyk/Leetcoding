package OOD;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class LC1396 {
    class Sync extends AbstractQueuedSynchronizer{

    }
}

class Pair<K, V>{
    K key;
    V value;
    Pair(K key, V value){
        this.key = key;
    }
}
