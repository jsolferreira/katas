package kyu2;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InsaneColouredTrianglesTest {
    Random r = new Random();

    @Test
    public void examples() {
        // assertEquals("expected", "actual");
        assertEquals('R', InsaneColouredTriangles.triangle("GB"));
        assertEquals('R', InsaneColouredTriangles.triangle("RRR"));
        assertEquals('B', InsaneColouredTriangles.triangle("RGBG"));
        assertEquals('G', InsaneColouredTriangles.triangle("RBRGBRB"));
        assertEquals('G', InsaneColouredTriangles.triangle("RBRGBRBGGRRRBGBBBGG"));
        assertEquals('B', InsaneColouredTriangles.triangle("B"));
    }
}
