package kyu2;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluateMathematicalExpressionTest {

    @Test
    public void testAddition() {
        assertEquals(new EvaluateMathematicalExpression().calculate("1+1"), 2d, 0.01);
    }

    @Test
    public void testSubtraction() {
        assertEquals(new EvaluateMathematicalExpression().calculate("1 - 1"), 0d, 0.01);
    }

    @Test
    public void testMultiplication() {
        assertEquals(new EvaluateMathematicalExpression().calculate("1* 1"), 1d, 0.01);
    }

    @Test
    public void testDivision() {
        assertEquals(new EvaluateMathematicalExpression().calculate("1 /1"), 1d, 0.01);
    }

    @Test
    public void testNegative() {
        assertEquals(new EvaluateMathematicalExpression().calculate("-123"), -123d, 0.01);
    }

    @Test
    public void testLiteral() {
        assertEquals(new EvaluateMathematicalExpression().calculate("123"), 123d, 0.01);
    }

    @Test
    public void testExpression() {
        assertEquals(new EvaluateMathematicalExpression().calculate("2 /2+3 * 4.75- -6"), 21.25, 0.01);
    }

    @Test
    public void testSimple() {
        assertEquals(new EvaluateMathematicalExpression().calculate("12* 123"), 1476d, 0.01);
    }

    @Test
    public void testComplex() {
        assertEquals(new EvaluateMathematicalExpression().calculate("2 / (2 + 3) * 4.33 - -6"), 7.732, 0.01);
    }
}
