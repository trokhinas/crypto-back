package vsu.labs.crypto.utils;

import java.util.ArrayList;
import java.util.List;

public final class ListIncrementDataBuilder {

    private ListIncrementDataBuilder() { }

    public static List<String> buildIncrementalList(List<String> parts) {
        List<String> incrementList = new ArrayList<>();

        String prevPart = parts.get(0);
        incrementList.add(prevPart);
        for (int i = 1; i < parts.size(); i++) {
            String concatPart = prevPart + parts.get(i);
            incrementList.add(concatPart);
            prevPart = concatPart;
        }
        return incrementList;
    }
}
