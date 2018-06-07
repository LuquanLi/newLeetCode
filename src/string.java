import java.util.HashMap;
import java.util.Map;

public class string {
    // 344
    public String reverseString(String s) {
        if (s == null) return s;
        StringBuilder sb = new StringBuilder(s);
        return sb.reverse().toString();
    }

    // 541
    public String reverseStr(String s, int k) {
        if (s == null || k <= 1) return s;

        int counter = k;
        for (int i = 0; i < s.length(); i ++) {
            counter --;
            if ((counter > 0 && i == s.length() - 1) || counter == 0) {
                s = reverseStr(s, i - (k - counter) + 1, i + 1); // reverse [s, e)
            } else if (counter == -k) {
                counter = k;
            }
        }

        return s;
    }

    private String reverseStr(String str, int s, int e) {
        StringBuilder sb = new StringBuilder(str.substring(s, e));
        return str.substring(0, s) + sb.reverse().toString() + str.substring(e);
    }

    // 151
    //    Input: "the sky is blue",
    //    Output: "blue is sky the".
    public static String reverseWords(String s) {
        // 1. reverse the entire string
        s = reverseStr(s).trim();
        StringBuilder sb = new StringBuilder();

        // 2. reverse each word
        int i = 0;
        for (int j = 0; j < s.length(); j ++) {
            if (s.charAt(j) != ' ' && (j == 0 || s.charAt(j - 1) == ' ')) {
                i = j;
            }

            if (s.charAt(j) != ' ' && (j == s.length() - 1 || s.charAt(j + 1) == ' ')) {
                sb.append(reverseStr(s.substring(i, j + 1)));
                sb.append(' ');
            }
        }

        return sb.toString().trim();
    }

    // reverse string [s, e)
    private static String reverseStr(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    // 186
    //    Input:  ['t','h','e',' ','s','k','y',' ','i','s',' ','b','l','u','e']
    //    Output: ['b','l','u','e',' ','i','s',' ','s','k','y',' ','t','h','e']
    public static void reverseWords(char[] str) {
        // reverse array?
        reverseArray(str, 0, str.length - 1);

        // reverse word
        int i = 0;
        for (int j = 0; j < str.length; j ++) {
            if (str[j] == ' ' || j == str.length - 1) {
                reverseArray(str, i, j == str.length - 1 ? j : j - 1);
                i = j + 1;
            }
        }
    }

    // reverse array [s, e]
    private static void reverseArray(char[] str, int s, int e) {
        while (s < e) {
            char tmp = str[s];
            str[s++] = str[e];
            str[e--] = tmp;
        }
    }

    // 557
    //    Input: "Let's take LeetCode contest"
    //    Output: "s'teL ekat edoCteeL tsetnoc"
    public String reverseWords3(String s) {
        StringBuilder sb = new StringBuilder();

        int i = 0;
        for (int j = 0; j < s.length() + 1; j ++ ) {
            if (j == s.length() || s.charAt(j) == ' ' ) {
                sb.append(reverseStr3(s.substring(i, j)));
                sb.append(' ');
                i = j + 1;
            }
        }
        return sb.toString().trim();
    }

    private String reverseStr3(String str) {
        StringBuilder sb = new StringBuilder(str);
        return sb.reverse().toString();
    }

    // 13
    //    I             1
    //    V             5
    //    X             10
    //    L             50
    //    C             100
    //    D             500
    //    M             1000
    public int romanToInt(String s) {
        // input validation
        if (s == null || s.length() == 0) {
            return 0;
        }

        // put roman into map
        Map<Character, Integer> romanValueMap = new HashMap<>();
        romanValueMap.put('I', 1);
        romanValueMap.put('V', 5);
        romanValueMap.put('X', 10);
        romanValueMap.put('L', 50);
        romanValueMap.put('C', 100);
        romanValueMap.put('D', 500);
        romanValueMap.put('M', 1000);

        // iterate string from back to front
        // if order of i < order of i + 1, -- value, else ++ value
        int value = romanValueMap.get(s.charAt(s.length() - 1));
        for (int i = s.length() - 2; i >= 0; i --) {
            if (romanValueMap.get(s.charAt(i)) < romanValueMap.get(s.charAt(i + 1))) {
                value -= romanValueMap.get(s.charAt(i));
            } else {
                value += romanValueMap.get(s.charAt(i));
            }
        }

        return value;
    }

    // 6
    /*n=numRows
   Δ=2n-2    1                           2n-1                         4n-3
   Δ=        2                     2n-2  2n                    4n-4   4n-2
   Δ=        3               2n-3        2n+1              4n-5       .
   Δ=        .           .               .               .            .
   Δ=        .       n+2                 .           3n               .
   Δ=        n-1 n+1                     3n-3    3n-1                 5n-5
   Δ=2n-2    n                           3n-2                         5n-4
   */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        StringBuilder b = new StringBuilder();
        int interval = 2 * numRows - 2;
        int n = s.length();
        for(int i = 0; i < numRows; i++){
            int j = i;
            while(j < n){
                if(interval != 0) b.append(s.charAt(j));
                j += interval;
                if(i != 0 && j < n) b.append(s.charAt(j));
                j += 2 * i;
            }
            interval -= 2;
        }
        return b.toString();
    }
}
