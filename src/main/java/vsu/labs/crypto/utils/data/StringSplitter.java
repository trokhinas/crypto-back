package vsu.labs.crypto.utils.data;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class StringSplitter {
    private final int partitionSize;

    private StringSplitter(int partitionSize) {
        this.partitionSize = partitionSize;
    }

    public static StringSplitter withPartitionSize(int partitionSize) {
        return new StringSplitter(partitionSize);
    }

    public List<String> splitIntoParts(@NotNull String source) {
        if (partitionSize > source.length())
            return Collections.singletonList(source);

        List<String> parts = new ArrayList<>();
        for (int i = 0; i < source.length(); i += partitionSize) {
            String part = getPartition(source, i);
            parts.add(part);
        }
        return parts;
    }

    private String getPartition(String source, int begin) {
        int end = Math.min(begin + partitionSize, source.length());
        return source.substring(begin, end);
    }
}
