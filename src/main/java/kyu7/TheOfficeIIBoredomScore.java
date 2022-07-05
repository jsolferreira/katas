package kyu7;

import java.util.Arrays;
import java.util.Map;

public class TheOfficeIIBoredomScore {

    private final static Map<String, Integer> boredomScore = Map.ofEntries(
            Map.entry("accounts", 1),
            Map.entry("finance", 2),
            Map.entry("canteen", 10),
            Map.entry("regulation", 3),
            Map.entry("trading", 6),
            Map.entry("change", 6),
            Map.entry("IS", 8),
            Map.entry("retail", 5),
            Map.entry("cleaning", 4),
            Map.entry("pissing about", 25)
    );

    public static class Person {
        public final String name;        // name of the staff member
        public final String department;  // department they work in

        public Person(String name, String department) {
            this.name = name;
            this.department = department;
        }
    }

    public static String boredom(Person[] staff) {

        final int score = Arrays.stream(staff)
                .mapToInt(person -> boredomScore.get(person.department))
                .sum();

        if (score <= 80) {
            return "kill me now";
        } else if (score >= 100) {
            return "party time!!";
        } else {
            return "i can handle this";
        }
    }
}
