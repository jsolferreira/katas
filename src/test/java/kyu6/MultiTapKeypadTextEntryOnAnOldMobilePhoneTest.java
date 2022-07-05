package kyu6;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MultiTapKeypadTextEntryOnAnOldMobilePhoneTest {

    @Test
    public void simpleTest() {
        assertEquals(9, MultiTapKeypadTextEntryOnAnOldMobilePhone.presses("LOL"));
        assertEquals(13, MultiTapKeypadTextEntryOnAnOldMobilePhone.presses("HOW R U"));
    }
}
