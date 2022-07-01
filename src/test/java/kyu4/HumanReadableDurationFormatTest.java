package kyu4;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HumanReadableDurationFormatTest {
    @Test
    public void exampleTests() {
        assertEquals("1 second", HumanReadableDurationFormat.formatDuration(1));
        assertEquals("1 minute and 2 seconds", HumanReadableDurationFormat.formatDuration(62));
        assertEquals("2 minutes", HumanReadableDurationFormat.formatDuration(120));
        assertEquals("1 hour", HumanReadableDurationFormat.formatDuration(3600));
        assertEquals("1 hour, 1 minute and 2 seconds", HumanReadableDurationFormat.formatDuration(3662));
    }
}
