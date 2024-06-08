package Hashing;

import Hashing.given.AbstractHashMap;
import Hashing.given.iPrintable;

/*
 * A class that counts the number of items with no removal or decrement. 
 * Each time an item is input to the class, it should increase its count.
 * When an item that has not been input is asked, it should return 0. 
 *    *   
 * When input the follow [a,b,a,a,b,c], the class should return:
 * - 3 for a
 * - 2 for b
 * - 1 for c
 * - 0 for d
 * 
 * It should also keep track of the number of items and the sum of all the items
 * 
 * It is setup as a composition based design instead of an inheritance based one.
 * This makes it easier to test multiple hashmaps and shows you another
 * way to achieve polymorphism 
 */

public class HashCounter<Key> implements iPrintable<Key> {
  
  AbstractHashMap<Key,Integer> A;
  
  // Total count of all items
  int s;
  
  // The hashmap to be used for counting will be supplied from the outside. 
  // Not the best choice for production code!
  public HashCounter(AbstractHashMap<Key,Integer> inContainer) {
    A = inContainer;
  }
  
  //Default constructor, feel free to change the default hash map implementation to HashMapSC
  public HashCounter() {
    this(new HashMapDH<Key, Integer>());
  }
  
  public int size() {
    return A.size();
  }
  
  public boolean isEmpty() {
    return A.isEmpty();
  }
  
  public int total() {
    return s;
  }
  
  /*
   * Increments the count of the key if it is already in the counter, 
   * creates a new item with count 1 if not
   */
  public void increment(Key key) {
    if (A.get(key) == null) {
      A.put(key, 1);
    } else {
      A.put(key, A.get(key) + 1);
    }
    s++;
  }
  
  /*
   * Returns the count of the key if it is in the counter, 
   * or returns 0 if not
   */
  public int getCount(Key key) {
    if (A.get(key) == null) {
      return 0;
    } else {
      return A.get(key);
    }
  }
  
  /*
   * Convenience function that counts all the keys in the given array
   */
  public void countAll(Key[] keys) {
    for (Key k : keys) {
      increment(k);
    }
  }
  
  /*
   * Convenience function that counts all the keys in the given iterable
   */
  public void countAll(Iterable<Key> keys) {
    for (Key k : keys) {
      increment(k);
    }
  }
  
  /*
   * Return an iterable collection of the unique keys in the counter
   */
  public Iterable<Key> keySet() {
    return A.keySet();
  }
  
  @Override
  public Object get(Key key) {
    return getCount(key);
  }

}