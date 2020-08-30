package org.example.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LRUImplTest {
    @Test
    public void findInCacheExistingTest() {
        LRUImpl<Integer, Integer> algorithm = new LRUImpl<>(2);
        algorithm.putIntoCache(1, 100);
        assertEquals(100, algorithm.findInCache(1).intValue());
    }

    @Test
    public void findInCacheNonExistentTest() {
        LRUImpl<Integer, Integer> algorithm = new LRUImpl<>(2);
        algorithm.putIntoCache(1, 100);
        assertNull(algorithm.findInCache(2));
    }

    @Test
    public void putIntoCacheTest() {
        LRUImpl<Integer, Integer> algorithm = new LRUImpl<>(2);
        assertEquals(100, algorithm.putIntoCache(1, 100).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void putInCacheNullExceptionTest() {
        LRUImpl<Integer, Integer> lfu = new LRUImpl<>(2);
        lfu.putIntoCache(null, null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void algorithmCreationNullMaxSizeExceptionTest() {
        LRUImpl<Integer, Integer> algorithm = new LRUImpl<>(0);
    }
}
