package util;

import java.util.Iterator;

public class OutUtil {
    public static void iterablePrint(Iterable iterable) {
        System.out.print("[");

        Iterator iterator = iterable.iterator();
        while(iterator.hasNext()) {
            Object o = iterator.next();
            System.out.print(o + (iterator.hasNext() ? ", " : "]"));
        }

        System.out.println();
    }
}
