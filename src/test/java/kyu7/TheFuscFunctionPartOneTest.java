package kyu7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheFuscFunctionPartOneTest {

    @Test
    public void tests() {
        assertEquals(0, TheFuscFunctionPartOne.fusc(0));
        assertEquals(1, TheFuscFunctionPartOne.fusc(1));
        assertEquals(21, TheFuscFunctionPartOne.fusc(85));
    }
}
