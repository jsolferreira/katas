package kyu7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheOfficeIVFindAMeetingRoomTest {
    @Test
    public void basic1() {
        assertEquals(1, TheOfficeIVFindAMeetingRoom.meeting(new char[]{'X', 'O', 'X'}));
    }

    @Test
    public void basic2() {
        assertEquals(0, TheOfficeIVFindAMeetingRoom.meeting(new char[]{'O', 'X', 'X', 'X', 'X'}));
    }

    @Test
    public void basic3() {
        assertEquals(2, TheOfficeIVFindAMeetingRoom.meeting(new char[]{'X', 'X', 'O', 'X', 'X'}));
    }

    @Test
    public void basic4() {
        assertEquals("None available!", TheOfficeIVFindAMeetingRoom.meeting(new char[]{'X', 'X', 'X', 'X', 'X'}));
    }
}
