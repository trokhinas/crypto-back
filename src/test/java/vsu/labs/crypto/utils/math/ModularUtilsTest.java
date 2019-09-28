package vsu.labs.crypto.utils.math;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

public class ModularUtilsTest {

    private static final BigInteger LEFT_VALUE = BigInteger.valueOf(15486);
    private static final BigInteger RIGHT_VALUE = BigInteger.valueOf(14568);
    private static final BigInteger MODULO = BigInteger.valueOf(15);

    private static final BigInteger MUL_RESULT = BigInteger.valueOf(3);
    private static final BigInteger ADD_RESULT = BigInteger.valueOf(9);
    private static final BigInteger SUB_RESULT = BigInteger.valueOf(3);


    @Test
    public void modularMul() {
        BigInteger result = ModularUtils.modularMul(LEFT_VALUE, RIGHT_VALUE, MODULO);
        assertEquals(MUL_RESULT, result);
    }

    @Test
    public void modularAdd() {
        BigInteger result = ModularUtils.modularAdd(LEFT_VALUE, RIGHT_VALUE, MODULO);
        assertEquals(ADD_RESULT, result);
    }

    @Test
    public void modularSub() {
        BigInteger result = ModularUtils.modularSub(LEFT_VALUE, RIGHT_VALUE, MODULO);
        assertEquals(SUB_RESULT, result);
    }
}