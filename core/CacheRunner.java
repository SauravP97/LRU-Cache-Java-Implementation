package compiled.lru;

import compiled.io.Reader;
import java.io.IOException;

class CacheRunner {
    public static void main(String[] args) throws IOException {
        Reader reader = new Reader();
        int tc = reader.nextInt();
        int capacity = reader.nextInt();

        LruCache cache = new LruCache(capacity);

        int ch = 0;

        while (ch < tc) {
            int op = reader.nextInt();
            if (op == 0) {
                // Get the value from cache
                int key = reader.nextInt();
                int value = cache.get(key);
                System.out.println("Value: " + value);
            } else {
                // Put the value into cache
                int key = reader.nextInt();
                int value = reader.nextInt();
                cache.put(key, value);
            }
            ch++;
        }
    }
}