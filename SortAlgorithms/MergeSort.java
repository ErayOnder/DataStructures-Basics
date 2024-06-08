package SortAlgorithms;

import SortAlgorithms.given.AbstractArraySort;

// Merge Sort implementation

public class MergeSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  public MergeSort() {
    name = "Mergesort";
  }

  @Override
  public void sort(K[] inputArray) {
    mergeSort(inputArray, 0, inputArray.length-1);
  }

  @SuppressWarnings("unchecked")
  public void merge(K[] array, int lo, int mid, int hi) {

    int lowerLenght = mid - lo + 1;
    K[] lowerArray = (K[]) new Comparable[lowerLenght];
    System.arraycopy(array, lo, lowerArray, 0, lowerLenght);

    int upperLenght = hi - mid;
    K[] upperArray = (K[]) new Comparable[upperLenght];
    System.arraycopy(array, mid + 1, upperArray, 0, upperLenght);

    int l_idx = 0;
    int u_idx = 0;
    int a_idx = lo;
    while ((l_idx < lowerLenght) && (u_idx < upperLenght)) {
      if (compare(lowerArray[l_idx], upperArray[u_idx]) <= 0) {
        array[a_idx] = lowerArray[l_idx];
        l_idx++;
      } else {
        array[a_idx] = upperArray[u_idx];
        u_idx++;
      }
      a_idx++;
    }
    while(l_idx<lowerLenght) {
      array[a_idx] = lowerArray[l_idx];
      l_idx++;
      a_idx++;
    }
    while(u_idx<upperLenght) {
      array[a_idx] = upperArray[u_idx];
      u_idx++;
      a_idx++;
    }
  }

  private void mergeSort(K[] array, int lo, int hi) {
    if (lo < hi) {
      int mid = lo + (hi - lo)/2;
      mergeSort(array, lo, mid);
      mergeSort(array, mid+1, hi);
      merge(array, lo, mid, hi);
    }
  }

}