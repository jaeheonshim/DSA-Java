package algorithms;

import util.OutUtil;
import util.RandUtil;

/**
 * Insertion sort is another elementary sorting algorithm. For every element we encounter, we will swap it with every
 * larger element to the left. This works especially well on partially sorted arrays, because the elements don't have
 * to move as far back. For a fully sorted array, the algorithm runs in linear time.
 */
public class InsertionSort {
    public static void sort(Comparable[] a) {
        for(int i = 1; i < a.length; i++) { // there's no point in starting at 0, since there would be no elements to swap to the left.
            for(int j = i; j > 0; j--) {
                // index j represents the location of the element at position i as it is moved throughout the array.
                if(a[j].compareTo(a[j - 1]) < 0) {
                    Comparable temp = a[j];
                    a[j] = a[j - 1];
                    a[j - 1] = temp;
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Double[] array = RandUtil.randDoubleArr(10);

        System.out.println("Before sort");
        OutUtil.arrayPrint(array);

        InsertionSort.sort(array);

        System.out.println("After sort");
        OutUtil.arrayPrint(array);
    }
}
