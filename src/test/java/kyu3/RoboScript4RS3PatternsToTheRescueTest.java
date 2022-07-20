package kyu3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class RoboScript4RS3PatternsToTheRescueTest {

    @Test
    public void shouldWorkForRS2CompliantPrograms_SampleTestCases() {
        assertPathEquals("(F2LF2R)2FRF4L(F2LF2R)2(FRFL)4(F2LF2R)2", "    **   **      *\r\n    **   ***     *\r\n  **** *** **  ***\r\n  *  * *    ** *  \r\n***  ***     ***  ");
    }

    @Test
    public void shouldProperlyParsePatternDefinition_notCausingAnySideEffect_SampleTestCases() {
        assertPathEquals("p0(F2LF2R)2q", "*");
        assertPathEquals("p312(F2LF2R)2q", "*");
    }

    @Test
    public void shouldExecuteGivenPatternWhenInvoked_SampleTestCases() {
        assertPathEquals("p0(F2LF2R)2qP0", "    *\r\n    *\r\n  ***\r\n  *  \r\n***  ");
        assertPathEquals("p312(F2LF2R)2qP312", "    *\r\n    *\r\n  ***\r\n  *  \r\n***  ");
    }

    @Test
    public void shouldAlwaysParsePatternDefinitionsFirst_SampleTestCases() {
        assertPathEquals("P0p0(F2LF2R)2q", "    *\r\n    *\r\n  ***\r\n  *  \r\n***  ");
        assertPathEquals("P312p312(F2LF2R)2q", "    *\r\n    *\r\n  ***\r\n  *  \r\n***  ");
    }

    @Test
    public void shouldAllowOtherFormsOfRoboScriptCodeAlongsidePatternDefinitionsAndInvocations_SampleTestCases() {
        assertPathEquals("F3P0Lp0(F2LF2R)2qF2", "       *\r\n       *\r\n       *\r\n       *\r\n     ***\r\n     *  \r\n******  ");
    }

    @Test
    public void shouldAllowMultipleInvokationOfSamePattern_SampleTestCases() {
        assertPathEquals("(P0)2p0F2LF2RqP0", "      *\r\n      *\r\n    ***\r\n    *  \r\n  ***  \r\n  *    \r\n***    ");
    }

    @Test
    public void shouldThrowWhenNonExistingPatternInvoked_SampleTestCases() {
        expectError("Your interpreter should throw an error because pattern \"P1\" does not exist", "p0(F2LF2R)2qP1");
        expectError("Your interpreter should throw an error because pattern \"P0\" does not exist", "P0p312(F2LF2R)2q");
        expectError("Your interpreter should throw an error because pattern \"P312\" does not exist", "P312");
    }

    @Test
    public void shouldProperlyParseMultiplePatternDefinitions_SampleTestCases() {
        assertPathEquals("P1P2p1F2Lqp2F2RqP2P1", "  ***\r\n  * *\r\n*** *");
        assertPathEquals("p1F2Lqp2F2Rqp3P1(P2)2P1q(P3)3", "  *** *** ***\r\n  * * * * * *\r\n*** *** *** *");
    }

    private static void assertPathEquals(String code, String expected) {
        String actual = RoboScript4RS3PatternsToTheRescue.execute(code);
        boolean areEqual = expected.equals(actual);

        if (!areEqual) {
            System.out.printf(
                    "--------------\nYou returned:\n%s\nExpected path of MyRobot:\n%s\n--------------\n%n",
                    actual, expected);
        }
        Assertions.assertTrue(areEqual, "Nope...");
    }

    private static void expectError(String msg, String code) {
        boolean threw = false;
        try {
            RoboScript4RS3PatternsToTheRescue.execute(code);
        } catch (RuntimeException e) {
            e.printStackTrace();
            threw = true;
        }
        Assertions.assertTrue(threw, msg);
    }
}
