package kyu6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DecodeTheMorseCodeAdvancedTest {
    @Test
    public void testExampleFromDescription() {
        assertEquals("HEY JUDE", DecodeTheMorseCode.decode(".... . -.--   .--- ..- -.. ."));
    }
}
