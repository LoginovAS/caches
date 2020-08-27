package org.example;

public interface CacheAlgorithm<K, V> {

    V findInCache(K k);
    V putIntoCache(K k, V v);
    int size();

}
