package algorithms;

import jdk.nashorn.tools.Shell;
import util.OutUtil;
import util.RandUtil;

/**
 * Shell sort is a sorting algorithm that is very similar to insertion sort. However, while insertion sort moves elements
 * back by 1 at a time, shell sort moves elements back multiple places. We keep doing this with less and less stride lengths
 * until the stride length is 1, at which point the array will be sorted. The idea is that with larger increments, the
 * size of the subarrays will be small. Then, when the increments decrease, the arrays will be partially sorted making
 * insertion sort fast.
 *
 * Another important question to consider in this algorithm is the increment sequence (the sequence of stride lengths to
 * be used in the insertion sorts.) A popular increment sequence is 3x + 1 (1, 4, 13, 40, 121).
 */
public class ShellSort {
    public static void sort(Comparable[] a) {
        int h = 1;
        while(h < a.length / 3) h = h * 3 + 1; // find the largest number for our increment sequence

        while(h >= 1) {
            for(int i = h; i < a.length; i++) {
                for(int j = i; j >= h; j -= h) { // j cannot be less than h because then j - h would be < 0.
                    // Just like in insertion sort, j will represent the position of the element as it is moved throughout.
                    if(a[j].compareTo(a[j - h]) < 0) {
                        // if the element is less than the element h spots to the left
                        Comparable temp = a[j];
                        a[j] = a[j - h];
                        a[j - h] = temp;
                    } else {
                        break;
                    }

                }
            }
            h /= 3; // go backwards and find the next largest number to use
        }
    }

    public static void main(String[] args) {
        Double[] array = RandUtil.randDoubleArr(20);

        System.out.println("Before sort");
        OutUtil.arrayPrint(array);

        ShellSort.sort(array);

        System.out.println("After sort");
        OutUtil.arrayPrint(array);
    }
}
