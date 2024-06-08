package BST_PQ;

import BST_PQ.given.Entry;
import BST_PQ.given.iAdaptablePriorityQueue;

// Binary search tree based priority queue implementation

public class BSTBasedPQ<Key, Value> extends BinarySearchTree<Key, Value> implements iAdaptablePriorityQueue<Key, Value> {
  
  public BSTBasedPQ() {
    super();
  }

  @Override
  public void insert(Key k, Value v) {
    put(k, v);
  }

  @Override
  public Entry<Key, Value> pop() {
    Entry<Key, Value> delEntry = top();
    if (delEntry == null) {
      return null;
    } else {
      remove(delEntry.getKey());
      return delEntry;
    }
  }

  @Override
  public Entry<Key, Value> top() {
    return findMin();
  }

  @Override
  public Key replaceKey(Entry<Key, Value> entry, Key k) {
    Entry<Key, Value> oldEntry = getNode(entry.getKey());
    if (oldEntry == null) {
      return null;
    } else if (!(entry.getValue().equals(oldEntry.getValue()))) {
      return null;
    } else {
      Key key = oldEntry.getKey();
      Value val = oldEntry.getValue();
      remove(key);
      put(k, val);
      return key;
    }
  }

  @Override
  public Key replaceKey(Value v, Key k) {
    Iterable<Key> keyset = keySet();
    Entry<Key, Value> oldEntry = null;
    int found = 0;
    for (Key key : keyset) {
      oldEntry = getNode(key);
      if (oldEntry.getValue().equals(v)) {
        found = 1;
        break;
      }
    }
    if (found == 0) {
      return null;
    } else {
      Key key = oldEntry.getKey();
      Value val = oldEntry.getValue();
      remove(key);
      put(k, val);
      return key;
    }
  }

  @Override
  public Value replaceValue(Entry<Key, Value> entry, Value v) {
    Entry<Key, Value> oldEntry = getNode(entry.getKey());
    if (oldEntry == null) {
      return null;
    } else if (!(entry.getValue().equals(oldEntry.getValue()))) {
      return null;
    }
    return put(entry.getKey(), v);
  }
    
  private Entry<Key, Value> findMin() {
    if (isEmpty()) {
      return null;
    } else {
      BinaryTreeNode<Key, Value> entry = getRoot();
      while (entry.hasLeftChild()) {
        entry = entry.getLeftChildNode();
      }
      return entry;
    }
  }

}