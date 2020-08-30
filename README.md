# caches
The solution of the following task:

Create an in-memory cache (for caching Objects) with configurable max size and eviction strategy.
Two strategies should be implemented: LRU and LFU.
For this task it is assumed that only one thread will access the cache, so there is no need to make it thread-safe.
Please provide an example of usage of the cache as a unit test(s).

 - LRU algorithm. Checks which item was requested least recently (consider put as the first request) end evicts it when the cache filled completely.
 - LFU algorithm. Checks which item was requested least frecuently (consider put as the first request) end evicts it when the cache filled completely.
 
 Tools used:
  - Java 1.8_251
  - JUnit 4.13
  - Maven 3.6.3
