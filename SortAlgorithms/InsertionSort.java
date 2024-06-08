package SortAlgorithms;

import SortAlgorithms.given.AbstractArraySort;

// Insertion Sort implementation

public class InsertionSort<K extends Comparable<K>> extends AbstractArraySort<K> {

  public InsertionSort()
  {
    name = "Insertionsort";
  }
  
  @Override
  public void sort(K[] inputArray) 
  {   
    for (int i = 1; i < inputArray.length; i++) {
      for (int j = i; (j>0) && (compare(inputArray[j-1], inputArray[j])>0); j--) {
        swap(inputArray, j, j-1);
      }
    }
  }

}