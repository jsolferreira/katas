package kyu5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoboScript2ImplementTheRS1SpecificationTest {

    @Test
    public void sampleTests() {
        assertPathEquals("", "*");
        assertPathEquals("FFFFF", "******");
        assertPathEquals("FFFFFLFFFFFLFFFFFLFFFFFL", "******\r\n*    *\r\n*    *\r\n*    *\r\n*    *\r\n******");
        assertPathEquals("LFFFFFRFFFRFFFRFFFFFFF", "    ****\r\n    *  *\r\n    *  *\r\n********\r\n    *   \r\n    *   ");
        assertPathEquals("LF5RF3RF3RF7", "    ****\r\n    *  *\r\n    *  *\r\n********\r\n    *   \r\n    *   ");
    }


    private static void assertPathEquals(String code, String expected) {
        String actual = RoboScript2ImplementTheRS1Specification.execute(code);
        boolean areEqual = expected.equals(actual);

        if (!areEqual) {
            System.out.printf(
                    "--------------\nYou returned:\n%s\nExpected path of MyRobot:\n%s\n--------------\n%n",
                    actual, expected);
        }
        Assertions.assertTrue(areEqual, "Nope...");
    }
}
