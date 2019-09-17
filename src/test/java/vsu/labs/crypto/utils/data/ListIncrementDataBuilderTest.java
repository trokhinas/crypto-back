package vsu.labs.crypto.utils.data;

import org.junit.Test;
import vsu.labs.crypto.config.UnitTest;
import vsu.labs.crypto.utils.data.ListIncrementDataBuilder;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class ListIncrementDataBuilderTest extends UnitTest {

    private static final List<String> SOURCE_LIST = Arrays.asList(
            "first_",
            "second_",
            "third"
    );
    private static final List<String> RESULT_LIST = Arrays.asList(
            "first_",
            "first_second_",
            "first_second_third"
    );

    @Test
    public void buildIncrementalList() {
        List<String> result = ListIncrementDataBuilder.buildIncrementalList(SOURCE_LIST);
        assertEquals(RESULT_LIST, result);
    }
}
