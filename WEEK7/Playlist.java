public class Playlist {
    private String[] songs;
    private int count;

    public Playlist(int maxSize) {
        songs = new String[maxSize];
        count = 0;
    }

    public void addSong(String song) {
        if (count < songs.length) {
            songs[count++] = song;
        }
    }

    public String[] getSongs() {
        String[] copy = new String[count];
        for (int i = 0; i < count; i++) {
            copy[i] = songs[i];
        }
        return copy;
    }

    public int getSongCount() {
        return count;
    }
}
