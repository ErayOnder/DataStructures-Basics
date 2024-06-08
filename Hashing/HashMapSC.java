package Hashing;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

import Hashing.given.AbstractHashMap;
import Hashing.given.HashEntry;

/*
 * The file should contain the implementation of a hashmap with:
 * - Separate Chaining for collision handling
 * - Multiply-Add-Divide (MAD) for compression: (a*k+b) mod p
 * - Java's own linked lists for the secondary containers
 * - Resizing (to double its size) and rehashing when the load factor gets above a threshold
 *   Note that for this type of hashmap, load factor can be higher than 1
 */

public class HashMapSC<Key, Value> extends AbstractHashMap<Key, Value> {

  // The underlying array to hold hash entry Lists
  private LinkedList<HashEntry<Key, Value>>[] buckets;

  @SuppressWarnings("unchecked")
  protected void resizeBuckets(int newSize) {
    // Update the capacity
    N = nextPrime(newSize);
    buckets = (LinkedList<HashEntry<Key, Value>>[]) Array.newInstance(LinkedList.class, N);
    for (int i = 0; i < N; i++) {
      buckets[i] = new LinkedList<>();
    }
  }

  // The threshold of the load factor for resizing
  protected float criticalLoadFactor;

  public int hashValue(Key key, int iter) {
    return hashValue(key);
  }

  public int hashValue(Key key) {
    int hashCode = Math.abs(key.hashCode());
    return Math.abs((a * hashCode + b) % P) % N;
  }

  // Default constructor
  public HashMapSC() {
    this(101);
  }

  public HashMapSC(int initSize) {
    // High criticalAlpha for representing average items in a secondary container
    this(initSize, 10f);
  }

  public HashMapSC(int initSize, float criticalAlpha) {
    N = initSize;
    criticalLoadFactor = criticalAlpha;
    resizeBuckets(N);
    
    // Set up the MAD compression and secondary hash parameters
    updateHashParams();
  }

  @Override
  public Value get(Key k) {
    if (k == null) return null;
    int idx = hashValue(k);
    for (HashEntry<Key, Value> entry: buckets[idx]) {
      if (entry.getKey().equals(k)) {
        return entry.getValue();
      }
    }
    return null;
  }

  @Override
  public Value put(Key k, Value v) {
    if (k == null) return null;
    checkAndResize();
    int idx = hashValue(k);
    for (HashEntry<Key, Value> entry: buckets[idx]) {
      if (entry.getKey().equals(k)) {
        Value oldVal = entry.getValue();
        entry.setValue(v);
        return oldVal;
      }
    }
    buckets[idx].add(new HashEntry<Key, Value>(k, v));
    n++;
    return null;
  }

  @Override
  public Value remove(Key k) {
    if (k == null) return null;
    int idx = hashValue(k);
    for (HashEntry<Key, Value> entry: buckets[idx]) {
      if (entry.getKey().equals(k)) {
        Value oldVal = entry.getValue();
        buckets[idx].remove(entry);
        n--;
        return oldVal;
      }
    }
    return null;
  }

  @Override
  public Iterable<Key> keySet() {
    Set<Key> keySet = new HashSet<>();
    for (LinkedList<HashEntry<Key, Value>> list: buckets) {
      for(HashEntry<Key, Value> entry: list) {
        keySet.add(entry.getKey());
      }
    }
    return keySet;
  }

  /**
   * checkAndResize checks whether the current load factor is greater than the
   * specified critical load factor. If it is, the table size should be increased
   * to 2*N and recreate the hash table for the keys (rehashing). 
   */
  protected void checkAndResize() {
    if (loadFactor() > criticalLoadFactor) {
      LinkedList<HashEntry<Key, Value>>[] oldEntries = buckets;
      resizeBuckets(N * 2);
      updateHashParams();
      n = 0;

      for (LinkedList<HashEntry<Key, Value>> list: oldEntries) {
        for(HashEntry<Key, Value> entry: list) {
          put(entry.getKey(), entry.getValue());
        }
      }
    }
  }

}