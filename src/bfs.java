import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class bfs {
    //    126. Word Ladder II
//    Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
//
//    Only one letter can be changed at a time
//    Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
//    Note:
//
//    Return an empty list if there is no such transformation sequence.
//    All words have the same length.
//    All words contain only lowercase alphabetic characters.
//    You may assume no duplicates in the word list.
//    You may assume beginWord and endWord are non-empty and are not the same.
//    Example 1:
//
//    Input:
//    beginWord = "hit",
//    endWord = "cog",
//    wordList = ["hot","dot","dog","lot","log","cog"]
//
//    Output:
//            [
//            ["hit","hot","dot","dog","cog"],
//            ["hit","hot","lot","log","cog"]
//            ]
//    Example 2:
//
//    Input:
//    beginWord = "hit"
//    endWord = "cog"
//    wordList = ["hot","dot","dog","lot","log"]
//
//    Output: []
//
//    Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.

    // print all combinations
    // n words, m len of each word
    // (26 * m * n) + (26 * m * (n - 1)) ...
    // 26 * m * n^2

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        List<List<String>> result = new ArrayList<>();

        int pathLen = Integer.MAX_VALUE;
        Queue<WordWithPath> queue = new LinkedList<>();
        List<String> beginlist = new ArrayList<>();
        beginlist.add(beginWord);

        WordWithPath begin = new WordWithPath(beginWord, beginlist);
        queue.add(begin);

        while (!queue.isEmpty()) {
            WordWithPath wordWithPath = queue.poll();
            if (wordWithPath.path.size() > pathLen) continue;

            // found
            if (endWord.equals(wordWithPath.word)) {
                pathLen = wordWithPath.path.size();
                result.add(wordWithPath.path);
                continue;
            }

            // for each char in word
            char[] wordChs = wordWithPath.word.toCharArray();
            for (int i = 0; i < wordWithPath.word.length(); i ++) {
                char preCh = wordChs[i];

                for (char j = 'a'; j <= 'z'; j ++) { // for a - z
                    wordChs[i] = j;
                    String newWord = new String(wordChs);
                    if (wordList.contains(newWord) && !wordWithPath.path.contains(newWord)) {
                        List<String> list = new ArrayList<>(wordWithPath.path);
                        list.add(newWord);

                        queue.add(new WordWithPath(newWord, list));
                    }
                }

                // put the preCh back
                wordChs[i] = preCh;
            }
        }

        return result;
    }

    static class WordWithPath {
        String word;
        List<String> path;

        WordWithPath(String word, List<String> path) {
            this.word = word;
            this.path = path;
        }
    }

    //    207. Course Schedule
//
//    There are a total of n courses you have to take, labeled from 0 to n-1.
//
//    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
    // [0, 1], take 1 before 0
//
//    Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
//
//    Example 1:
//
//    Input: 2, [[1,0]]
//    Output: true
//    Explanation: There are a total of 2 courses to take.
//    To take course 1 you should have finished course 0. So it is possible.
//    Example 2:
//
//    Input: 2, [[1,0],[0,1]]
//    Output: false
//    Explanation: There are a total of 2 courses to take.
//    To take course 1 you should have finished course 0, and to take course 0 you should
//    also have finished course 1. So it is impossible.
//    Note:
//
//    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
//    You may assume that there are no duplicate edges in the input prerequisites.

    // map<course, # of pre>, you can take the course if it has 0 pre
    // map<course, pre for course 1,2,3>
    // queue<course> the course you can take
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0 || numCourses < 1) {
            return true;
        }

        Queue<Integer> availableCourse = new LinkedList<>();

        // map<course, pre for course 1,2,3>
        Map<Integer, List<Integer>> preCourseMap = new HashMap<>();

        //  # of pre for course i you can take the course if it has 0 pre
        int[] preNumberMap = new int[numCourses];

        // loop pre array, update two maps
        for (int pre[]: prerequisites) {
            // [0, 1], take 1 before 0
            preNumberMap[pre[0]] ++;

            List<Integer> preList = preCourseMap.get(pre[1]);
            if (preList == null) preList = new ArrayList<>();
            preList.add(pre[0]);
            preCourseMap.put(pre[1], preList);
        }

        // update available course queue
        for (int i = 0; i < numCourses; i ++) {
            if (preNumberMap[i] == 0) {
                availableCourse.add(i);
            }
        }

        while (!availableCourse.isEmpty()) {
            Integer course = availableCourse.poll();

            if (!preCourseMap.containsKey(course)) continue;

            List<Integer> courseToTake = preCourseMap.get(course);
            for (int pre: courseToTake) {
                preNumberMap[pre] --;
                if (preNumberMap[pre] == 0) {
                    availableCourse.add(pre);
                }
            }
        }

        for (int num: preNumberMap) {
            if (num > 0) {
                return false;
            }
        }

        return true;
    }

    //    210. Course Schedule II
//
//
//    There are a total of n courses you have to take, labeled from 0 to n-1.
//
//    Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
//
//    Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
//
//    There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
//
//            Example 1:
//
//    Input: 2, [[1,0]]
//    Output: [0,1]
//    Explanation: There are a total of 2 courses to take. To take course 1 you should have finished
//    course 0. So the correct course order is [0,1] .
//    Example 2:
//
//    Input: 4, [[1,0],[2,0],[3,1],[3,2]]
//    Output: [0,1,2,3] or [0,2,1,3]
//    Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both
//    courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
//    So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3] .
//    Note:
//
//    The input prerequisites is a graph represented by a list of edges, not adjacency matrices. Read more about how a graph is represented.
//    You may assume that there are no duplicate edges in the input prerequisites.

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        List<Integer> result = new ArrayList<>();
        Queue<Integer> availableCourse = new LinkedList<>();
        // map<course, pre for course 1,2,3>
        Map<Integer, List<Integer>> preCourseMap = new HashMap<>();
        //  # of pre for course i you can take the course if it has 0 pre
        int[] preNumberMap = new int[numCourses];

        // loop pre array, update two maps
        for (int pre[]: prerequisites) {
            // [0, 1], take 1 before 0
            preNumberMap[pre[0]] ++;

            List<Integer> preList = preCourseMap.get(pre[1]);
            if (preList == null) preList = new ArrayList<>();
            preList.add(pre[0]);
            preCourseMap.put(pre[1], preList);
        }

        // update available course queue
        for (int i = 0; i < numCourses; i ++) {
            if (preNumberMap[i] == 0) availableCourse.add(i);
        }

        while (!availableCourse.isEmpty()) {
            Integer course = availableCourse.poll();
            result.add(course);

            if (!preCourseMap.containsKey(course)) continue;

            List<Integer> courseToTake = preCourseMap.get(course);
            for (int pre: courseToTake) {
                preNumberMap[pre] --;
                if (preNumberMap[pre] == 0) availableCourse.add(pre);
            }
        }

        for (int num: preNumberMap) {
            if (num > 0) result.clear();
        }

        return result.stream().mapToInt(i -> i).toArray();
    }
}
