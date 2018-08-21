import com.sun.tools.javac.util.StringUtils;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("HELLO WORLD");
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};

        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        TreeNode t6 = new TreeNode(6);

        t1.left = t2;
        t1.right = t5;
        t2.left = t3;
        t2.right = t4;
        t5.right = t6;

        UndirectedGraphNode n0 = new UndirectedGraphNode(0);
        UndirectedGraphNode n1 = new UndirectedGraphNode(1);
        UndirectedGraphNode n2 = new UndirectedGraphNode(2);

        n0.neighbors.add(n1);
        n0.neighbors.add(n2);
        n1.neighbors.add(n2);
        n2.neighbors.add(n2);

        String[] array = {"apple", "pen", "applepen", "pine", "pineapple"};
        List<String> list = Arrays.asList(array);

        char[][] board = {
                {'A', 'B', 'C', 'E'},
                {'S', 'F', 'C', 'S'},
                {'A', 'D', 'E', 'E'}
        };

        System.out.println(exist(board, "SEE"));
    }

//    79. Word Search
//
//    Given a 2D board and a word, find if the word exists in the grid.
//
//    The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.
//
//            Example:
//
//    board =
//            [
//            ['A','B','C','E'],
//            ['S','F','C','S'],
//            ['A','D','E','E']
//            ]
//
//    Given word = "ABCCED", return true.
//    Given word = "SEE", return true.
//    Given word = "ABCB", return false.
//
    // letter only use once
    //
    public static boolean exist(char[][] board, String word) {
        if (board == null || word == null) return false;

        for (int i = 0; i < board.length; i ++) {
            for (int j = 0; j < board[0].length; j ++) {
                
            }
        }

        return wordSearchHelper(0, 0, board, word);
    }

    private static boolean wordSearchHelper(int i, int j, char[][] board, String word) {
        if (word.isEmpty()) return true; // reach the end
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length) return false; // out of range

        char ch = word.charAt(0);
        char temp = board[i][j];

        if (temp != ch) return false;

        board[i][j] = '.'; // mark

        boolean result = wordSearchHelper(i + 1, j, board, word.substring(1))
                || wordSearchHelper(i - 1, j, board, word.substring(1))
                || wordSearchHelper(i, j + 1, board, word.substring(1))
                || wordSearchHelper(i, j - 1, board, word.substring(1));

        board[i][j] = temp;

        return result;
    }

//819. Most Common Word
//
//    Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words.  It is guaranteed there is at least one word that isn't banned, and that the answer is unique.
//
//    Words in the list of banned words are given in lowercase, and free of punctuation.  Words in the paragraph are not case sensitive.  The answer is in lowercase.
//
//            Example:
//    Input:
//    paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
//    banned = ["hit"]
//    Output: "ball"
//    Explanation:
//            "hit" occurs 3 times, but it is a banned word.
//            "ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph.
//    Note that words in the paragraph are not case sensitive,
//    that punctuation is ignored (even if adjacent to words, such as "ball,"),
//    and that "hit" isn't the answer even though it occurs more because it is banned.
//
//
//    Note:
//
//            1 <= paragraph.length <= 1000.
//            1 <= banned.length <= 100.
//            1 <= banned[i].length <= 10.
//    The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
//    paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
//    Different words in paragraph are always separated by a space.
//    There are no hyphens or hyphenated words.
//    Words only consist of letters, never apostrophes or other punctuation symbols.
//
//    public String mostCommonWord(String paragraph, String[] banned) {
//
//    }

//    692. Top K Frequent Words
//    Given a non-empty list of words, return the k most frequent elements.
//
//    Your answer should be sorted by frequency from highest to lowest. If two words have the same frequency, then the word with the lower alphabetical order comes first.
//
//    Example 1:
//    Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
//    Output: ["i", "love"]
//    Explanation: "i" and "love" are the two most frequent words.
//    Note that "i" comes before "love" due to a lower alphabetical order.
//    Example 2:
//    Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
//    Output: ["the", "is", "sunny", "day"]
//    Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
//    with the number of occurrence being 4, 3, 2 and 1 respectively.
//            Note:
//    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
//    Input words contain only lowercase letters.
//    Follow up:
//    Try to solve it in O(n log k) time and O(n) extra space.
//    public List<String> topKFrequent(String[] words, int k) {
//
//    }

//
////    152. Maximum Product Subarray
////
////    Given an integer array nums, find the contiguous subarray within an array (containing at least one number) which has the largest product.
////
////    Example 1:
////
////    Input: [2,3,-2,4]
////    Output: 6
////    Explanation: [2,3] has the largest product 6.
////    Example 2:
////
////    Input: [-2,0,-1]
////    Output: 0
////    Explanation: The result cannot be 2, because [-2,-1] is not a subarray.
//
//    public int maxProduct(int[] nums) {
//
//    }
}

class UndirectedGraphNode {
   int label;
   List<UndirectedGraphNode> neighbors;
   UndirectedGraphNode(int x) { label = x; neighbors = new ArrayList<UndirectedGraphNode>(); }
};

class TreeLinkNode {
     int val;
     TreeLinkNode left, right, next;
     TreeLinkNode(int x) { val = x; }
}

class Interval {
    int start;
     int end;
     Interval() { start = 0; end = 0; }
     Interval(int s, int e) { start = s; end = e; }
}

class Point {
     int x;
     int y;
     Point() { x = 0; y = 0; }
     Point(int a, int b) { x = a; y = b; }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    int getVal() { return  val; }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}