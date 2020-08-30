package org.example.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LruImplTest {

    /**
     * Creates LRU Cache, fills it completely, requests all but one item and puts yet another object.
     * Checks that least recently used item has been evicted from cache but others were not.
     */
    @Test
    public void findInCacheAfterEvictionTest() {
        LruImpl<Integer, Integer> cache = createFilledCache(3);
        cache.findInCache(2);
        cache.findInCache(2);
        cache.findInCache(1);
        cache.putIntoCache(4, 400);
        assertEquals(100, cache.findInCache(1).intValue());
        assertEquals(200, cache.findInCache(2).intValue());
        assertNull(cache.findInCache(3)); // Least recently requested. Should be displaced.
    }

    /**
     * Creates LRU cache, fills it completely.
     * Checks that existing item can be found.
     */
    @Test
    public void findInCacheExistingTest() {
        LruImpl<Integer, Integer> cache = createFilledCache(2);
        assertEquals(100, cache.findInCache(1).intValue());
    }

    /**
     * Creates LRU cache, fills it completely.
     * Checks that null returns upon attempt to find nonexistent item.
     */
    @Test
    public void findInCacheNonExistentTest() {
        LruImpl<Integer, Integer> cache = createFilledCache(2);
        assertNull(cache.findInCache(3));
    }

    /**
     * Creates empty LRU cache. Puts there an item.
     * Checks that value of item has been returned.
     */
    @Test
    public void putIntoCacheTest() {
        LruImpl<Integer, Integer> cache = createEmptyCache(2);
        assertEquals(100, cache.putIntoCache(1, 100).intValue());
    }

    /**
     * Creates empty LRU cache. Tries to put there null.
     * Checks that IllegalArgumentException has been returned.
     */
    @Test(expected = IllegalArgumentException.class)
    public void putInCacheNullExceptionTest() {
        LruImpl<Integer, Integer> cache = createEmptyCache(2);
        cache.putIntoCache(null, null);
    }

    /**
     * Tries to create LRU cache with 0 maxSize.
     * Checks that IllegalArgumentException has been returned.
     */
    @Test(expected = IllegalArgumentException.class)
    public void algorithmCreationNullMaxSizeExceptionTest() {
        new LruImpl<>(0);
    }

    private LruImpl<Integer, Integer> createEmptyCache(int maxSize) {
        return new LruImpl<>(maxSize);
    }

    private LruImpl<Integer, Integer> createFilledCache(int maxSize) {
        return fillCache(new LruImpl<>(maxSize));
    }

    private LruImpl<Integer, Integer> fillCache(LruImpl<Integer, Integer> cache) {
        for (int i = 1; i <= cache.getMaxSize(); i++) {
            cache.putIntoCache(i, i * 100);
        }

        return cache;
    }
}
