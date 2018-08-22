import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        if (paragraph == null || paragraph.isEmpty()) return "";

        // remove all non letter char
        List<String> words = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (char ch: paragraph.toCharArray()) {
            if (Character.isLetter(ch)) {
                sb.append(Character.toLowerCase(ch));
            } else { // break the word
                if (sb.length() > 0) words.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if (sb.length() > 0) words.add(sb.toString()); // add last word
        List<String> bannedWords = Arrays.asList(banned);

        Map<String, Integer> map = new HashMap<>();
        for (String word: words) {
            if (!bannedWords.contains(word))
                map.put(word, map.getOrDefault(word, 0) + 1);
        }

        return Collections.max(map.entrySet(), Map.Entry.comparingByValue()).getKey();
    }
}
