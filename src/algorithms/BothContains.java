package algorithms;

import util.OutUtil;
import util.RandUtil;

/**
 * This is an example of a simple problem that can be solved with sorting.
 *
 * Suppose we have two distinct arrays, containing n elements each. Design a subquadratic algorithm to count the number
 * of elements that are contained in both arrays.
 *
 * Solution: If we sort both arrays, we can maintain two pointers at the beginning. Until both pointers reach the end of the array,
 * we will increment whichever pointer points to the smaller element. Then, we will be able to compare duplicates.
 */
public class BothContains {
    public static int countBothQuadratic(Integer[] a, Integer[] b) {
        int count = 0;
        for(int i = 0; i < a.length; i++) {
            for(int j = 0; j < a.length; j++) {
                if(a[i].equals(b[j])) {
                    count++;
                }
            }
        }

        return count;
    }

    public static int countBoth(Integer[] a, Integer[] b) {
        ShellSort.sort(a);
        ShellSort.sort(b);

        int count = 0;

        for(int i = 0, j = 0; i < a.length && j < a.length;) {
            if(a[i].equals(b[j])) {
                count++;
                i++;
                j++;
                continue;
            }

            if(a[i].compareTo(b[j]) <= 0) {
                i++;
            } else {
                j++;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        for(int i = 0; i < 100000; i++) {
            Integer[] a1 = RandUtil.uniqueRandIntArr(10, 0, 15);
            Integer[] a2 = RandUtil.uniqueRandIntArr(10, 0, 15);

            if(countBoth(a1, a2) != countBothQuadratic(a1, a2)) {
                throw new RuntimeException();
            }
        }
    }
}
