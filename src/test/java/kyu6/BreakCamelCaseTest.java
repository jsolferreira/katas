package kyu6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BreakCamelCaseTest {
    @Test
    public void tests() {
        assertEquals("camel Casing", BreakCamelCase.camelCase("camelCasing"), "Incorrect");
        assertEquals("camel Casing Test", BreakCamelCase.camelCase("camelCasingTest"), "Incorrect");
        assertEquals("camelcasingtest", BreakCamelCase.camelCase("camelcasingtest"), "Incorrect");
    }
}
