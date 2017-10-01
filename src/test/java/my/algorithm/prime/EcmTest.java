package my.algorithm.prime;

import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

public class EcmTest {
    @Test
    public void aprtCle() throws Exception {
        Ecm ecm = new Ecm();
        assertEquals(0, ecm.aprtCle(BigInteger.valueOf(5)));
        assertEquals(1, ecm.aprtCle(BigInteger.valueOf(8)));
        assertEquals(1, ecm.aprtCle(BigInteger.valueOf(9)));
        assertEquals(0, ecm.aprtCle(BigInteger.valueOf(15485863)));
        assertEquals(1, ecm.aprtCle(BigInteger.valueOf(15485861)));
    }

}