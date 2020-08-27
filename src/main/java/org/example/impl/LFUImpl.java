package org.example.impl;

import org.example.CacheAlgorithm;

import java.util.HashMap;
import java.util.Map;

public class LFUImpl<K, V> implements CacheAlgorithm<K, V> {

    private Map<K, Element<V>> heap;
    private int maxSize;
    private int maxCount;
    private int minCount;
    private K lfuKey;

    public LFUImpl(int maxSize) {
        this.heap = new HashMap<>(maxSize);
        this.maxSize = maxSize;
        this.maxCount = 0;
        this.minCount = -1;
        this.lfuKey = null;
    }

    @Override
    public V findInCache(K k) {
        Element<V> element = heap.get(k);
        if (element != null) {
            element.increaseCount();
            if (maxCount < element.getCount()) {
                maxCount = element.getCount();
                // TODO: Отслеживание элемента с минимальным количеством обращений.
            }
            return element.getValue();
        }

        return null;
    }

    @Override
    public V putIntoCache(K k, V v) {
        V value = findInCache(k);
        if (value == null) {
            if (heap.size() == maxSize) {

            }
        }
        return null;
    }

    @Override
    public void display() {
        System.out.println("No implementation");
    }
}
