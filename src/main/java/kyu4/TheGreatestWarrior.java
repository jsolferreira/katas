package kyu4;

import java.util.ArrayList;

public class TheGreatestWarrior {

    private enum Rank {
        Pushover,
        Novice,
        Fighter,
        Warrior,
        Veteran,
        Sage,
        Elite,
        Conqueror,
        Champion,
        Master,
        Greatest;

        public static Rank fromLevel(int level) {
            return Rank.values()[Math.floorDiv(level, 10)];
        }
    }

    private int level = 1;

    private Rank rank = Rank.Pushover;

    private int experience = 100;

    private final ArrayList<String> achievements = new ArrayList<>();

    public TheGreatestWarrior() {
    }

    public String training(String description, int experience, int minimumLevel) {

        if (level < minimumLevel) {
            return "Not strong enough";
        }

        addExperience(experience);
        achievements.add(description);

        return description;
    }

    public String battle(int enemyLevel) {

        if (enemyLevel < 1 || enemyLevel > 100) {
            return "Invalid level";
        }

        if (enemyLevel <= level) {
            if (enemyLevel == level) {
                addExperience(10);
                return "A good fight";
            } else if (enemyLevel == level - 1) {
                addExperience(5);
                return "A good fight";
            } else {
                return "Easy fight";
            }
        } else {
            if (Rank.fromLevel(enemyLevel) != rank && enemyLevel >= level + 5) {
                return "You've been defeated";
            }

            final int diff = enemyLevel - level;

            addExperience(20 * diff * diff);

            return "An intense fight";
        }
    }

    private void addExperience(int experience) {
        this.experience += experience;
        if (this.experience > 10000) {
            this.experience = 10000;
        }
        level = Math.floorDiv(this.experience, 100);
        rank = Rank.fromLevel(level);
    }

    public int level() {
        return level;
    }

    public String rank() {
        return rank.name();
    }

    public int experience() {
        return experience;
    }

    public ArrayList<String> achievements() {
        return achievements;
    }
}
