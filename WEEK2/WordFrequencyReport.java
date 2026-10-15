import java.util.*;

public class WordFrequencyReport {
    public void printFilteredWordFrequency(String feedback) {
        Set<String> stopWords = new HashSet<>(Arrays.asList("the", "was", "and", "a", "is", "of", "in"));
        String cleaned = feedback.toLowerCase().replace(".", "").replace(",", "");
        String[] words = cleaned.split("\\\\s+");
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            if (!stopWords.contains(word) && !word.isEmpty()) {
                counts.put(word, counts.getOrDefault(word, 0) + 1);
            }
        }
        List<Map.Entry<String, Integer>> list = new ArrayList<>(counts.entrySet());
        list.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        for (Map.Entry<String, Integer> entry : list) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
