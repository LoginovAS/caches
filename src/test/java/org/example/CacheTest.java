package org.example;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class CacheTest {

    /**
     * Creates Default Cache (using default constructor) with default maxSize (10), fills it completely.
     * Checks that existing object can be found in cache.
     */
    @Test
    public void getByDefaultAlgorithmExistingWithoutEvictionTest() {
        Cache<Integer, Integer> cache = fillDefaultCache();
        assertEquals(200, cache.get(2).intValue());
    }

    /**
     * Creates Default Cache (using default constructor) with default maxSize (10), fills it completely.
     * Checks that request of nonexistent items will return null
     */
    @Test
    public void getByDefaultAlgorithmNullIfNotExistsTest() {
        Cache<Integer, Integer> cache = fillDefaultCache();
        assertNull(cache.get(11));
    }

    /**
     * Creates Default Cache (using default constructor) with default maxSize (10), fills it completely.
     * Requests all but one item and puts yet another object.
     * Checks that least recently used item has been displaced from cache but others were not.
     */
    @Test
    public void getByDefaultAlgorithmEvictionTest() {
        Cache<Integer, Integer> cache = fillDefaultCache();
        cache.get(10);
        cache.get(8);
        cache.get(7);
        cache.get(6);
        cache.get(5);
        cache.get(4);
        cache.get(3);
        cache.get(2);
        cache.get(1);
        cache.put(11, 1100);
        assertEquals(100, cache.get(1).intValue());
        assertEquals(200, cache.get(2).intValue());
        assertEquals(300, cache.get(3).intValue());
        assertEquals(400, cache.get(4).intValue());
        assertEquals(500, cache.get(5).intValue());
        assertEquals(600, cache.get(6).intValue());
        assertEquals(700, cache.get(7).intValue());
        assertEquals(800, cache.get(8).intValue());
        assertEquals(1000, cache.get(10).intValue());
        assertEquals(1100, cache.get(11).intValue());
        assertNull(cache.get(9)); // Least recently requested. Should be displaced.
    }

    private Cache<Integer, Integer> fillDefaultCache() {
        return fillCache(new Cache<>());
    }

    private Cache<Integer, Integer> fillCache(Cache<Integer, Integer> cache) {
            for (int i = 1; i <= cache.getMaxSize(); i++) {
                cache.put(i, i * 100);
            }

        return cache;
    }


}
