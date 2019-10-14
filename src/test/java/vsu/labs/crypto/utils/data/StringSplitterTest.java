package vsu.labs.crypto.utils.data;

import org.junit.Before;
import org.junit.Test;
import vsu.labs.crypto.config.UnitTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;


public class StringSplitterTest extends UnitTest {

    private static final int PARTITION_SIZE = 2;

    private static final String TEST_SOURCE_STR = "ff,aa,bb,cc,g";
    private static final List<String> EXPECTED_SOURCE_LIST = Arrays.asList(TEST_SOURCE_STR.split(","));

    private StringSplitter splitter;

    @Before
    public void setUp() throws Exception {
        splitter = StringSplitter.withPartitionSize(PARTITION_SIZE);
    }

    @Test
    public void splitIntoParts() {
        String strWithoutComma = TEST_SOURCE_STR.replace(",", "");
        List<String> result = splitter.splitIntoParts(strWithoutComma);

        assertEquals(EXPECTED_SOURCE_LIST, result);
    }
}
