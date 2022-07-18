package kyu4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoboScript3ImplementTheRS2SpecificationTest {

    @Test
    public void sampleTests() {
        assertPathEquals("LF5(RF3)(RF3R)F7", "    ****\r\n    *  *\r\n    *  *\r\n********\r\n    *   \r\n    *   ");
        assertPathEquals("(L(F5(RF3))(((R(F3R)F7))))", "    ****\r\n    *  *\r\n    *  *\r\n********\r\n    *   \r\n    *   ");
        assertPathEquals("F4L(F4RF4RF4LF4L)2F4RF4RF4", "    *****   *****   *****\r\n    *   *   *   *   *   *\r\n    *   *   *   *   *   *\r\n    *   *   *   *   *   *\r\n*****   *****   *****   *");
        assertPathEquals("F4L((F4R)2(F4L)2)2(F4R)2F4", "    *****   *****   *****\r\n    *   *   *   *   *   *\r\n    *   *   *   *   *   *\r\n    *   *   *   *   *   *\r\n*****   *****   *****   *");
    }


    private static void assertPathEquals(String code, String expected) {
        String actual = RoboScript3ImplementTheRS2Specification.execute(code);
        boolean areEqual = expected.equals(actual);

        if (!areEqual) {
            System.out.printf(
                    "--------------\nYou returned:\n%s\nExpected path of MyRobot:\n%s\n--------------\n%n",
                    actual, expected);
        }
        Assertions.assertTrue(areEqual, "Nope...");
    }
}
