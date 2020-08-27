package org.example.impl;

import org.example.CacheAlgorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUImpl<K, V> implements CacheAlgorithm<K, V> {

    private Map<K, V> heap;
    private LinkedList<K> keyQuery;
    private int maxSize;

    public LRUImpl(int maxSize) {
        this.heap = new HashMap<>();
        this.keyQuery = new LinkedList<>();
        this.maxSize = maxSize;
    }


    @Override
    public V findInCache(K k) {
        V value = heap.get(k);
        if (value != null) {
            if (keyQuery.contains(k)) {
                keyQuery.remove(k);
                keyQuery.add(k);
            }
        }
        return value;
    }

    @Override
    public V putIntoCache(K k, V v) {
        V value = findInCache(k);
        if (value == null) {
            if (keyQuery.size() == maxSize) {
                heap.remove(keyQuery.removeFirst());
            }
            keyQuery.add(k);
        }

        heap.put(k, v);

        return v;
    }

    @Override
    public void display() {
        for (Map.Entry<K, V> entry : heap.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
    }
}
