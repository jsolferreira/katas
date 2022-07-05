package kyu7;

import java.util.Arrays;

public class TheOfficeIOuted {

    public static class Person {
        final String name;    // team member's name
        final int happiness;  // happiness rating out of 10

        public Person(String name, int happiness) {
            this.name = name;
            this.happiness = happiness;
        }
    }

    public static String outed(Person[] meet, String boss) {

        return Arrays.stream(meet)
                .filter(person -> person.name.equals(boss))
                .findFirst()
                .map(person -> person.happiness <= 5 ? "Get Out Now!" : "Nice Work Champ!")
                .orElse("");
    }
}
