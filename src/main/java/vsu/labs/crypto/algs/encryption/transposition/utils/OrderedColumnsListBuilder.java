package vsu.labs.crypto.algs.encryption.transposition.utils;

import vsu.labs.crypto.exceptions.algs.encryption.transposition.DuplicateColumnException;
import vsu.labs.crypto.exceptions.algs.encryption.transposition.MissedColumnException;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class OrderedColumnsListBuilder {
    private OrderedColumnsListBuilder() { }

    public static List<Integer> buildList(BigInteger key) {
        checkKey(key.toString());
        Map<Integer, Integer> map = new HashMap<>();
        String stringKey = key.toString();

        for (int i = 0; i < stringKey.length(); i++) {
            int orderNumber = Integer.valueOf(String.valueOf(stringKey.charAt(i)));
            map.put(orderNumber, i);
        }
        return new ArrayList<>(map.values());
    }

    private static void checkKey(String key) {
        for (int i = 1; i <= key.length() ; i++) {
            String strValue = String.valueOf(i);
            int first = key.indexOf(strValue);
            int last = key.lastIndexOf(strValue);

            if (first == -1) {
               throw new MissedColumnException(i);
            }
            if (first != last) {
                throw new DuplicateColumnException(i);
            }
        }
    }
}
