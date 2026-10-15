public class MatchDayGridAnalyzer {
    private static double rowAverage(int[] row) {
        if (row.length == 0) return 0;
        double sum = 0;
        for (int runs : row) sum += runs;
        return sum / row.length;
    }

    public static String classifyMatches(int[][] runsPerOver, int threshold) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < runsPerOver.length; i++) {
            double avg = rowAverage(runsPerOver[i]);
            if (i > 0) result.append(" | ");
            result.append("Match ").append(i).append(": ");
            if (avg >= threshold) {
                result.append("Power Surge");
            } else {
                result.append("Normal");
            }
        }
        return result.toString();
    }
}
