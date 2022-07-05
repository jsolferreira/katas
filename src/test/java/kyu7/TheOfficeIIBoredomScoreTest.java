package kyu7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheOfficeIIBoredomScoreTest {
    @Test
    public void basic1() {
        assertEquals("kill me now", TheOfficeIIBoredomScore.boredom(new TheOfficeIIBoredomScore.Person[] {
                new TheOfficeIIBoredomScore.Person("tim", "change"),
                new TheOfficeIIBoredomScore.Person("jim", "accounts"),
                new TheOfficeIIBoredomScore.Person("randy", "canteen"),
                new TheOfficeIIBoredomScore.Person("sandy", "change"),
                new TheOfficeIIBoredomScore.Person("andy", "change"),
                new TheOfficeIIBoredomScore.Person("katie", "IS"),
                new TheOfficeIIBoredomScore.Person("laura", "change"),
                new TheOfficeIIBoredomScore.Person("saajid", "IS"),
                new TheOfficeIIBoredomScore.Person("alex", "trading"),
                new TheOfficeIIBoredomScore.Person("john", "accounts"),
                new TheOfficeIIBoredomScore.Person("mr", "finance")
        }));
    }

    @Test
    public void basic2() {
        assertEquals("i can handle this", TheOfficeIIBoredomScore.boredom(new TheOfficeIIBoredomScore.Person[] {
                new TheOfficeIIBoredomScore.Person("tim", "IS"),
                new TheOfficeIIBoredomScore.Person("jim", "finance"),
                new TheOfficeIIBoredomScore.Person("randy", "pissing about"),
                new TheOfficeIIBoredomScore.Person("sandy", "cleaning"),
                new TheOfficeIIBoredomScore.Person("andy", "cleaning"),
                new TheOfficeIIBoredomScore.Person("katie", "cleaning"),
                new TheOfficeIIBoredomScore.Person("laura", "pissing about"),
                new TheOfficeIIBoredomScore.Person("saajid", "regulation"),
                new TheOfficeIIBoredomScore.Person("alex", "regulation"),
                new TheOfficeIIBoredomScore.Person("john", "accounts"),
                new TheOfficeIIBoredomScore.Person("mr", "canteen")
        }));
    }

    @Test
    public void basic3() {
        assertEquals("party time!!", TheOfficeIIBoredomScore.boredom(new TheOfficeIIBoredomScore.Person[] {
                new TheOfficeIIBoredomScore.Person("tim", "accounts"),
                new TheOfficeIIBoredomScore.Person("jim", "accounts"),
                new TheOfficeIIBoredomScore.Person("randy", "pissing about"),
                new TheOfficeIIBoredomScore.Person("sandy", "finance"),
                new TheOfficeIIBoredomScore.Person("andy", "change"),
                new TheOfficeIIBoredomScore.Person("katie", "IS"),
                new TheOfficeIIBoredomScore.Person("laura", "IS"),
                new TheOfficeIIBoredomScore.Person("saajid", "canteen"),
                new TheOfficeIIBoredomScore.Person("alex", "pissing about"),
                new TheOfficeIIBoredomScore.Person("john", "retail"),
                new TheOfficeIIBoredomScore.Person("mr", "pissing about")
        }));
    }
}
