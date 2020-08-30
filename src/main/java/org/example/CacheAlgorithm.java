package org.example;

import java.util.HashMap;
import java.util.Map;

public abstract class CacheAlgorithm<K, V> {

    protected Map<K, V> heap;
    protected int maxSize;

    public CacheAlgorithm(int maxSize) {
        checkAboveZero("Max size should be above zero", maxSize);
        this.heap = new HashMap<>(maxSize);
        this.maxSize = maxSize;
    }

    private void checkAboveZero(String message, int value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Max size should be above zero");
        }
    }

    public V findInCache(K k) {
        V value = heap.get(k);
        if (value != null) {
            registerRequest(k);
        }

        return value;
    }

    public V putIntoCache(K k, V v) {
        checkArgumentNotNull("Key cannot be null", k);
        checkArgumentNotNull("Value cannot be null", v);
        V value = findInCache(k);
        if (value == null) {
            evictIfFull();
            updatePositions(k);
        }

        heap.put(k, v);

        return v;
    }

    private void checkArgumentNotNull(String message, Object o) {
        if (o == null) {
            throw new IllegalArgumentException(message);
        }
    }

    private void evictIfFull() {
        if (heap.size() == maxSize) {
            evict();
        }
    }

    public int getMaxSize() {
        return maxSize;
    }

    protected abstract void registerRequest(K k);

    protected abstract void evict();

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
