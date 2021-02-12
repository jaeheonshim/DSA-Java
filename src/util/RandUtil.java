package util;

public class RandUtil {
    public static Double[] randDoubleArr(int len) {
        Double[] darr = new Double[len];
        for(int i = 0; i < len; i++) {
            darr[i] = Math.random();
        }

        return darr;
    }
}
