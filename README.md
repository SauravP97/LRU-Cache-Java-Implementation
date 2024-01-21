# LRU (Least Recently Used) Cache [Java implementation]

## How to use

Compile the LRU Cache:

```
// Compile the dependecies
javac -d <source_directory> core/DoublyLinkedList.java
javac -d <source_directory> core/LruCache.java
javac -d <source_directory> io/Reader.java

// Compile the Cache Runner class
javac -d <source_directory> core/CacheRunner.java
```

Head over to the source directory and Run the LRU Cache:
```
java compiled/lru/CacheRunner

// Input the total number of entries and the capacity of Cache
// No. of enrteis = 9
// Size of Cache = 2
9 2
// Types of Operations
//  Put: 1 <key> <value>
//  Get: 0 <key>
1 1 1   // cache.put(1, 1), cache is {1=1}
1 2 2   // cache.put(2, 2), cache is {1=1, 2=2}
0 1     // cache.get(1), returns 1
1 3 3   // cache.put(3, 3), Least recently used key was 2, evicts key 2, cache is {1=1, 3=3}
0 2     // cache.get(0), returns -1 (not found)
1 4 4   // cache.put(4, 4), Least recently used key was 1, evicts key 1, cache is {4=4, 3=3}
0 1     // cache.get(1), returns -1 (not found)
0 3     // cache.get(3), returns 3
0 4     // cache.get(4), returns 4
```

> Try this problem on Leetcode: [Link](https://leetcode.com/problems/lru-cache/description/)

---

## Problem Statement

Design a Least Recently Used (LRU) cache from scratch. The basic idea is to implement an LRU Cache that supports two operations:

1. <u>**Get (int key)**</u>: Returns the value of the **key**, if that key exists, otherwise return **-1** or **null**.
2. <u>**Put (int key, int value)** </u>: Update the **value** of the **key** if the key exists. Otherwise, add the **key-value** pair to the cache. If the number of key exceeds the **capacity** from this operation, then evict the least recently used key.

> The function **get** and **put** must each run in <u>**O(1)**</u> average time complexity.

---
## Solution

The implementation involves two Data Structures:

1. Doubly LinkedList
2. HashMap

At first we start the LRU Cache by setting the **capacity** of the cache. This means that the cache cannot store the key-value pairs exceeding the capacity value of the LRU Cache.

The LRU Cache implementation supports two operations:
1. int get(int key)
2. void put(int key, int value)

![LRU Cache Internals](/media/cache1.jpg)

---
## int get(int key)

The method returns the **value** of the **key** if it exists, otherwise return **-1** or **null**.

If the cache holds the **key**, then the **key-value** pair is **refreshed** and becomes the most recently used pair in the cache.

We use a pair of **Doubly LinkedList** and **HashMap** data-structure to store the relative ordering of the **key-value** pairs according to their last-use time.

We get the exact **Node** in the **Doubly LinkedList** which holds the **key** of the requested key-value pair using the **HashMap** and pluck out that **Node** from its current position and move it to the front in the **Doubly LinkedList**. The nodes in the LinkedList are arranged in the order of their last-use time.

The Get operation takes place as follows.

![LRU Cache - Get operation](/media/cache3.jpeg)

---
## void put(int key, int value)

The method sets the **value** for the **key** in the **Cache**. If the **key** does not exist then the added key is the latest used entry in the Cache. Otherwise the last used time of the **key** is refreshed to be the latest used entry in the Cache.

![LRU Cache - Put operation](/media/cache2.jpg)