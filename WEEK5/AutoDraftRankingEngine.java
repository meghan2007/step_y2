import java.util.Arrays;

class Player implements Comparable<Player> {
    String name;
    int matchesPlayed;
    double battingAverage;
    boolean injured;

    public Player(String name, int matchesPlayed, double battingAverage, boolean injured) {
        this.name = name;
        this.matchesPlayed = matchesPlayed;
        this.battingAverage = battingAverage;
        this.injured = injured;
    }

    public static boolean isDraftable(int matchesPlayed) {
        return matchesPlayed >= 10;
    }

    public static boolean isDraftable(int matchesPlayed, boolean injured) {
        return matchesPlayed >= 5 && !injured;
    }

    @Override
    public int compareTo(Player other) {
        return Double.compare(other.battingAverage, this.battingAverage);
    }

    public static String draftAndRank(Player[] players) {
        Player[] draftable = new Player[players.length];
        int count = 0;
        for (Player p : players) {
            boolean canDraft = isDraftable(p.matchesPlayed) || isDraftable(p.matchesPlayed, p.injured);
            if (canDraft) {
                draftable[count++] = p;
            }
        }
        Player[] finalDraft = Arrays.copyOf(draftable, count);
        Arrays.sort(finalDraft);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < finalDraft.length; i++) {
            if (i > 0) sb.append(" | ");
            sb.append((i + 1) + ". " + finalDraft[i].name);
        }
        return sb.toString();
    }
}
