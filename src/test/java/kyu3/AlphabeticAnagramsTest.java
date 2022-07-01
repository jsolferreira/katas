package kyu3;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlphabeticAnagramsTest {

    @Test
    public void testKnownInputs() {
        AlphabeticAnagrams anagram = new AlphabeticAnagrams();
        assertEquals(BigInteger.ONE, anagram.listPosition("A"), "Position for 'A' incorrect");
        assertEquals(BigInteger.valueOf(2), anagram.listPosition("ABAB"), "Position for 'ABAB' incorrect");
        assertEquals(BigInteger.ONE, anagram.listPosition("AAAB"), "Position for 'AAAB' incorrect");
        assertEquals(BigInteger.valueOf(4), anagram.listPosition("BAAA"), "Position for 'BAAA' incorrect");
        assertEquals(BigInteger.valueOf(24572), anagram.listPosition("QUESTION"), "Position for 'QUESTION' incorrect");
        assertEquals(BigInteger.valueOf(10743), anagram.listPosition("BOOKKEEPER"), "Position for 'BOOKKEEPER' incorrect");
        assertEquals(new BigInteger("718393983731145698173"), anagram.listPosition("IMMUNOELECTROPHORETICALLY"), "Position for 'IMMUNOELECTROPHORETICALLY' incorrect");
    }
}
