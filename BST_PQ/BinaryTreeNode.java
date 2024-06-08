package BST_PQ;

import BST_PQ.given.Entry;

public class BinaryTreeNode<Key, Value> extends Entry<Key, Value> {
  
  private BinaryTreeNode<Key, Value> parentNode, leftChildNode, rightChildNode;

  public BinaryTreeNode(Key k, Value v) {
    super(k, v);
    this.parentNode = null;
    this.leftChildNode = null;
    this.rightChildNode = null;
  }

  public BinaryTreeNode<Key, Value> getParentNode() {
    return parentNode;
  }

  public BinaryTreeNode<Key, Value> getLeftChildNode() {
    return leftChildNode;
  }

  public BinaryTreeNode<Key, Value> getRightChildNode() {
    return rightChildNode;
  }

  public void setParentNode(BinaryTreeNode<Key, Value> parentNode) {
    this.parentNode = parentNode;
  }

  public void setLeftChildNode(BinaryTreeNode<Key, Value> leftChildNode) {
    this.leftChildNode = leftChildNode;
    if (leftChildNode != null) {
      leftChildNode.setParentNode(this);
    }
  }

  public void setRightChildNode(BinaryTreeNode<Key, Value> rightChildNode) {
    this.rightChildNode = rightChildNode;
    if (rightChildNode != null) {
      rightChildNode.setParentNode(this);
    }
  }

  public boolean hasLeftChild() {
    return leftChildNode != null;
  }

  public boolean hasRightChild() {
    return rightChildNode != null;
  }

  public boolean hasTwoChild() {
    return (hasLeftChild() && hasRightChild());
  }

}