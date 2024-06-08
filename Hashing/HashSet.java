package Hashing;

import Hashing.given.AbstractHashMap;
import Hashing.given.iPrintable;
import Hashing.given.iSet;

// A set class implemented with hashing.

public class HashSet<Key> implements iSet<Key>, iPrintable<Key>{

  private AbstractHashMap<Key, Integer> hashMap;
  
  public HashSet() {
    hashMap = new HashMapDH<Key, Integer>();
  }

  @Override
  public int size() {
    return hashMap.size();
  }

  @Override
  public boolean isEmpty() {
    return hashMap.isEmpty();
  }

  @Override
  public boolean contains(Key k) {
    return hashMap.get(k) != null;
  }

  @Override
  public boolean put(Key k) {
    return hashMap.put(k, 0) == null;
  }

  @Override
  public boolean remove(Key k) {
    return hashMap.remove(k) != null;
  }

  @Override
  public Iterable<Key> keySet() {
    return hashMap.keySet();
  }

  @Override
  public Object get(Key key) {
    return null;
  }

}