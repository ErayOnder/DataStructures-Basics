package BST_PQ;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import BST_PQ.given.iMap;
import BST_PQ.given.iBinarySearchTree;

// Vanilla binary search tree implementation using a linked tree representation

public class BinarySearchTree<Key, Value> implements iBinarySearchTree<Key, Value>, iMap<Key, Value> {
  
  private BinaryTreeNode<Key, Value> root;
  private Comparator<Key> comparator;
  private List<Key> keySet;
  private int size;
  
  public BinarySearchTree() {
    this.root = null;
    this.comparator = null;
    this.keySet = new ArrayList<Key>();
    this.size = 0;
  }

  @Override
  public Value get(Key k) {
    BinaryTreeNode<Key, Value> node = getNode(k);
    if (node != null) {
      return node.getValue();
    }
    return null;
  }

  @Override
  public Value put(Key k, Value v) {
    if (getRoot() == null) {
      root = new BinaryTreeNode<Key,Value>(k, v);
      keySet.add(k);
      size++;
    } else {
      BinaryTreeNode<Key, Value> node = getNode(k);
      if (node != null) {
        Value oldValue = node.getValue();
        node.setValue(v);
        return oldValue;
      } else {
        node = getRoot();
        int cmpResult;
        while (true) {
          cmpResult = comparator.compare(k, node.getKey());
          if (cmpResult < 0) {
            if (node.hasLeftChild()) {
              node = node.getLeftChildNode();
            } else {
              BinaryTreeNode<Key, Value> newNode = new BinaryTreeNode<Key,Value>(k, v);
              node.setLeftChildNode(newNode);
              keySet.add(k);
              size++;
              break;
            }
          } else if (cmpResult > 0) {
            if (node.hasRightChild()) {
              node = node.getRightChildNode();
            } else {
              BinaryTreeNode<Key, Value> newNode = new BinaryTreeNode<Key,Value>(k, v);
              node.setRightChildNode(newNode);
              keySet.add(k);
              size++;
              break;
            }
          }
        }
      }
    }
    return null;
  }

  @Override
  public Value remove(Key k) {
    BinaryTreeNode<Key, Value> node = getNode(k);
    if (node == null) {
      return null;
    } else {
      Value value = node.getValue();
      keySet.remove(k);
      if (isExternal(node)) {
        if (isRoot(node)) {
          root = null;
        } else {
          if (isLeftChild(node)) {
            node.getParentNode().setLeftChildNode(null);
          } else {
            node.getParentNode().setRightChildNode(null);
          }
        }
        node = null;
        size--;
      } else if (!node.hasTwoChild()) {
        if (isRoot(node)) {
          if (node.hasLeftChild()) {
            root = node.getLeftChildNode();
            root.setParentNode(null);
          } else {
            root = node.getRightChildNode();
            root.setParentNode(null);
          }
        } else {
          if (isLeftChild(node)) {
            if (node.hasLeftChild()) {
              node.getParentNode().setLeftChildNode(node.getLeftChildNode());
            } else {
              node.getParentNode().setLeftChildNode(node.getRightChildNode());
            }
          } else {
            if (node.hasLeftChild()) {
              node.getParentNode().setRightChildNode(node.getLeftChildNode());
            } else {
              node.getParentNode().setRightChildNode(node.getRightChildNode());
            }
          }
        }
        node = null;
        size--;
      } else {
        BinaryTreeNode<Key, Value> maxLeftSubtreeNode = findMaxNode(node.getLeftChildNode());
        Key maxNodeKey = maxLeftSubtreeNode.getKey();
        Value maxNodeValue = maxLeftSubtreeNode.getValue();
        if (isExternal(maxLeftSubtreeNode)) {
          if (isLeftChild(maxLeftSubtreeNode)) {
            maxLeftSubtreeNode.getParentNode().setLeftChildNode(null);
          } else {
            maxLeftSubtreeNode.getParentNode().setRightChildNode(null);
          }
        } else {
          if (maxLeftSubtreeNode.hasLeftChild()) {
            if (isLeftChild(maxLeftSubtreeNode)) {
                maxLeftSubtreeNode.getParentNode().setLeftChildNode(maxLeftSubtreeNode.getLeftChildNode());
            } else {
                maxLeftSubtreeNode.getParentNode().setRightChildNode(maxLeftSubtreeNode.getLeftChildNode());
            }
          }
        }
        maxLeftSubtreeNode = null;
        node.setKey(maxNodeKey);
        node.setValue(maxNodeValue);
        size--;
      }
      return value;
    }
  }

  @Override
  public Iterable<Key> keySet() {
    return keySet;
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
  public BinaryTreeNode<Key, Value> getRoot() {
    return root;
  }

  @Override
  public BinaryTreeNode<Key, Value> getParent(BinaryTreeNode<Key, Value> node) {
    return node.getParentNode();
  }

  @Override
  public boolean isInternal(BinaryTreeNode<Key, Value> node) {
    return node != null && (node.hasLeftChild() || node.hasRightChild());
  }

  @Override
  public boolean isExternal(BinaryTreeNode<Key, Value> node) {
    return node == null || !(node.hasLeftChild() || node.hasRightChild());
  }

  @Override
  public boolean isRoot(BinaryTreeNode<Key, Value> node) {
    return node.getParentNode() == null;
  }

  @Override
  public BinaryTreeNode<Key, Value> getNode(Key k) {
    BinaryTreeNode<Key, Value> node = getRoot();
    int cmpResult;
    while(node != null) {
      cmpResult = comparator.compare(k, node.getKey());
      if (cmpResult < 0) {
        node = node.getLeftChildNode();
      } else if (cmpResult > 0) {
        node = node.getRightChildNode();
      } else {
        return node;
      }
    } 
    return null;
  }

  @Override
  public Value getValue(Key k) {
    BinaryTreeNode<Key, Value> node = getNode(k);
    if (node != null) {
      return node.getValue();
    }
    return null;
  }

  @Override
  public BinaryTreeNode<Key, Value> getLeftChild(BinaryTreeNode<Key, Value> node) {
    if (node.hasLeftChild()) {
      return node.getLeftChildNode();
    }
    return null;
  }

  @Override
  public BinaryTreeNode<Key, Value> getRightChild(BinaryTreeNode<Key, Value> node) {
    if (node.hasRightChild()) {
      return node.getRightChildNode();
    }
    return null;
  }

  @Override
  public BinaryTreeNode<Key, Value> sibling(BinaryTreeNode<Key, Value> node) {
    if (isRoot(node)) {
      return null;
    } else if (isLeftChild(node) && node.getParentNode().hasRightChild()) {
      return node.getParentNode().getRightChildNode();
    } else if (isRightChild(node) && node.getParentNode().hasLeftChild()) {
      return node.getParentNode().getLeftChildNode();
    }
    return null;
  }

  @Override
  public boolean isLeftChild(BinaryTreeNode<Key, Value> node) {
    if (!isRoot(node)) {
      int cmpResult = comparator.compare(node.getKey(),node.getParentNode().getKey());
      if (cmpResult < 0) {
        return true;
      }
    }
    return false;
  }

  @Override
  public boolean isRightChild(BinaryTreeNode<Key, Value> node) {
    if (!isRoot(node)) {
      int cmpResult = comparator.compare(node.getKey(),node.getParentNode().getKey());
      if (cmpResult > 0) {
        return true;
      }
    }
    return false;
  }

  @Override
  public List<BinaryTreeNode<Key, Value>> getNodesInOrder() {
    List<BinaryTreeNode<Key, Value>> orderedNodesList = new ArrayList<>();
    inOrderTraversal(getRoot(), orderedNodesList);
    return orderedNodesList;
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
  public BinaryTreeNode<Key, Value> ceiling(Key k) {
    return findCeiling(getRoot(), k);
  }

  @Override
  public BinaryTreeNode<Key, Value> floor(Key k) {
    return findFloor(getRoot(), k);
  }

  private BinaryTreeNode<Key, Value> findCeiling(BinaryTreeNode<Key, Value> node, Key k) {
    if (node == null) {
      return null;
    } else {
      int cmpResult = comparator.compare(k, node.getKey());
      if (cmpResult == 0) {
        return node;
      } else if (cmpResult > 0) {
        return findCeiling(node.getRightChildNode(), k);
      } else {
        BinaryTreeNode<Key, Value> leftSubtree = findCeiling(node.getLeftChildNode(), k);
        return (leftSubtree != null) ? leftSubtree : node;
      }
    }
  }

  private BinaryTreeNode<Key, Value> findFloor(BinaryTreeNode<Key, Value> node, Key k) {
    if (node == null) {
      return null;
    } else {
      int cmpResult = comparator.compare(k, node.getKey());
      if (cmpResult == 0) {
        return node;
      } else if (cmpResult < 0) {
        return findFloor(node.getLeftChildNode(), k);
      } else {
        BinaryTreeNode<Key, Value> rightSubtree = findFloor(node.getRightChildNode(), k);
        return (rightSubtree != null) ? rightSubtree : node;
      }
    }
  }

  private void inOrderTraversal(BinaryTreeNode<Key, Value> node, List<BinaryTreeNode<Key, Value>> list) {
    if (node == null) {
      return;
    }
    inOrderTraversal(node.getLeftChildNode(), list);
    list.add(node);
    inOrderTraversal(node.getRightChildNode(), list);
  }

  private BinaryTreeNode<Key, Value> findMaxNode(BinaryTreeNode<Key, Value> node) {
    while (node.hasRightChild()) {
      node = node.getRightChildNode();
    }
    return node;
  }

}