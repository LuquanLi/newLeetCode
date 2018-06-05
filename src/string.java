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
}
