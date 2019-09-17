package vsu.labs.crypto.utils.math;

import org.junit.Test;

import static org.junit.Assert.*;

public class MathUtilsTest {

    @Test
    public void isCoprimeIntegers() {
    }

    @Test
    public void isCoprimeIntegers1() {
    }

    @Test
    public void eulerPrimeFunction() {
    }

    @Test
    public void getGCD() {
    }

    @Test
    public void isPrime() {
    }


    @Test
    public void isPrimeDefault() {
    }

    @Test
    public void getPrimitiveRootModuloN() {
        Long modulo = 7L;
        Long result = MathUtils.getPrimitiveRootModuloN(modulo);

        assertEquals(Long.valueOf(3), result);
    }
}
