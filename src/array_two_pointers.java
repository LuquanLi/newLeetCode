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
}
