package vsu.labs.crypto.algs.encryption.transposition.utils;

public class StringWithCounter {
    private final String str;
    private int counter;

    private StringWithCounter(String str) {
        this.str = str;
        counter = 0;
    }

    public static StringWithCounter withString(String str) {
        return new StringWithCounter(str);
    }

    public String getOne() {
        if (counter < str.length())
            return str.substring(counter , ++counter);
        throw new IllegalStateException();
    }
}
