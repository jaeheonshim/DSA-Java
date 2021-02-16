package algorithms;

import util.OutUtil;
import util.RandUtil;

/**
 * Mergesort is a widely used recursive sorting algorithm that runs in NlgN time.
 * It works with the concept of merging two previously sorted arrays, in order to create one large sorted array containing
 * the elements from the two small sorted arrays.
 *
 * By splitting the array into two until each half only has 1 element left, you can work from the bottom up and merge
 * those elements in order to sort.
 *
 * It can also be helpful to use insertion sort when there are a few number of elements to sort, as using mergesort for
 * a small problem space can often be inefficient.
 */
public class MergeSort {
    /**
     * Sort the input array using Merge Sort.
     * @param a Array of items to sort.
     */
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];

        sort(a, aux, 0, a.length - 1);
    }

    /**
     * Sort a portion of the input array.
     * @param a Array to sort
     * @param aux Auxiliary array instantiated out of recursive sequence
     * @param lo First element of portion to sort
     * @param hi Last element of portion to sort (inclusive)
     */
    private static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if(hi <= lo) {
            // if hi is equal to lo, that means there is only one element. In that case, the array is already sorted and we can just break out.
            // (this is our recursive edge case.)
            return;
        }

        int mid = lo + (hi - lo) / 2; // this is the midpoint we use to split the array into two parts

        /*
        Sort both halves of the array. Due to the recursive nature, this will recurse until there is only one element to sort (edge case.)
        Then, those halves will be merged until the full array is sorted. (A diagram will help).
         */
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        // finally, once both halves are sorted, we can merge them together to create one big sorted portion.
        merge(a, aux, lo, mid, hi);
    }

    /**
     * Merges two different parts of the array. The two arrays are: [lo, mid] and [mid + 1, hi].
     *
     * aux is an auxiliary array where the elements are copied so that they can be referenced while they are copied into a.
     * it is passed as a parameter so that a new array is not instantiated multiple times.
     *
     * @param a The main array with the elements to sort
     * @param aux The auxiliary array
     * @param lo The first index of the first array
     * @param mid The last index of the first array
     * @param hi The last index of the second array
     */
    private static void merge(Comparable[] a, Comparable[] aux, int lo, int mid, int hi) {
        // copy a over to aux
        for(int i = lo; i <= hi; i++) {
            aux[i] = a[i];
        }

        // merge time
        int i = lo, j = mid + 1; // i represents the pointer in the first array, j represents the pointer in the second array.
        for(int k = lo; k <= hi; k++) {
            // k represents the index to place the next copied element.

            if(i > mid) // if the first array has been exhausted just copy from the second one
                a[k] = aux[j++];
            else if(j > hi) // same thing if other array is exhausted
                a[k] = aux[i++];
            else if(aux[i].compareTo(aux[j]) < 0) // if the element in the first array comes before the one in the second
                a[k] = aux[i++]; // copy over the element in the first array in place
            else
                a[k] = aux[j++]; // otherwise, copy over the second array.
        }

        // merged.
    }

    public static void main(String[] args) {
        Double[] array = RandUtil.randDoubleArr(10);

        System.out.println("Before sort");
        OutUtil.arrayPrint(array);

        MergeSort.sort(array);

        System.out.println("After sort");
        OutUtil.arrayPrint(array);
    }
}
