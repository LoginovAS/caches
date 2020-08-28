package org.example.impl;

import org.example.CacheAlgorithm;

import java.util.HashMap;
import java.util.LinkedList;

public class LRUImpl<K, V> extends CacheAlgorithm<K, V> {

    private LinkedList<K> keyQuery;

    public LRUImpl(int maxSize) {
        this.heap = new HashMap<>(maxSize);
        this.keyQuery = new LinkedList<>();
        this.maxSize = maxSize;
    }

    @Override
    protected void registerRequest(K k) {
        keyQuery.remove(k);
        keyQuery.add(k);
    }

    @Override
    protected void supplant() {
        heap.remove(keyQuery.removeFirst());
    }

    @Override
    protected void updatePositions(K k) {
        keyQuery.add(k);
    }
}
