package kyu4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HammingNumbersTest {

    @Test
    public void Test1() {
        assertEquals(1, HammingNumbers.hamming(1), "hamming(1) should be 1");
        assertEquals(2, HammingNumbers.hamming(2), "hamming(2) should be 2");
        assertEquals(3, HammingNumbers.hamming(3), "hamming(3) should be 3");
        assertEquals(4, HammingNumbers.hamming(4), "hamming(4) should be 4");
        assertEquals(5, HammingNumbers.hamming(5), "hamming(5) should be 5");
        assertEquals(6, HammingNumbers.hamming(6), "hamming(6) should be 6");
        assertEquals(8, HammingNumbers.hamming(7), "hamming(7) should be 8");
        assertEquals(9, HammingNumbers.hamming(8), "hamming(8) should be 9");
        assertEquals(10, HammingNumbers.hamming(9), "hamming(9) should be 10");
        assertEquals(12, HammingNumbers.hamming(10), "hamming(10) should be 12");
        assertEquals(15, HammingNumbers.hamming(11), "hamming(11) should be 15");
        assertEquals(16, HammingNumbers.hamming(12), "hamming(12) should be 16");
        assertEquals(18, HammingNumbers.hamming(13), "hamming(13) should be 18");
        assertEquals(20, HammingNumbers.hamming(14), "hamming(14) should be 20");
        assertEquals(24, HammingNumbers.hamming(15), "hamming(15) should be 24");
        assertEquals(25, HammingNumbers.hamming(16), "hamming(16) should be 25");
        assertEquals(27, HammingNumbers.hamming(17), "hamming(17) should be 27");
        assertEquals(30, HammingNumbers.hamming(18), "hamming(18) should be 30");
        assertEquals(32, HammingNumbers.hamming(19), "hamming(19) should be 32");
    }
}
