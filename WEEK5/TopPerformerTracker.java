public class TopPerformerTracker {
    public static String findMinMaxSpread(int[] scores) {
        int min = scores[0];
        int max = scores[0];
        for (int i = 1; i < scores.length; i++) {
            if (scores[i] < min) min = scores[i];
            if (scores[i] > max) max = scores[i];
        }
        return "Min: " + min + " | Max: " + max + " | Spread: " + (max - min);
    }
}
