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

        String[] banned = {"bob", "hit"};

    }

//212. Word Search II
//
//    Given a 2D board and a list of words from the dictionary, find all words in the board.
//
//    Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.
//
//    Example:
//
//    Input:
//    words = ["oath","pea","eat","rain"] and board =
//[
//        ['o','a','a','n'],
//        ['e','t','a','e'],
//        ['i','h','k','r'],
//        ['i','f','l','v']
//        ]
//
//    Output: ["eat","oath"]
//    Note:
//    You may assume that all inputs are consist of lowercase letters a-z.
//
//    public List<String> findWords(char[][] board, String[] words) {
//
//    }

//243. Shortest Word Distance
//
//    Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
//
//    Example:
//    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
//    Input: word1 = “coding”, word2 = “practice”
//    Output: 3
//    Input: word1 = "makes", word2 = "coding"
//    Output: 1
//    Note:
//    You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
//
//    public int shortestDistance(String[] words, String word1, String word2) {
//
//    }

//    244. Shortest Word Distance II
//
//    Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list. Your method will be called repeatedly many times with different parameters.
//
//            Example:
//    Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
//
//    Input: word1 = “coding”, word2 = “practice”
//    Output: 3
//    Input: word1 = "makes", word2 = "coding"
//    Output: 1
//    Note:
//    You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
//
//    public WordDistance(String[] words) {
//
//    }
//
//    public int shortest(String word1, String word2) {
//
//    }
//
//
///**
// * Your WordDistance object will be instantiated and called as such:
// * WordDistance obj = new WordDistance(words);
// * int param_1 = obj.shortest(word1,word2);
// */

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

//    146. LRU Cache
//
//    Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and put.
//
//            get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
//    put(key, value) - Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
//
//    Follow up:
//    Could you do both operations in O(1) time complexity?
//
//    Example:
//
//    LRUCache cache = new LRUCache( 2 /* capacity */ );
//
//cache.put(1, 1);
//cache.put(2, 2);
//cache.get(1);       // returns 1
//cache.put(3, 3);    // evicts key 2
//cache.get(2);       // returns -1 (not found)
//cache.put(4, 4);    // evicts key 1
//cache.get(1);       // returns -1 (not found)
//cache.get(3);       // returns 3
//cache.get(4);       // returns 4
//
//    public LRUCache(int capacity) {
//
//    }
//
//    public int get(int key) {
//
//    }
//
//    public void put(int key, int value) {
//
//    }
//
//    /**
//     * Your LRUCache object will be instantiated and called as such:
//     * LRUCache obj = new LRUCache(capacity);
//     * int param_1 = obj.get(key);
//     * obj.put(key,value);
//     */

//    208. Implement Trie (Prefix Tree)
//
//    Implement a trie with insert, search, and startsWith methods.
//
//            Example:
//
//    Trie trie = new Trie();
//
//trie.insert("apple");
//trie.search("apple");   // returns true
//trie.search("app");     // returns false
//trie.startsWith("app"); // returns true
//trie.insert("app");
//trie.search("app");     // returns true
//    Note:
//
//    You may assume that all inputs are consist of lowercase letters a-z.
//    All inputs are guaranteed to be non-empty strings.
//
//    class Trie {
//
//        /** Initialize your data structure here. */
//        public Trie() {
//
//        }
//
//        /** Inserts a word into the trie. */
//        public void insert(String word) {
//
//        }
//
//        /** Returns if the word is in the trie. */
//        public boolean search(String word) {
//
//        }
//
//        /** Returns if there is any word in the trie that starts with the given prefix. */
//        public boolean startsWith(String prefix) {
//
//        }
//    }
//
///**
// * Your Trie object will be instantiated and called as such:
// * Trie obj = new Trie();
// * obj.insert(word);
// * boolean param_2 = obj.search(word);
// * boolean param_3 = obj.startsWith(prefix);
// */

//    648. Replace Words
//
//    In English, we have a concept called root, which can be followed by some other words to form another longer word - let's call this word successor. For example, the root an, followed by other, which can form another word another.
//
//    Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.
//
//    You need to output the sentence after the replacement.
//
//            Example 1:
//    Input: dict = ["cat", "bat", "rat"]
//    sentence = "the cattle was rattled by the battery"
//    Output: "the cat was rat by the bat"
//    Note:
//    The input will only have lower-case letters.
//1 <= dict words number <= 1000
//            1 <= sentence words number <= 1000
//            1 <= root length <= 100
//            1 <= sentence words length <= 1000
//
//    public String replaceWords(List<String> dict, String sentence) {
//
//    }

//    128. Longest Consecutive Sequence
//
//    Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
//
//    Your algorithm should run in O(n) complexity.
//
//    Example:
//
//    Input: [100, 4, 200, 1, 3, 2]
//    Output: 4
//    Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
//
//    public int longestConsecutive(int[] nums) {
//
//    }

//    547. Friend Circles
//
//    There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.
//
//    Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.
//
//            Example 1:
//    Input:
//            [[1,1,0],
//            [1,1,0],
//            [0,0,1]]
//    Output: 2
//    Explanation:The 0th and 1st students are direct friends, so they are in a friend circle.
//            The 2nd student himself is in a friend circle. So return 2.
//    Example 2:
//    Input:
//            [[1,1,0],
//            [1,1,1],
//            [0,1,1]]
//    Output: 1
//    Explanation:The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends,
//    so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
//    Note:
//    N is in range [1,200].
//    M[i][i] = 1 for all students.
//    If M[i][j] = 1, then M[j][i] = 1.
//
//    public int findCircleNum(int[][] M) {
//
//    }

//    130. Surrounded Regions
//
//    Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
//
//    A region is captured by flipping all 'O's into 'X's in that surrounded region.
//
//            Example:
//
//    X X X X
//    X O O X
//    X X O X
//    X O X X
//    After running your function, the board should be:
//
//    X X X X
//    X X X X
//    X X X X
//    X O X X
//    Explanation:
//
//    Surrounded regions shouldn’t be on the border, which means that any 'O' on the border of the
//    board are not flipped to 'X'. Any 'O' that is not on the border and it is not connected to an 'O' on
//    the border will be flipped to 'X'. Two cells are connected if they are adjacent cells connected horizontally or vertically.
//
//    public void solve(char[][] board) {
//
//    }

//    305. Number of Islands II
//
//    A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand operation which turns the water at position (row, col) into a land. Given a list of positions to operate, count the number of islands after each addLand operation. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//
//    Example:
//
//    Input: m = 3, n = 3, positions = [[0,0], [0,1], [1,2], [2,1]]
//    Output: [1,1,2,3]
//    Explanation:
//
//    Initially, the 2d grid grid is filled with water. (Assume 0 represents water and 1 represents land).
//
//            0 0 0
//            0 0 0
//            0 0 0
//    Operation #1: addLand(0, 0) turns the water at grid[0][0] into a land.
//
//          1 0 0
//          0 0 0   Number of islands = 1
//          0 0 0
//    Operation #2: addLand(0, 1) turns the water at grid[0][1] into a land.
//
//          1 1 0
//          0 0 0   Number of islands = 1
//          0 0 0
//    Operation #3: addLand(1, 2) turns the water at grid[1][2] into a land.
//
//          1 1 0
//          0 0 1   Number of islands = 2
//          0 0 0
//    Operation #4: addLand(2, 1) turns the water at grid[2][1] into a land.
//
//          1 1 0
//          0 0 1   Number of islands = 3
//          0 1 0
//    Follow up:
//
//    Can you do it in time complexity O(k log mn), where k is the length of the positions?
//
//    public List<Integer> numIslands2(int m, int n, int[][] positions) {
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