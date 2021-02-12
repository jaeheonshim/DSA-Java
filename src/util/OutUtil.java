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

    public static void arrayPrint(Object[] a) {
        System.out.print("[");
        for(int i = 0; i < a.length; i++) {
            System.out.print(a[i]);
            if(i != a.length - 1) {
                System.out.print(", ");
            }
        }

        System.out.println("]");
    }
}
