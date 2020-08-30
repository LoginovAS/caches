package org.example;

import org.example.impl.LruImpl;

public class Cache<K, V> {

    private static final int DEFAULT_CAPACITY = 10;

    private CacheAlgorithm<K, V> algorithm;

    /**
     * Creates cache with LRU algorithm and 10 objects by default.
     */
    public Cache() {
        algorithm = new LruImpl<>(DEFAULT_CAPACITY);
    }

    public Cache(CacheAlgorithm<K, V> algorithm) {
        this.algorithm = algorithm;
    }

    public V get(K k) {
        return algorithm.findInCache(k);
    }

    public V put(K k, V v) {
        return algorithm.putIntoCache(k, v);
    }

    public int getMaxSize() {
        return algorithm.getMaxSize();
    }

    public String toString() {
        return algorithm.toString();
    }

}
