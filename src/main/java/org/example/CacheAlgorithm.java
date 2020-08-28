package org.example;

import java.util.Map;

public abstract class CacheAlgorithm<K, V> {

    protected Map<K, V> heap;
    protected int maxSize;

    public V findInCache(K k) {
        V value = heap.get(k);
        if (value != null) {
            registerRequest(k);
        }

        return value;
    }

    public V putIntoCache(K k, V v) {
        V value = findInCache(k);
        if (value == null) {
            supplantIfIsFull();
            updatePositions(k);
        }

        heap.put(k, v);

        return v;
    }

    private void supplantIfIsFull() {
        if (heap.size() == maxSize) {
            supplant();
        }
    }

    protected abstract void registerRequest(K k);

    protected abstract void supplant();

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
