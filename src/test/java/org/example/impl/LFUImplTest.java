package org.example.impl;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class LFUImplTest {

    @Test
    public void findInCacheExistingTest() {
        LFUImpl<Integer, Integer> algorithm = new LFUImpl<>(2);
        algorithm.putIntoCache(1, 100);
        assertEquals(100, algorithm.findInCache(1).intValue());
    }

    @Test
    public void findInCacheNonExistentTest() {
        LFUImpl<Integer, Integer> algorithm = new LFUImpl<>(2);
        algorithm.putIntoCache(1, 100);
        assertNull(algorithm.findInCache(2));
    }

    @Test
    public void putIntoCacheTest() {
        LFUImpl<Integer, Integer> algorithm = new LFUImpl<>(2);
        assertEquals(100, algorithm.putIntoCache(1, 100).intValue());
    }

    @Test(expected = IllegalArgumentException.class)
    public void putInCacheNullExceptionTest() {
        LFUImpl<Integer, Integer> lfu = new LFUImpl<>(2);
        lfu.putIntoCache(null, null);
    }


    @Test(expected = IllegalArgumentException.class)
    public void algorithmCreationNullMaxSizeExceptionTest() {
        LFUImpl<Integer, Integer> algorithm = new LFUImpl<>(0);
    }
}
