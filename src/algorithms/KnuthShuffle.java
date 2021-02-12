package algorithms;

/**
 * This is a simple linear-time shuffling algorithm. Although shuffling seems like a trivial task, it is important that
 * the shuffling is uniform to ensure your code functions properly and cannot be exploited.
 *
 * The knuth shuffle goes through every element in the array, and swaps that element with a random element between 0 and i
 * (inclusive) where i is the location of that element.
 */
public class KnuthShuffle {
    public static void shuffle(Object[] a) {
        for(int i = 0; i < a.length; i++) {
            int r = (int) (Math.random() * (i + 1));
            Object temp = a[r];
            a[r] = a[i];
            a[i] = temp;
        }
    }
}
