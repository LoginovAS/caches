package org.example;

public class Cache<K, V> {

    private CacheAlgorithm<K, V> algorithm;

    public void setAlgorithm(CacheAlgorithm<K, V> algorithm) {
        this.algorithm = algorithm;
    }

    public V get(K k) {
        return algorithm == null ? null : algorithm.findInCache(k);
    }

    public V put(K k, V v) {
        return algorithm == null ? null : algorithm.putIntoCache(k, v);
    }

    public void display() {
        if (algorithm != null) {
            algorithm.display();
        }
    }

}
