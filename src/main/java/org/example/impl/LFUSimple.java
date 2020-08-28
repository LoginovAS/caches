package org.example.impl;

import org.example.CacheAlgorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class LFUSimple<K, V> implements CacheAlgorithm<K, V> {

    private Map<K, V> heap;
    private TreeMap<Integer, LinkedList<K>> frequencies;
    private Map<K, Integer> keys;
    private int maxSize;

    public LFUSimple(int maxSize) {
        this.heap = new HashMap<>(maxSize);
        this.frequencies = new TreeMap<>();
        this.keys = new HashMap<>(maxSize);
        this.maxSize = maxSize;
    }

    @Override
    public V findInCache(K k) {
        V value = heap.get(k);
        if (value != null) {
            Integer frequencyByKey = keys.get(k);
            LinkedList<K> keysByFrequency = frequencies.get(frequencyByKey);

            // Удалим, т.к. количество обращений изменится.
            keysByFrequency.remove(k);

            // Если это был последний элемент, то удалим запись о частоте, потому что для этого элемента она уже меняется.
            if (keysByFrequency.isEmpty()) {
                frequencies.remove(frequencyByKey);
            }

            frequencyByKey++;

            keysByFrequency = frequencies.get(frequencyByKey);

            if (keysByFrequency == null) {
                keysByFrequency = new LinkedList<>();
            }

            keysByFrequency.add(k);

            frequencies.put(frequencyByKey, keysByFrequency);
            keys.put(k, frequencyByKey);
        }
        return value;
    }

    @Override
    public V putIntoCache(K k, V v) {
        V value = findInCache(k);
        if (value == null) {
            if (heap.size() == maxSize) {
                LinkedList<K> minimalFrequencyKeys = frequencies.get(frequencies.firstKey());
                K oldestKeyByFrequency = minimalFrequencyKeys.removeFirst();
                keys.remove(oldestKeyByFrequency);
                heap.remove(oldestKeyByFrequency);
            }

            keys.put(k, 0);
            LinkedList<K> newList = new LinkedList<>();
            newList.add(k);
            frequencies.put(0, newList);
        }

        heap.put(k, v);

        return v;
    }

    @Override
    public void display() {

    }
}
