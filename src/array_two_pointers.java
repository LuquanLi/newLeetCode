public class array_two_pointers {
    // 283
    public void moveZeroes(int[] nums) {
        // input validation
        if (nums == null || nums.length <= 1) {
            return;
        }

        // p points to the moved array
        // q is pointer loop through each element
        int p = 0, q = 0;
        while (q < nums.length) {
            if (nums[q] != 0) {
                nums[p++] = nums[q];
            }
            q ++;
        }

        // fill zeros after p
        while (p < nums.length) {
            nums[p++] = 0;
        }
    }

    // 27
    public int removeElement(int[] nums, int val) {
        // input validation
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // two pointers, p points to moved elements, q loop through array
        int p = 0, q = 0;
        while (q < nums.length) {
            if (nums[q] != val) {
                // nums[q] moves to p position
                nums[p] = nums[q];
                p ++;
            }
            q ++;
        }

        return p;
    }

    // 26
    public int removeDuplicates(int[] nums) {
        // input validation
        if (nums == null || nums.length == 0) {
            return 0;
        }

        // two pointers
        int p = 0, q = 0;
        while (++q < nums.length) {
            if (nums[p] != nums[q]) {
                nums[++p] = nums[q];
                // p ++; before is nums[p + 1] = nums[q]
            }
            // q ++; change to ++q above
        }

        return ++p;
    }

    // 80
    public int removeDuplicates2(int[] nums) {
        if (nums.length < 3) {
            return nums.length;
        }

        int p = 2, q = 2;
        while (q < nums.length) {
            if (nums[q] > nums[p - 2]) {
                nums[p] = nums[q];
                p ++;
            }
            q ++;
        }
        return p;

/*        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2])
                nums[i++] = n;
        }

        return i;*/
    }

    // 75
    public void sortColors(int[] nums) {
        // input validation
        if (nums == null || nums.length <= 1) {
            return;
        }

        // three pointers
        // p: all zeros before p
        // q: all twos after q
        // i: loop through array
        int p = 0, q = nums.length - 1, i = 0;
        while (i <= q) {
            if (nums[i] == 0) {
                swap(nums, i++, p++); // i is moving here, b/c nums[p] can only be 1 here, 2 already swap to back when i pass by
            } else if (nums[i] == 2) {
                swap(nums, i, q--); // i is not move forward here,b/c nums[q] can be 0, which should swap front
            } else { // nums[i] == 1
                i ++;
            }
        }
    }

    // swap value in inedx m,n in array nums
    private void swap(int[] nums, int m, int n) {
        int tmp = nums[m];
        nums[m] = nums[n];
        nums[n] = tmp;
    }

    /* extra:
    Given an array of integers (positive and negative numbers), sort the array in the following way: all the negative numbers are in the front and all the positive numbers are in the end, while keeping the relative position.
eg.
input: -1,1,5,-9,4,-3
output: -1,-9,-3,1,5,4

1. two queues (list etc), one keeps negative, one keeps positive, O(n) time, O(n) space
2. two pointers:
  -1, 1, 5, -9, 4, -3
  find -9, swap forward: -1, 1, -9, 5, 4, -3   ->  -1, -9, 1, 5, 4, -3
  O(n^2) time, O(1) space
3. O(nlogn), use merge sort: https://leetcode.com/discuss/interview-question/124711/sort-array-with-negative-numbers-followed-by-positive-numbers
     */

    // way 2
    public static void rearrange(int[] nums) {
        // input validation
        if (nums == null || nums.length <= 1) {
            return;
        }

        // two pointers
        // p: all negative number before p
        // q: loop through array
        int p = 0;
        while (p < nums.length && nums[p] < 0) {
            p ++;
        }
        int q = p + 1;

        while (q < nums.length) {
            if (nums[q] < 0) {
                moveElement(nums, p, q);
                p ++;
            }
            q ++;
        }
    }

    // move element in j to i
    private static void moveElement(int[] nums, int i, int j) {
        while (i < j) {
            // swap j and j - 1
            int tmp = nums[j - 1];
            nums[j - 1] = nums[j];
            nums[j] = tmp;
            j --;
        }
    }
}
