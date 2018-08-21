import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class hashMap {
    // 1
    //    Given nums = [2, 7, 11, 15], target = 9,
    //
    //    Because nums[0] + nums[1] = 2 + 7 = 9,
    //    return [0, 1].
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) return null;
        HashMap<Integer, Integer> valueIndexMap = new HashMap<>();

        for (int i = 0; i < nums.length; i ++) {
            if (valueIndexMap.containsKey(target - nums[i])) {
                return new int[] { valueIndexMap.get(target - nums[i]), i };
            }
            valueIndexMap.put(nums[i], i);
        }

        return null;
    }

    // 167
    //    Input: numbers = [2,7,11,15], target = 9
//    Output: [1,2]
//    Explanation: The sum of 2 and 7 is 9. Therefore index1 = 1, index2 = 2.
//    two pointers
//    Let [... , a, b, c, ... , d, e, f, ...][...,a,b,c,...,d,e,f,...] be the input array that is sorted
//    in ascending order and the element bb, ee be the exactly only solution. Because we are moving the
//    smaller index from left to right, and the larger index from right to left, at some point one of the
//    indexes must reach either one of bb or ee. Without loss of generality, suppose the smaller index reaches
//    bb first. At this time, the sum of these two elements must be greater than target. Based on our algorithm,
//    we will keep moving the larger index to its left until we reach the solution.
    public int[] twoSum2(int[] numbers, int target) {
        if (numbers == null || numbers.length < 2) return null;

        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[] { left + 1, right + 1 };
            } else if (sum < target) {
                left ++;
            } else { // sum > target
                right --;
            }
        }

        return null;
    }

    // 15
//    Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
//
//            Note:
//
//    The solution set must not contain duplicate triplets.
//
//    Example:
//
//    Given array nums = [-1, 0, 1, 2, -1, -4],
//
//    A solution set is:
//            [
//            [-1, 0, 1],
//            [-1, -1, 2]
//            ]
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) return result;

        // sort array
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i ++) {
            if( i== 0 || nums[i] > nums[i-1]){
                int left = i + 1, right = nums.length - 1;

                while (left < right) {
                    int sum = nums[i] + nums[left] + nums[right];
                    if (sum == 0) {
                        List<Integer> triplet = Arrays.asList(nums[i], nums[left], nums[right]);
                        result.add(triplet);

                        do {
                            left ++;
                        } while (left < right && nums[left] == nums[left - 1]);

                        do {
                            right --;
                        } while (left < right && nums[right] == nums[right + 1]);

                    } else if (sum < 0) {
                        left ++;
                    } else {
                        right --;
                    }
                }
            }
        }

        return result;
    }

    //    Given a non-empty array of integers, return the k most frequent elements.
//
//            For example,
//    Given [1,1,1,2,2,3] and k = 2, return [1,2].
//
//    Note:
//    You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
//    Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

    public static List<Integer> topKFrequent(int[] nums, int k) {
        List<Integer> result = new ArrayList<>();
        if (nums == null || nums.length == 0) return result;

        PriorityQueue<Node> frquencyQueue = new PriorityQueue<>(Comparator.comparingInt((Node n) -> n.frequency));
        Map<Integer, Integer> valueFrequencyMap = new HashMap<>();
        for (int num: nums) {
            int freq = valueFrequencyMap.getOrDefault(num, 0);
            valueFrequencyMap.put(num, freq + 1);
        }

        for (Map.Entry<Integer, Integer> entry : valueFrequencyMap.entrySet()) {
            frquencyQueue.add(new Node(entry.getKey(), entry.getValue()));
            if (frquencyQueue.size() > k) {
                frquencyQueue.poll();
            }
        }

        for (Node node: frquencyQueue) result.add(node.value);
        return result;
    }

    static class Node {
        private int value;
        private int frequency;

        Node(int value, int frequency) {
            this.value = value;
            this.frequency = frequency;
        }
    }


    //290. Word Pattern
//    Given a pattern and a string str, find if str follows the same pattern.
//
//    Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
//
//    Example 1:
//
//    Input: pattern = "abba", str = "dog cat cat dog"
//    Output: true
//    Example 2:
//
//    Input:pattern = "abba", str = "dog cat cat fish"
//    Output: false
//    Example 3:
//
//    Input: pattern = "aaaa", str = "dog cat cat dog"
//    Output: false
//    Example 4:
//
//    Input: pattern = "abba", str = "dog dog dog dog"
//    Output: false
//    Notes:
//    You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.
//
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if (pattern.length() != words.length) return false;

        Map<Character, String> patternWordMap = new HashMap<>();

        for (int i = 0; i < words.length; i ++) {
            char ch = pattern.charAt(i);
            String word = words[i];

            if (patternWordMap.containsKey(ch)) {
                if (!patternWordMap.get(ch).equals(word)) return false;
            } else {
                if (patternWordMap.containsValue(word)) return false;
                patternWordMap.put(ch, word);
            }
        }

        return true;
    }
}
