package org.example.impl;

import org.example.CacheAlgorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.TreeMap;

public class LFUImpl<K, V> extends CacheAlgorithm<K, V> {

    private TreeMap<Integer, LinkedList<K>> frequencies;
    private Map<K, Integer> keys;

    public LFUImpl(int maxSize) {
        this.heap = new HashMap<>(maxSize);
        this.frequencies = new TreeMap<>();
        this.keys = new HashMap<>(maxSize);
        this.maxSize = maxSize;
    }

    protected void registerRequest(K k) {
        Integer frequencyByKey = removeFrequencyInformation(k);
        addNewFrequencyInformation(++frequencyByKey, k);
    }

    private Integer removeFrequencyInformation(K k) {
        Integer frequencyByKey = keys.get(k);
        LinkedList<K> keysByFrequency = frequencies.get(frequencyByKey);

        keysByFrequency.remove(k);

        if (keysByFrequency.isEmpty()) {
            frequencies.remove(frequencyByKey);
        }

        return frequencyByKey;
    }

    private void addNewFrequencyInformation(Integer frequency, K k) {
        LinkedList<K> keysByFrequency = getOrCreateKeysByFrequencySeq(frequency);
        keysByFrequency.add(k);
        frequencies.put(frequency, keysByFrequency);
        keys.put(k, frequency);
    }

    private LinkedList<K> getOrCreateKeysByFrequencySeq(Integer frequency) {
        LinkedList<K> keysByFrequency = frequencies.get(frequency);

        if (keysByFrequency == null) {
            keysByFrequency = new LinkedList<>();
        }

        return keysByFrequency;
    }

    @Override
    protected void supplant() {
        LinkedList<K> minimalFrequencyKeys = frequencies.get(frequencies.firstKey());
        K oldestKeyByFrequency = minimalFrequencyKeys.removeFirst();
        keys.remove(oldestKeyByFrequency);
        heap.remove(oldestKeyByFrequency);
    }

    protected void updatePositions(K k) {
        keys.put(k, 0);
        LinkedList<K> firstRequest = frequencies.get(0);

        if (firstRequest == null) {
            firstRequest = new LinkedList<>();
        }

        firstRequest.add(k);
        frequencies.put(0, firstRequest);
    }
}
