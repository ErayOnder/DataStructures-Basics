package Hashing;

import java.lang.reflect.Array;
import java.util.HashSet;
import java.util.Set;

import Hashing.given.AbstractHashMap;
import Hashing.given.HashEntry;

/*
 * The file should contain the implementation of a hashmap with:
 * - Open addressing for collision handling
 * - Double hashing for probing. The double hash function should be of the form: q - (k mod q)
 * - Multiply-Add-Divide (MAD) for compression: (a*k+b) mod p
 * - Resizing (to double its size) and rehashing when the load factor gets above a threshold
 */

public class HashMapDH<Key, Value> extends AbstractHashMap<Key, Value> {

  // The underlying array to hold hash entries (see the HashEntry class)
  private HashEntry<Key, Value>[] buckets;

  @SuppressWarnings("unchecked")
  protected void resizeBuckets(int newSize) {
    // Update the capacity
    N = nextPrime(newSize);
    buckets = (HashEntry<Key, Value>[]) Array.newInstance(HashEntry.class, N);
  }

  // The threshold of the load factor for resizing
  protected float criticalLoadFactor;

  // The prime number for the secondary hash
  int dhP;
  @SuppressWarnings("rawtypes")
  private static final HashEntry DELETED = new HashEntry<>(null, null);

  // Default constructor
  public HashMapDH() {
    this(101);
  }

  public HashMapDH(int initSize) {
    this(initSize, 0.6f);
  }

  public HashMapDH(int initSize, float criticalAlpha) {
    N = initSize;
    criticalLoadFactor = criticalAlpha;
    resizeBuckets(N);

    // Set up the MAD compression and secondary hash parameters
    updateHashParams();
  }

  /*
   * Calculates the hash value by compressing the given hashcode. 
   */
  protected int primaryHash(int hashCode) {
    return Math.abs((a * hashCode + b) % P);
  }

  /*
   * The secondary hash function.
   */
  protected int secondaryHash(int hashCode) {
    return dhP - (hashCode % dhP);
  }

  @Override
  public int hashValue(Key key, int iter) {
    int k = Math.abs(key.hashCode());
    return Math.abs(primaryHash(k) + iter * secondaryHash(k)) % N;
  }

  /**
   * checkAndResize checks whether the current load factor is greater than the
   * specified critical load factor. If it is, the table size should be increased
   * to 2*N and recreate the hash table for the keys (rehashing). 
   */
  protected void checkAndResize() {
    if (loadFactor() > criticalLoadFactor) {
      HashEntry<Key, Value>[] oldEntries = buckets;
      resizeBuckets(N * 2);
      updateHashParams();
      n = 0;
      for (HashEntry<Key, Value> entry: oldEntries) {
        if (entry != DELETED && entry != null) {
          put(entry.getKey(), entry.getValue());
        }
      }
    }
  }

  @Override
  public Value get(Key k) {
    if (k == null) return null;
    int i = 0;
    int idx = hashValue(k, i);
    
    while (buckets[idx] != null) {
      if (buckets[idx] != DELETED && buckets[idx].getKey().equals(k)) {
        return buckets[idx].getValue();
      }
      idx = hashValue(k, ++i);
    }
    return null;
  }

  @Override
  public Value put(Key k, Value v) {
    if (k == null) return null;
    checkAndResize();
    int i = 0;
    int idx = hashValue(k, i);
    int firstDeletedIndex = -1;

    while (buckets[idx] != null) {
        if (buckets[idx].getKey().equals(k)) {
            Value oldVal = buckets[idx].getValue();
            buckets[idx].setValue(v);
            return oldVal;
        }
        if (buckets[idx] == DELETED && firstDeletedIndex == -1) {
            firstDeletedIndex = idx;
        }
        idx = hashValue(k, ++i);
    }
    if (firstDeletedIndex != -1) {
        buckets[firstDeletedIndex] = new HashEntry<>(k, v);
    } else {
        buckets[idx] = new HashEntry<>(k, v);
    }
    n++;
    return null;
}

  @SuppressWarnings("unchecked")
  @Override
  public Value remove(Key k) {
    if (k == null) return null;
    int i = 0;
    int idx = hashValue(k, i);
    
    while (buckets[idx] != null) {
        if (buckets[idx] != DELETED && buckets[idx].getKey().equals(k)) {
            Value val = buckets[idx].getValue();
            buckets[idx] = DELETED;
            n--;
            return val;
        }
        idx = hashValue(k, ++i);
    }
    return null;
}

  @Override
  public Iterable<Key> keySet() {
    Set<Key> keySet = new HashSet<>();
    for (HashEntry<Key, Value> entry: buckets) {
      if (entry != DELETED && entry != null) {
        keySet.add(entry.getKey());
      }
    }
    return keySet;
  }

  @Override
  protected void updateHashParams() {
    super.updateHashParams();
    dhP = nextPrime(N / 2);
  }

}