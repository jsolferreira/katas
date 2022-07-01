package kyu3;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IceMazeTest {
    private List<Character> stringToCharList(String s) {
        return s.chars().mapToObj(e -> (char) e).collect(Collectors.toList());
    }

    @Test
    public void test01_Example() {

        String map = """
                    x\s
                  #  \s
                   E \s
                 #   \s
                    #\s
                S    #""";
        System.out.printf("A simple spiral\n%s\n", map);
        assertEquals(stringToCharList("urdlur"), IceMaze.solve(map));

        map = """
                 #   \s
                x   E\s
                     \s
                     S
                     \s
                 #   \s""";
        System.out.printf("Ice puzzle has one-way routes\n%s\n", map);
        assertEquals(stringToCharList("lur"), IceMaze.solve(map));

        map = """
                E#   \s
                     \s
                     \s
                     \s
                     \s
                 #   S""";
        System.out.printf("The end is unreachable\n%s\n", map);
        assertNull(IceMaze.solve(map));

        map = """
                E#   #
                     \s
                #    \s
                  #  \s
                 #   \s
                 S   \s""";
        System.out.printf("Tiebreak by least number of moves first\n%s\n", map);
        assertEquals(stringToCharList("rulu"), IceMaze.solve(map));

        map = """
                    E\s
                     #
                     \s
                # #  \s
                    #\s
                 #  S\s""";
        System.out.printf("Then by total distance traversed\n%s\n", map);
        assertEquals(stringToCharList("lurur"), IceMaze.solve(map));

        map = """
                E#xxx
                x#x#x
                x#x#x
                x#x#x
                xxx#S""";
        System.out.printf("Covering all tiles with x should reduce the problem to simple pathfinding\n%s\n", map);
        assertEquals(stringToCharList("uuuullddddlluuuu"), IceMaze.solve(map));

        map = """
                E#\s
                #S#
                 #\s""";
        System.out.printf("There is no escape\n%s\n", map);
        assertNull(IceMaze.solve(map));
    }
}
