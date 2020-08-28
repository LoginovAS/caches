package org.example;

import org.example.impl.LRUImpl;

public class Cache<K, V> {

    private static final int DEFAULT_CAPACITY = 10;

    private CacheAlgorithm<K, V> algorithm;

    /**
     * Creates cache with LRU algorithm and 10 objects by default.
     */
    public Cache() {
        algorithm = new LRUImpl<>(DEFAULT_CAPACITY);
    }

    public Cache(CacheAlgorithm<K, V> algorithm) {
        this.algorithm = algorithm;
    }

    public void setAlgorithm(CacheAlgorithm<K, V> algorithm) {
        this.algorithm = algorithm;
    }

    public V get(K k) {
        return algorithm == null ? null : algorithm.findInCache(k);
    }

    public V put(K k, V v) {
        return algorithm == null ? null : algorithm.putIntoCache(k, v);
    }

    public String toString() {
        return algorithm.toString();
    }

}
