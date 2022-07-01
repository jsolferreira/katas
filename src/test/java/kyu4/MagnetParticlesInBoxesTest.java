package kyu4;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class MagnetParticlesInBoxesTest {

    private static void assertFuzzyEquals(double act, double exp) {
        boolean inRange = Math.abs(act - exp) <= 1e-6;
        if (!inRange) {
            DecimalFormat df = new DecimalFormat("#0.000000");
            System.out.println("At 1e-6: Expected must be " + df.format(exp) + ", but got " + df.format(act));
        }
        assertTrue(inRange);
    }

    @Test
    public void test1() {
        System.out.println("Fixed Tests: doubles");
        assertFuzzyEquals(MagnetParticlesInBoxes.doubles(1, 10), 0.5580321939764581);
        assertFuzzyEquals(MagnetParticlesInBoxes.doubles(10, 1000), 0.6921486500921933);
        assertFuzzyEquals(MagnetParticlesInBoxes.doubles(10, 10000), 0.6930471674194457);
        assertFuzzyEquals(MagnetParticlesInBoxes.doubles(20, 10000), 0.6930471955575918);
    }
}
