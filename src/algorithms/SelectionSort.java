package algorithms;

import util.OutUtil;
import util.RandUtil;

/**
 * Selection sort is a very basic sort that works by finding the smallest/largest element, index by index.
 *
 * For every iteration of a loop, the algorithm will look for every element after that index and find the smallest one.
 * That element will replace the one at the current index.
 *
 * This algorithm is quite inefficient because even for a partially sorted array, the algorithm must look through every
 * element. Even if the array is fully sorted, the algorithm will still look through every element!
 *
 * One benefit to this algorithm is that it makes N changes to the array itself.
 */
public class SelectionSort {
    public static void sort(Comparable[] a) {
        for(int i = 0; i < a.length; i++) {
            int min = i; // this will contain the index of the min element. We set it to i so that if no smaller element to the right is found, we know that the smallest element is already in place.
            for(int j = i + 1; j < a.length; j++) {
                if(a[j].compareTo(a[min]) < 0) {
                    min = j; // if the current element is less than the min element, set the new min element.
                }
            }

            // swap
            Comparable temp = a[min];
            a[min] = a[i];
            a[i] = temp;
        }
    }

    public static void main(String[] args) {
        Double[] array = RandUtil.randDoubleArr(10);

        System.out.println("Before sort");
        OutUtil.arrayPrint(array);

        SelectionSort.sort(array);

        System.out.println("After sort");
        OutUtil.arrayPrint(array);
    }
}
