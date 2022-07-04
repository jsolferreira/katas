package kyu4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NextSmallerNumberWithTheSameDigitsTest {

    @Test
    public void basicTests() {
        assertEquals(12, NextSmallerNumberWithTheSameDigits.nextSmaller(21));
        assertEquals(790, NextSmallerNumberWithTheSameDigits.nextSmaller(907));
        assertEquals(513, NextSmallerNumberWithTheSameDigits.nextSmaller(531));
        assertEquals(-1, NextSmallerNumberWithTheSameDigits.nextSmaller(1027));
        assertEquals(414, NextSmallerNumberWithTheSameDigits.nextSmaller(441));
        assertEquals(123456789, NextSmallerNumberWithTheSameDigits.nextSmaller(123456798));
    }
}
