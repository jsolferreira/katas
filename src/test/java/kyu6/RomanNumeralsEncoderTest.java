package kyu6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RomanNumeralsEncoderTest {

    private final RomanNumeralsEncoder romanNumeralsEncoder = new RomanNumeralsEncoder();

    @Test
    public void shouldConvertToRoman() {
        assertEquals("solution(1) should equal to I", "I", romanNumeralsEncoder.solution(1));
        assertEquals("solution(4) should equal to IV", "IV", romanNumeralsEncoder.solution(4));
        assertEquals("solution(6) should equal to VI", "VI", romanNumeralsEncoder.solution(6));
    }
}
