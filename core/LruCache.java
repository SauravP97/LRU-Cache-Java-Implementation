package compiled.lru;

import java.util.HashMap;

public class LruCache {
    private final int CACHE_MAX_SIZE;
    private int CACHE_SIZE;
    private HashMap<Integer, Integer> cacheMap;
    private DoublyLinkedList doublyList;

    LruCache(int capacity) {
        this.CACHE_SIZE = 0;
        this.CACHE_MAX_SIZE = capacity;
        this.cacheMap = new HashMap<>();
        this.doublyList = new DoublyLinkedList();
    }

    public int get(int key) {
        if (this.cacheMap.containsKey(key)) {
            this.doublyList.refreshNode(key);
            return this.cacheMap.get(key);
        } else {
            return -1;
        }
    }

    public void put(int key, int value) {
        if (this.cacheMap.containsKey(key)) {
            // Key already exists.
            this.cacheMap.put(key, value);
            this.doublyList.refreshNode(key);
            return;
        }
        this.cacheMap.put(key, value);
        this.CACHE_SIZE++;
        this.doublyList.addNode(key);
        checkAndEvict();
    }

    public void checkAndEvict() {
        if (this.CACHE_SIZE > this.CACHE_MAX_SIZE) {
            int evictedKey = this.doublyList.evict();
            this.cacheMap.remove(evictedKey);
        }
    }
}