package SortAlgorithms;

import java.util.Random;

import SortAlgorithms.given.AbstractArraySort;

// Quick Sort implementation

public class QuickSort<K extends Comparable<K>> extends AbstractArraySort<K> {  

  public QuickSort()
  {
    name = "Quicksort";
  }
  
  public class indexPair {
    public int p1, p2;
    
    indexPair(int pos1, int pos2)
    {
      p1 = pos1;
      p2 = pos2;
    }
    
    public String toString()
    {
      return "(" + Integer.toString(p1) + ", " + Integer.toString(p2) + ")";
    }
  }
  
  @Override
  public void sort(K[] inputArray) {
    quickSort(inputArray, 0, inputArray.length - 1);
  }
  
  public int partition(K[] inputArray, int lo, int hi, int p) {
    K pivot = inputArray[p];
    swap(inputArray, p, hi);

    int leftP = lo;
    int rightP = hi;

    while (leftP < rightP) {
      while ((leftP < rightP) && (compare(inputArray[leftP], pivot) <= 0)) {
        leftP++;
      }
      while ((leftP < rightP) && (compare(inputArray[rightP], pivot) >= 0)) {
        rightP--;
      }
      swap(inputArray, leftP, rightP);
    }
    swap(inputArray, leftP, hi);
    return leftP;
  }
  
  protected int pickPivot(K[] inpuArray, int lo, int hi) {
    return new Random().nextInt(hi - lo) + lo;
  }

  private void quickSort(K[] array, int lo, int hi) {
    if (lo < hi) {
      int p_idx = pickPivot(array, lo, hi);
      int leftP = partition(array, lo, hi, p_idx);
      quickSort(array, lo, leftP - 1);
      quickSort(array, leftP + 1, hi);
    }
  }

}