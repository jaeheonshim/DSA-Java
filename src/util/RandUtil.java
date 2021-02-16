package util;

public class RandUtil {
    public static Double[] randDoubleArr(int len) {
        Double[] darr = new Double[len];
        for(int i = 0; i < len; i++) {
            darr[i] = Math.random();
        }

        return darr;
    }

    public static Integer[] uniqueRandIntArr(int len, int lower, int upper) {
        if(upper - lower < len) {
            throw new IllegalArgumentException();
        }

        Integer[] arr = new Integer[len];
        for(int i = 0; i < len; i++) {
            arr[i] = (int) (Math.random() * (upper - lower)) + lower;
            for(int j = 0; j < i; j++) {
                if(arr[i].equals(arr[j])) {
                    i--;
                    break;
                }
            }
        }

        return arr;
    }
}
