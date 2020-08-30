package org.example;

import org.example.impl.LFUImpl;
import org.example.impl.LRUImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class CacheTest {

    /**
     * Creates LRU cache with maxSize 3, fills it by 3 objects.
     * Checks that existing object can be found in cache.
     */
    @Test
    public void getByLRUExistingWithoutDisplaceTest() {
        Cache<Integer, Integer> lruCache = fillCache(CacheType.LRU, 3);
        assertEquals(200, lruCache.get(2).intValue());
    }

    /**
     * Creates empty LRU cache with maxSize 3.
     * Checks that request of nonexistent items will return null
     */
    @Test
    public void getByLRUNullIfNotExistsTest() {
        Cache<Integer, Integer> lruCache = new Cache<>(new LRUImpl<>(3));
        assertNull(lruCache.get(1));
    }

    /**
     * Creates default Cache (using default constructor) with default maxSize (10), fills it completely,
     * requests all but one item and puts yet another object.
     * Checks that least recently used item has been displaced from cache but others were not.
     */
    @Test
    public void getByDefaultAlgorithmTest() {
        int defaultMaxSize = 10;
        Cache<Integer, Integer> defaultCache = fillCache(CacheType.DEFAULT, defaultMaxSize);
        defaultCache.get(10);
        defaultCache.get(8);
        defaultCache.get(7);
        defaultCache.get(6);
        defaultCache.get(5);
        defaultCache.get(4);
        defaultCache.get(3);
        defaultCache.get(2);
        defaultCache.get(1);
        defaultCache.put(11, 1100);
        assertEquals(100, defaultCache.get(1).intValue());
        assertEquals(200, defaultCache.get(2).intValue());
        assertEquals(300, defaultCache.get(3).intValue());
        assertEquals(400, defaultCache.get(4).intValue());
        assertEquals(500, defaultCache.get(5).intValue());
        assertEquals(600, defaultCache.get(6).intValue());
        assertEquals(700, defaultCache.get(7).intValue());
        assertEquals(800, defaultCache.get(8).intValue());
        assertEquals(1000, defaultCache.get(10).intValue());
        assertEquals(1100, defaultCache.get(11).intValue());
        assertNull(defaultCache.get(9)); // Last requested. Should be displaced.
    }

    /**
     * Creates LRU Cache, fills it completely, requests all but one item and puts yet another object.
     * Checks that least recently used item has been displaced from cache but others were not.
     */
    @Test
    public void getByLRUAfterDisplaceTest() {
        Cache<Integer, Integer> lruCache = fillCache(CacheType.LRU, 3);
        lruCache.get(2);
        lruCache.get(2);
        lruCache.get(1);
        lruCache.put(4, 400);
        assertEquals(100, lruCache.get(1).intValue());
        assertEquals(200, lruCache.get(2).intValue());
        assertNull(lruCache.get(3)); // Last requested. Should be displaced.
    }

    /**
     * Creates LFU cache with maxSize 3, fills it by 3 objects.
     * Checks that existing object can be found in cache.
     */
    @Test
    public void getByLFUExistingWithoutDisplaceTest() {
        Cache<Integer, Integer> lfuCache = fillCache(CacheType.LFU, 3);
        assertEquals(200, lfuCache.get(2).intValue());
    }

    /**
     * Creates LFU Cache, fills it completely, requests all several times but one item and puts yet another object.
     * Checks that least frequency used item has been displaced from cache but others were not.
     */
    @Test
    public void getByLFUAfterDisplaceTest() {
        Cache<Integer, Integer> lruCache = fillCache(CacheType.LFU, 3);
        lruCache.get(2);
        lruCache.get(2);
        lruCache.get(2);
        lruCache.get(1);
        lruCache.get(1);
        lruCache.put(4, 400);
        lruCache.get(4);
        assertEquals(200, lruCache.get(2).intValue()); // 5 requests
        assertEquals(100, lruCache.get(1).intValue()); // 4 requests
        assertEquals(400, lruCache.get(4).intValue()); // 3 requests
        assertNull(lruCache.get(3)); // 2 requests. Should
    }

    private enum CacheType {
        LRU,
        LFU,
        DEFAULT
    }

    private Cache<Integer, Integer> fillCache(CacheType cacheType, int maxSize) {
        Cache<Integer, Integer> cache;

        switch (cacheType) {
            case LFU:
                cache = new Cache<>(new LFUImpl<>(maxSize));
                break;
            case LRU:
                cache = new Cache<>(new LRUImpl<>(maxSize));
                break;
            default:
                cache = new Cache<>();
        }

        if (maxSize != 0) {
            for (int i = 1; i <= maxSize; i++) {
                cache.put(i, i * 100);
            }
        }

        return cache;
    }

}
