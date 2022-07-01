package kyu6;

public class HighestScoringWord {

    public static String high(String s) {

        int highestScore = 0;
        String highestWord = "";

        for (String s1 : s.split(" ")) {
            final int score = score(s1);

            if (score > highestScore) {
                highestScore = score;
                highestWord = s1;
            }
        }

        return highestWord;
    }

    private static int score(String s) {

        return s.toLowerCase().chars().reduce(0, (acc, val) -> acc + (val - (int) 'a' + 1));
    }
}
