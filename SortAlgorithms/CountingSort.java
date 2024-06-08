package SortAlgorithms;

import SortAlgorithms.given.AbstractArraySort;

// Counting Sort implementation

public class CountingSort<K extends Comparable<K>> extends AbstractArraySort<K> {
  
  public CountingSort()
  {
    name = "Countingsort";
  }
  
  @Override
  public void sort(K[] inputArray) {
    
    if(inputArray == null)
    {
      System.out.println("Null array");
      return;
    }
    if(inputArray.length < 1)
    {
      System.out.println("Empty array");
      return;
    }   
    
    if(!(inputArray[0] instanceof Integer)) {
      System.out.println("Can only sort integers!");
      return;
    }

    Integer[] array = (Integer[]) inputArray;
    int max = findMax(array);
    int[] count = new int[max+1];
    for (int integer: array) {
      count[integer]++;
    }
    cumulate(count);
    Integer[] sortedArray = new Integer[array.length];
    for (int i = array.length - 1; i >= 0; i--) {
      sortedArray[count[array[i]] - 1] = array[i];
      count[array[i]]--;
    }
    System.arraycopy(sortedArray, 0, inputArray, 0, array.length);
  }

  private int findMax(Integer[] array) {
    int max = array[0];
    for (int i = 1; i < array.length; i++) {
      if (array[i] > max) {
        max = array[i];
      }
    }
    return max;
  }

  private void cumulate(int[] array) {
    for (int i = 1; i < array.length; i++) {
      array[i] += array[i-1];
    }
  }

}