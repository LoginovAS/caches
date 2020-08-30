package org.example.impl;

import org.example.CacheAlgorithm;

import java.util.LinkedList;

public class LruImpl<K, V> extends CacheAlgorithm<K, V> {

    private LinkedList<K> keyQuery;

    public LruImpl(int maxSize) {
        super(maxSize);
        this.keyQuery = new LinkedList<>();
    }

    @Override
    protected void registerRequest(K k) {
        keyQuery.remove(k);
        keyQuery.add(k);
    }

    @Override
    protected void evict() {
        heap.remove(keyQuery.removeFirst());
    }

    @Override
    protected void updatePositions(K k) {
        keyQuery.add(k);
    }
}
