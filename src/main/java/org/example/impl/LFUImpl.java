package org.example.impl;

import org.example.CacheAlgorithm;

public class LFUImpl<K, V> implements CacheAlgorithm<K, V> {
    @Override
    public V findInCache(K k) {
        return null;
    }

    @Override
    public V putIntoCache(K k, V v) {
        return null;
    }

    @Override
    public void display() {
        System.out.println("No implementation");
    }
}
