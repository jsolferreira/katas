package kyu4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MostFrequentlyUsedWordsInATextTest {

    @Test
    public void sampleTests() {
        assertEquals(Arrays.asList("e", "d", "a"), MostFrequentlyUsedWordsInAText.top3("a a a  b  c c  d d d d  e e e e e"));
        assertEquals(Arrays.asList("e", "ddd", "aa"), MostFrequentlyUsedWordsInAText.top3("e e e e DDD ddd DdD: ddd ddd aa aA Aa, bb cc cC e e e"));
        assertEquals(Arrays.asList("won't", "wont"), MostFrequentlyUsedWordsInAText.top3("  //wont won't won't "));
        assertEquals(List.of("e"), MostFrequentlyUsedWordsInAText.top3("  , e   .. "));
        assertEquals(List.of(), MostFrequentlyUsedWordsInAText.top3("  ...  "));
        assertEquals(List.of(), MostFrequentlyUsedWordsInAText.top3("  '  "));
        assertEquals(List.of(), MostFrequentlyUsedWordsInAText.top3("  '''  "));
        assertEquals(Arrays.asList("a", "of", "on"), MostFrequentlyUsedWordsInAText.top3(String.join("\n", "In a village of La Mancha, the name of which I have no desire to call to",
                "mind, there lived not long since one of those gentlemen that keep a lance",
                "in the lance-rack, an old buckler, a lean hack, and a greyhound for",
                "coursing. An olla of rather more beef than mutton, a salad on most",
                "nights, scraps on Saturdays, lentils on Fridays, and a pigeon or so extra",
                "on Sundays, made away with three-quarters of his income.")));
    }
}
