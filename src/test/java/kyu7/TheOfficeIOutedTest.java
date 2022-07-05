package kyu7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheOfficeIOutedTest {
    @Test
    public void basic1() {
        TheOfficeIOuted.Person[] meet = new TheOfficeIOuted.Person[]{
                new TheOfficeIOuted.Person("tim", 0),
                new TheOfficeIOuted.Person("jim", 2),
                new TheOfficeIOuted.Person("randy", 0),
                new TheOfficeIOuted.Person("sandy", 7),
                new TheOfficeIOuted.Person("andy", 0),
                new TheOfficeIOuted.Person("katie", 5),
                new TheOfficeIOuted.Person("laura", 1),
                new TheOfficeIOuted.Person("saajid", 2),
                new TheOfficeIOuted.Person("alex", 3),
                new TheOfficeIOuted.Person("john", 2),
                new TheOfficeIOuted.Person("mr", 0)
        };
        assertEquals("Get Out Now!", TheOfficeIOuted.outed(meet, "laura"));
    }

    @Test
    public void basic2() {
        TheOfficeIOuted.Person[] meet = new TheOfficeIOuted.Person[]{
                new TheOfficeIOuted.Person("tim", 1),
                new TheOfficeIOuted.Person("jim", 3),
                new TheOfficeIOuted.Person("randy", 9),
                new TheOfficeIOuted.Person("sandy", 6),
                new TheOfficeIOuted.Person("andy", 7),
                new TheOfficeIOuted.Person("katie", 6),
                new TheOfficeIOuted.Person("laura", 9),
                new TheOfficeIOuted.Person("saajid", 9),
                new TheOfficeIOuted.Person("alex", 9),
                new TheOfficeIOuted.Person("john", 9),
                new TheOfficeIOuted.Person("mr", 8)
        };
        assertEquals("Nice Work Champ!", TheOfficeIOuted.outed(meet, "katie"));
    }

    @Test
    public void basic3() {
        TheOfficeIOuted.Person[] meet = new TheOfficeIOuted.Person[]{
                new TheOfficeIOuted.Person("tim", 2),
                new TheOfficeIOuted.Person("jim", 4),
                new TheOfficeIOuted.Person("randy", 0),
                new TheOfficeIOuted.Person("sandy", 5),
                new TheOfficeIOuted.Person("andy", 8),
                new TheOfficeIOuted.Person("katie", 6),
                new TheOfficeIOuted.Person("laura", 2),
                new TheOfficeIOuted.Person("saajid", 2),
                new TheOfficeIOuted.Person("alex", 3),
                new TheOfficeIOuted.Person("john", 2),
                new TheOfficeIOuted.Person("mr", 8),
        };
        assertEquals("Get Out Now!", TheOfficeIOuted.outed(meet, "john"));
    }
}
