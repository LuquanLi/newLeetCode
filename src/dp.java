import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dp {
    //    70. Climbing Stairs
//
//    You are climbing a stair case. It takes n steps to reach to the top.
//
//    Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
//
//    Note: Given n will be a positive integer.
//
//            Example 1:
//
//    Input: 2
//    Output: 2
//    Explanation: There are two ways to climb to the top.
//1. 1 step + 1 step
//2. 2 steps
//    Example 2:
//
//    Input: 3
//    Output: 3
//    Explanation: There are three ways to climb to the top.
//1. 1 step + 1 step + 1 step
//2. 1 step + 2 steps
//3. 2 steps + 1 step

    // climb 1 step or 2 step2
    // dp[0] = 1, dp[1] = 1, .. dp[i] = dp[i-1] + dp[i-2]
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i < n + 1; i ++) {
            if (i == 0 || i == 1) dp[i] = 1;
            else dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }

//    198. House Robber
//
//    You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//    Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
//
//            Example 1:
//
//    Input: [1,2,3,1]
//    Output: 4
//    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//    Total amount you can rob = 1 + 3 = 4.
//    Example 2:
//
//    Input: [2,7,9,3,1]
//    Output: 12
//    Explanation: Rob house 1 (money = 2), rob house 3 (money = 9) and rob house 5 (money = 1).
//    Total amount you can rob = 2 + 9 + 1 = 12.

    // 2 adjacent house broke
    // dp[0] = nums[0], only 1 house, just rob it
    // dp[1] = Max(num[0], num[1])
    // dp[i] = rob i: num[i] + dp[i - 2], not rob i: dp[i - 1]
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] dp = new int[nums.length];
        for (int i = 0; i < dp.length; i ++) {
            if (i == 0) dp[i] = nums[0];
            else if (i == 1) dp[i] = Math.max(nums[0], nums[1]);
            else dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[dp.length - 1];
    }

    //    213. House Robber II
//
//    You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed. All houses at this place are arranged in a circle. That means the first house is the neighbor of the last one. Meanwhile, adjacent houses have security system connected and it will automatically contact the police if two adjacent houses were broken into on the same night.
//
//    Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob tonight without alerting the police.
//
//            Example 1:
//
//    Input: [2,3,2]
//    Output: 3
//    Explanation: You cannot rob house 1 (money = 2) and then rob house 3 (money = 2),
//    because they are adjacent houses.
//            Example 2:
//
//    Input: [1,2,3,1]
//    Output: 4
//    Explanation: Rob house 1 (money = 1) and then rob house 3 (money = 3).
//    Total amount you can rob = 1 + 3 = 4.

    // a circle, first house connect to last
    // 1. rob1st, 2. nrob1st
    public int rob2(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int[] rob1st = new int[nums.length];
        int[] nrob1st = new int[nums.length];

        for (int i = 0; i < nums.length; i ++) {
            if (i == 0 || i == 1) {
                rob1st[i] = nums[0]; // not take i = 1 house
                nrob1st[i] = i == 0 ? 0 : nums[i]; // take i = 1
            } else {
                // rob1st[nums.length - 2] since it takes nums[0] so it cannot take nums.lenght - 1
                rob1st[i] = i == nums.length - 1 ? rob1st[i - 1] : Math.max(nums[i] + rob1st[i - 2], rob1st[i - 1]);
                nrob1st[i] = Math.max(nums[i] + nrob1st[i - 2], nrob1st[i - 1]);
            }
        }

        return Math.max(rob1st[nums.length - 1], nrob1st[nums.length - 1]);
    }

    // use array [not take root, take root]
    public int rob3(TreeNode root) {
        int[] res = rob33(root);
        return Math.max(res[0], res[1]);
    }

    private int[] rob33(TreeNode root) {
        if (root == null) return new int[2];

        int[] left = rob33(root.left);
        int[] right = rob33(root.right);
        int[] result = new int[2];

        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        result[1] = root.val + left[0] + right[0];

        return result;
    }

    ////139. Word Break
//////
//////    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, determine if s can be segmented into a space-separated sequence of one or more dictionary words.
//////
//////    Note:
//////
//////    The same word in the dictionary may be reused multiple times in the segmentation.
//////    You may assume the dictionary does not contain duplicate words.
//////    Example 1:
//////
//////    Input: s = "leetcode", wordDict = ["leet", "code"]
//////    Output: true
//////    Explanation: Return true because "leetcode" can be segmented as "leet code".
//////    Example 2:
//////
//////    Input: s = "applepenapple", wordDict = ["apple", "pen"]
//////    Output: true
//////    Explanation: Return true because "applepenapple" can be segmented as "apple pen apple".
//////    Note that you are allowed to reuse a dictionary word.
//////    Example 3:
//////
//////    Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//////    Output: false
//

    // word in dict can be reused
// return T/F
// dp[i] -> T/F
    public static boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];

        for (int i = 0; i < s.length(); i ++) {
            if (!dp[i]) { // dp[i] = false
                if (wordDict.contains(s.substring(0, i + 1))) {
                    dp[i] = true;
                } else {
                    for (int j = 0; j < i; j ++) {
                        if (dp[j] && wordDict.contains(s.substring(j + 1, i + 1))) {
                            dp[i] = true;
                            break;
                        }
                    }
                }
            }
        }

        return dp[s.length() - 1];
    }

    //    140. Word Break II
//
//    Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences.
//
//            Note:
//
//    The same word in the dictionary may be reused multiple times in the segmentation.
//    You may assume the dictionary does not contain duplicate words.
//    Example 1:
//
//    Input:
//    s = "catsanddog"
//    wordDict = ["cat", "cats", "and", "sand", "dog"]
//    Output:
//            [
//            "cats and dog",
//            "cat sand dog"
//            ]
//    Example 2:
//
//    Input:
//    s = "pineapplepenapple"
//    wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//    Output:
//            [
//            "pine apple pen apple",
//            "pineapple pen apple",
//            "pine applepen apple"
//            ]
//    Explanation: Note that you are allowed to reuse a dictionary word.
//    Example 3:
//
//    Input:
//    s = "catsandog"
//    wordDict = ["cats", "dog", "sand", "and", "cat"]
//    Output:
//            []
//

    // return all combinations
    // recursion/DFS -> time out of limit
    public static List<String> wordBreak2(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();
        wordBreakHelper(s, wordDict, result, new ArrayList<>());
        return result;
    }

    private static void wordBreakHelper(String s, List<String> wordDict, List<String> result, List<String> path) {
        if ("".equals(s)) {
            StringBuilder sb = new StringBuilder();
            for (String str: path) {
                sb.append(str);
                sb.append(" ");
            }
            result.add(sb.toString().trim()); // remove last " "
        }

        for (int i = 0; i < s.length(); i ++) {
            String sub = s.substring(0, i + 1);
            if (wordDict.contains(sub)) {
                path.add(sub);
                wordBreakHelper(s.substring(i + 1), wordDict, result, path);
                path.remove(path.size() - 1);
            }
        }
    }

    // return all combinations
    // DP, use List<List<integer>>, to save the word len
    // dp[j].size > 0, the sub string until j) can be ma
    public static List<String> wordBreak21(String s, List<String> wordDict) {
        List<String>[] dp = new ArrayList[s.length() + 1];
        List<String> initial = new ArrayList<>();
        initial.add("");
        dp[0] = initial;
        for (int i = 1; i <= s.length(); i++) {
            List<String> list = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                if (dp[j].size() > 0 && wordDict.contains(s.substring(j, i))) {
                    for (String l : dp[j]) {
                        list.add(l + (l.equals("") ? "" : " ") + s.substring(j, i));
                    }
                }
            }
            dp[i] = list;
        }
        return dp[s.length()];
    }

    //    291. Word Pattern II
//    Given a pattern and a string str, find if str follows the same pattern.
//
//    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.
//
//    Example 1:
//
//    Input: pattern = "abab", str = "redblueredblue"
//    Output: true
//    Example 2:
//
//    Input: pattern = pattern = "aaaa", str = "asdasdasdasd"
//    Output: true
//    Example 3:
//
//    Input: pattern = "aabb", str = "xyzabcxzyabc"
//    Output: false
//    Notes:
//    You may assume both pattern and str contains only lowercase letters.
//
    public static boolean wordPatternMatch(String pattern, String str) {
        return wordPatternMatchHelper(pattern, str, new HashMap<>());
    }

    private static boolean wordPatternMatchHelper(String pattern, String str, Map<Character, String> map) {
        if (pattern.length() == 0 && str.length() == 0) return true;
        if (pattern.length() == 0 || str.length() == 0) return false; // one is empty another is not

        char ch = pattern.charAt(0);
        boolean result = false;
        for (int i = 0; i < str.length(); i ++) {
            String sub = str.substring(0, i + 1);

            // no key no value
            // key with right value
            if ((!map.containsKey(ch) && !map.containsValue(sub)) || sub.equals(map.get(ch))) {
                boolean containsKey = map.containsKey(ch);
                map.put(ch, sub);
                result = result || wordPatternMatchHelper(pattern.substring(1), str.substring(i + 1), map);
                if (!containsKey) map.remove(ch); // remove it from map if it is newly added
            }
        }

        return result;
    }

    //
////    10. Regular Expression Matching
////
////    Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*'.
////
////            '.' Matches any single character.
////            '*' Matches zero or more of the preceding element.
////    The matching should cover the entire input string (not partial).
////
////    Note:
////
////    s could be empty and contains only lowercase letters a-z.
////    p could be empty and contains only lowercase letters a-z, and characters like . or *.
////    Example 1:
////
////    Input:
////    s = "aa"
////    p = "a"
////    Output: false
////    Explanation: "a" does not match the entire string "aa".
////    Example 2:
////
////    Input:
////    s = "aa"
////    p = "a*"
////    Output: true
////    Explanation: '*' means zero or more of the precedeng element, 'a'. Therefore, by repeating 'a' once, it becomes "aa".
////    Example 3:
////
////    Input:
////    s = "ab"
////    p = ".*"
////    Output: true
////    Explanation: ".*" means "zero or more (*) of any character (.)".
////    Example 4:
////
////    Input:
////    s = "aab"
////    p = "c*a*b"
////    Output: true
////    Explanation: c can be repeated 0 times, a can be repeated 1 time. Therefore it matches "aab".
////    Example 5:
////
////    Input:
////    s = "mississippi"
////    p = "mis*is*p*."
////    Output: false
//
    // p includes . and *
    // . match exactly one char
    // * Matches zero or more of the preceding element

    // can not start with a *? always valid?
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();

        // check whether first char is match
        // s.first == p.first or p.first == '.'
        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

        // if the second char in p is *
        // case 1: ignore the char before *, aka take * as 0 -> isMatch(s, p.sub(2))
        // case 2: if the first char is match, isMatch(s.sub(1), p), ex: abcd, a*bcd -> bcd, a*bcd is match as well since * can take 0
        // else: if first is match, isMath(s.sub(1), p.sub(1))
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }
}
