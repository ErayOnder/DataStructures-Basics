package BST_PQ;

import java.util.ArrayList;
import java.util.Comparator;

import BST_PQ.given.Entry;
import BST_PQ.given.iAdaptablePriorityQueue;

// Array based heap implementation

public class ArrayBasedHeap<Key, Value> implements iAdaptablePriorityQueue<Key, Value> {
  
  protected ArrayList<Entry<Key,Value>> nodes;
  private Comparator<Key> comparator;
  private int size;

  public ArrayBasedHeap() {
    this.nodes = new ArrayList<>();
    this.comparator = null;
    this.size = 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    return size == 0;
  }

  @Override
  public void setComparator(Comparator<Key> C) {
    this.comparator = C;
  }

  @Override
  public Comparator<Key> getComparator() {
    return comparator;
  }

  @Override
  public void insert(Key k, Value v) {
    Entry<Key, Value> newEntry = new Entry<>(k, v);
    nodes.add(newEntry);
    size++;
    climbUp(nodes.indexOf(newEntry));
  }

  @Override
  public Entry<Key, Value> pop() {
    if (size == 0) {
      return null;
    } else {
      Entry<Key, Value> delEntry = top();
      swap(0, size() - 1);
      nodes.remove(delEntry);
      size--;
      climbDown(0);
      return delEntry;
    }
  }

  @Override
  public Entry<Key, Value> top() {
    return isEmpty() ? null : nodes.get(0);
  }

  @Override
  public Value remove(Key k) {
    Entry<Key, Value> delEntry = findEntryByKey(k);
    if (delEntry == null) {
      return null;
    } else {
      int idx = nodes.indexOf(delEntry);
      swap(idx, size() - 1);
      nodes.remove(size() - 1);
      size--;
      if (idx < size()) {
        climbDown(idx);
        climbUp(idx);
      }
      return delEntry.getValue();
    }
  }

  @Override
  public Key replaceKey(Entry<Key, Value> entry, Key k) {
    Entry<Key, Value> oldEntry = findEntry(entry);
    if (oldEntry == null) {
      return null;
    } else {
      int idx = nodes.indexOf(oldEntry);
      Key oldKey = oldEntry.getKey();
      oldEntry.setKey(k);
      climbDown(idx);
      climbUp(idx);
      return oldKey;
    }
  }

  @Override
  public Key replaceKey(Value v, Key k) {
    Entry<Key, Value> oldEntry = findEntryByValue(v);
    if (oldEntry == null) {
      return null;
    } else {
      int idx = nodes.indexOf(oldEntry);
      Key oldKey = oldEntry.getKey();
      oldEntry.setKey(k);
      climbDown(idx);
      climbUp(idx);
      return oldKey;
    }
  }

  @Override
  public Value replaceValue(Entry<Key, Value> entry, Value v) {
    Entry<Key, Value> oldEntry = findEntry(entry);
    if (oldEntry == null) {
      return null;
    } else {
      Value oldValue = oldEntry.getValue();
      oldEntry.setValue(v);
      return oldValue;
    }
  }

  private void climbUp(int idx) {
    if (idx > 0) {
      while (isLess(idx, (idx-1)/2)) {
        swap(idx, (idx-1)/2);
        idx = (idx-1)/2;
      }
    }
  }

  private void climbDown(int idx) {
    while (idx*2 + 1 < size) {
      int leftIdx = 2*idx + 1;
      int biggerChild = leftIdx;
      if ((leftIdx < size - 1) && (isLess(leftIdx + 1, leftIdx))) {
        biggerChild = leftIdx + 1;
      } 
      if (isLess(idx, biggerChild)) {
        break;
      }
      swap(idx, biggerChild);
      idx = biggerChild;
    }
  }
  
  private boolean isLess(int idx1, int idx2) {
    return comparator.compare(nodes.get(idx1).getKey(), nodes.get(idx2).getKey()) < 0;
  }

  private void swap(int idx1, int idx2) {
    Entry<Key, Value> temp = nodes.get(idx1);
    nodes.set(idx1, nodes.get(idx2));
    nodes.set(idx2, temp);
  }

  private Entry<Key, Value> findEntry(Entry<Key, Value> entry) {
    for (Entry<Key, Value> node : nodes) {
      if (node.equals(entry)) {
        return node;
      }
    }
    return null;
  }

  private Entry<Key, Value> findEntryByKey(Key k) {
    for (Entry<Key, Value> node : nodes) {
      if (node.getKey().equals(k)) {
        return node;
      }
    }
    return null;
  }

  private Entry<Key, Value> findEntryByValue(Value v) {
    for (Entry<Key, Value> node : nodes) {
      if (node.getValue().equals(v)) {
        return node;
      }
    }
    return null;
  }

}