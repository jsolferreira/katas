package kyu4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseIntReloadedTest {
    @Test
    public void fixedTests() {
        assertEquals(1, ParseIntReloaded.parseInt("one"));
        assertEquals(20, ParseIntReloaded.parseInt("twenty"));
        assertEquals(246, ParseIntReloaded.parseInt("two hundred forty-six"));
    }
}
