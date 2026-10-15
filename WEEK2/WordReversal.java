public class WordReversal {
    public String reverseEachWord(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder(words[i]);
            result.append(sb.reverse().toString());
            if (i < words.length - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }
}
