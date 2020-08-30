package org.example.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LfuImplTest {

    /**
     * Creates LFU Cache, fills it completely, requests all several times but one item and puts yet another object.
     * Checks that least frequency used item has been displaced from cache but others were not.
     */
    @Test
    public void findInCacheAfterEvictionTest() {
        LfuImpl<Integer, Integer> cache = createFilledCache(3);
        cache.findInCache(2);
        cache.findInCache(2);
        cache.findInCache(2);
        cache.findInCache(1);
        cache.findInCache(1);
        cache.putIntoCache(4, 400);
        cache.findInCache(4);
        assertEquals(200, cache.findInCache(2).intValue()); // 5 requests
        assertEquals(100, cache.findInCache(1).intValue()); // 4 requests
        assertEquals(400, cache.findInCache(4).intValue()); // 3 requests
        assertNull(cache.findInCache(3)); // 2 requests. Should be displaced.
    }

    /**
     * Creates LFU cache, fills it completely.
     * Checks that existing item can be found.
     */
    @Test
    public void findInCacheExistingTest() {
        LfuImpl<Integer, Integer> cache = createFilledCache(3);
        assertEquals(100, cache.findInCache(1).intValue());
    }

    /**
     * Creates LRU cache, fills it completely.
     * Checks that null returns upon attempt to find nonexistent item.
     */
    @Test
    public void findInCacheNonExistentTest() {
        LfuImpl<Integer, Integer> cache = createFilledCache(2);
        assertNull(cache.findInCache(3));
    }

    /**
     * Creates empty LFU cache. Puts there an item.
     * Checks that value of item has been returned.
     */
    @Test
    public void putIntoCacheTest() {
        LfuImpl<Integer, Integer> cache = createEmptyCache(2);
        assertEquals(100, cache.putIntoCache(1, 100).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void putInCacheNullExceptionTest() {
        LfuImpl<Integer, Integer> lfu = createEmptyCache(2);
        lfu.putIntoCache(null, null);
    }

    /**
     * Creates empty LFU cache. Tries to put there null.
     * Checks that IllegalArgumentException has been returned.
     */
    @Test(expected = IllegalArgumentException.class)
    public void algorithmCreationNullMaxSizeExceptionTest() {
        new LfuImpl<>(0);
    }

    private LfuImpl<Integer, Integer> createEmptyCache(int maxSize) {
        return new LfuImpl<>(maxSize);
    }

    private LfuImpl<Integer, Integer> createFilledCache(int maxSize) {
        return fillCache(new LfuImpl<>(maxSize));
    }

    private LfuImpl<Integer, Integer> fillCache(LfuImpl<Integer, Integer> cache) {
        for (int i = 1; i <= cache.getMaxSize(); i++) {
            cache.putIntoCache(i, i * 100);
        }

        return cache;
    }
}
