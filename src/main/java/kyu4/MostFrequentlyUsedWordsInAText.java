package kyu4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MostFrequentlyUsedWordsInAText {

    public static List<String> top3(String s) {

        return Arrays.stream(s.split("(^'+$|[^a-zA-Z']+'+[^a-zA-Z']+|[^a-zA-Z']+)"))
                .filter(word -> !word.equals(""))
                .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting())).entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed().thenComparing(Map.Entry.comparingByKey()))
                .limit(3)
                .map(Map.Entry::getKey)
                .toList();
    }
}
