package kyu6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheOfficeVFindAChairTest {
    @Test
    public void basic1() {
        TheOfficeVFindAChair.Room[] rooms = new TheOfficeVFindAChair.Room[]{
                new TheOfficeVFindAChair.Room("XXX", 3),
                new TheOfficeVFindAChair.Room("XXXXX", 6),
                new TheOfficeVFindAChair.Room("XXXXXX", 9)
        };
        assertArrayEquals(new int[]{0, 1, 3}, (int[]) TheOfficeVFindAChair.meeting(rooms, 4));
    }

    @Test
    public void basic2() {
        TheOfficeVFindAChair.Room[] rooms = new TheOfficeVFindAChair.Room[]{
                new TheOfficeVFindAChair.Room("XXX", 1),
                new TheOfficeVFindAChair.Room("XXXXXX", 6),
                new TheOfficeVFindAChair.Room("X", 2),
                new TheOfficeVFindAChair.Room("XXXXXX", 8),
                new TheOfficeVFindAChair.Room("X", 3),
                new TheOfficeVFindAChair.Room("XXX", 1)
        };
        assertArrayEquals(new int[]{0, 0, 1, 2, 2}, (int[]) TheOfficeVFindAChair.meeting(rooms, 5));
    }

    @Test
    public void basic3() {
        TheOfficeVFindAChair.Room[] rooms = new TheOfficeVFindAChair.Room[]{
                new TheOfficeVFindAChair.Room("XX", 2),
                new TheOfficeVFindAChair.Room("XXXX", 6),
                new TheOfficeVFindAChair.Room("XXXXX", 4)
        };
        assertEquals("Game On", TheOfficeVFindAChair.meeting(rooms, 0));
    }
}
