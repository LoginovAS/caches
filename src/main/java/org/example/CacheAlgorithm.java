package org.example;

import java.util.HashMap;
import java.util.Map;

public abstract class CacheAlgorithm<K, V> {

    protected Map<K, V> heap;
    protected int maxSize;

    public CacheAlgorithm(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("Max size should be above zero");
        }
        this.heap = new HashMap<>(maxSize);
        this.maxSize = maxSize;
    }

    public V findInCache(K k) {
        V value = heap.get(k);
        if (value != null) {
            registerRequest(k);
        }

        return value;
    }

    public V putIntoCache(K k, V v) {
        if (k == null || v == null) {
            throw new IllegalArgumentException("Key or value cannot be null");
        }
        V value = findInCache(k);
        if (value == null) {
            displaceIfFull();
            updatePositions(k);
        }

        heap.put(k, v);

        return v;
    }

    private void displaceIfFull() {
        if (heap.size() == maxSize) {
            displace();
        }
    }

    protected abstract void registerRequest(K k);

    protected abstract void displace();

    protected abstract void updatePositions(K k);



    public String toString() {
        StringBuilder result = new StringBuilder();

        for (Map.Entry<K, V> entry : heap.entrySet()) {
            result
                    .append("[")
                    .append(entry.getKey())
                    .append(",")
                    .append(entry.getValue())
                    .append("] ");
        }

        return result.toString();
    }

}
