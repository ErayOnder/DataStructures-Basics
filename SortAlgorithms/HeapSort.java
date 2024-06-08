package SortAlgorithms;

import SortAlgorithms.given.AbstractArraySort;

// Heap Sort implementation

public class HeapSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  public HeapSort() {
    name = "Heapsort";
  }

  @Override
  public void sort(K[] inputArray) {
    heapify(inputArray);
    for (int i = inputArray.length - 1; i > 0; i--) {
      swap(inputArray, 0, i);
      downheap(inputArray, 0, i);
    }
  }

  public void heapify(K[] inputArray) {
    for (int i = (inputArray.length/2) - 1; i >= 0; i--) {
      downheap(inputArray, i, inputArray.length);
    }
  }

  protected void downheap(K[] inputArray, int i, int heapSize) {
    while (i*2 + 1 < heapSize) {
      int left_idx = i*2 + 1;
      int right_idx = i*2 + 2;
      int larger_idx = left_idx;
      if ((right_idx < heapSize) && (compare(inputArray[right_idx], inputArray[left_idx]) > 0)) {
        larger_idx = right_idx;
      }
      if (compare(inputArray[i], inputArray[larger_idx]) >= 0) {
        break;
      } else {
        swap(inputArray, i, larger_idx);
        i = larger_idx;
      }
    }
  }

}