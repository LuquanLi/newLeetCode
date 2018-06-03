import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class array_others {
    // 66
    public int[] plusOne(int[] digits) {
        // input validation
        if (digits == null || digits.length == 0) {
            return new int[0];
        }

        // add one from least important digit, carry = 1 when nums[i] + 1 = 10, carry to next level
        // int carry = 1;
        // for (int i = digits.length - 1; i >= 0; i --) {
        //     int curDigit = digits[i];
        //     digits[i] = (curDigit + carry) % 10;
        //     carry = (curDigit + carry) / 10;
        //     if (carry == 0) {
        //         return digits;
        //     }
        // }

        // no need carry
        for (int i = digits.length - 1; i >= 0; i --) {
            if (digits[i] < 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }

        // if carry is still 1 after loop, need add extra digit for array
        // digits must be ex: 999 + 1 = 1000
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }

    // 43
    public String multiply(String num1, String num2) {
        // input validation
        if (num1 == null || num2 == null) {
            return null;
        }

        // digit m * digit n, result has most m + n digits
        int[] result = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >=0; i --) {
            for (int j = num2.length() - 1; j >=0; j --) {
                result[i + j + 1] = result[i + j + 1] + (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
                result[i + j] = result[i + j] + result[i + j + 1] / 10;
                result[i + j + 1] = result[i + j + 1] % 10;
            }
        }

        // remove leading 0
        StringBuilder sb = new StringBuilder();
        boolean nonZero = false;
        for (int i = 0; i < result.length; i ++) {
            if (result[i] != 0) nonZero = true;
            if (nonZero) sb.append(result[i]);
            if (i == result.length - 1 && !nonZero) return "0"; // all 0 in result array
        }
        return sb.toString();
    }

    // 121, stock 1
    public int maxProfit(int[] prices) {
        // input validation
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        // min price: Min(p[0], p[1] ... p[i])
        // local max profit: max profit for current price: prices[i] - minPrice
        // global max profix: Max(all local max P)
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i ++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
        }

        return maxProfit;
    }

    // 122. stock 2
    public int maxProfit2(int[] prices) {
        // input validation
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        // if P[i + 1] > P[i], buy and sell
        int totalProfit = 0;

        // not buy at last day
        for (int i = 0; i < prices.length - 1; i++){
            if (prices[i + 1] > prices[i]) {
                totalProfit += prices[i + 1] - prices[i];
            }
        }

        return totalProfit;
    }

    // 123 stock3
    // buy/sell stock at most two tranction
    public static int maxProfit3(int[] prices) {
        // input validation
        if (prices == null || prices.length <= 1) {
            return 0;
        }

        // get the max profit in first transaction
        int[] firstMaxProfit = new int[prices.length];
        int maxProfit = 0, minPrice = prices[0];

        for (int i = 1; i < prices.length; i ++) {
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            minPrice = Math.min(minPrice, prices[i]);
            firstMaxProfit[i] = maxProfit;
        }

        // loop from backward
        // ex: the buy/sell -> (0, i], second -> [i + 1, n]
        // get the max profit for second: MaxPrice[i + 2, n], profit = MaxPrice - prices[i+1]
        int maxPrice = prices[prices.length - 1], totalMaxProfit = 0;
        for (int i = prices.length - 2; i > 1; i --) {
            int secondProfit = maxPrice - prices[i];
            if (secondProfit >= 0) { // second buy at i
                totalMaxProfit = Math.max(totalMaxProfit, secondProfit + firstMaxProfit[i - 1]);
            }
            maxPrice = Math.max(maxPrice, prices[i]);
        }

        // firstMaxProfit[prices.length - 2] and firstMaxProfit[prices.length - 1]
        // no 2nd transaction
        return Math.max(
                Math.max(totalMaxProfit, firstMaxProfit[prices.length - 2])
                , firstMaxProfit[prices.length - 1]);
    }

    // 280. Wiggle Sort
    // the number in odd index is greater than it's neighbour
    // 1. sort, then swap(1,2), swap(3,4)
    public void wiggleSort(int[] nums) {
        // input validation
        if (nums == null || nums.length < 2) {
            return;
        }

        for (int i = 1; i < nums.length; i ++) {
            if ((i % 2 == 1 && nums[i] < nums[i - 1]) || (i % 2 ==0 && nums[i] > nums[i - 1])) {
                swap(nums, i, i- 1);
            }
        }
    }

    // swap the value in index m and n
    private void swap(int[] nums, int m, int n) {
        int temp = nums[m];
        nums[m] = nums[n];
        nums[n] = temp;
    }

    // 31

    // 1. find the decreasing order in back, ex: [i, n)
    // 2. value[i - 1]
    // 3. get the least larger value > value[i - 1] in range [i, n)
    // 4. swap value[i - 1] and value[k]
    // 5. reverse [i, n) to increasing order

    // there might be dup number
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) {
            return;
        }

        // index for the head of decreasing sequence at rear
        int inversionPoint = nums.length - 1;
        while (inversionPoint > 0 && nums[inversionPoint - 1] >= nums[inversionPoint]) {
            inversionPoint --;
        }

        if (inversionPoint == 0) {
            reverseArray(nums, 0, nums.length - 1);
        } else {
            // get the least larger value greater than invPoint
            int indexLeastLarger = inversionPoint;
            for (int i = inversionPoint + 1; i < nums.length; i ++) {
                if (nums[inversionPoint - 1] < nums[i] && nums[indexLeastLarger] >= nums[i]) {
                    indexLeastLarger = i;
                }
            }

            swap(nums, inversionPoint - 1, indexLeastLarger);

            // reverse
            reverseArray(nums, inversionPoint, nums.length - 1);
        }

    }

    private void reverseArray(int[] nums, int i, int j) {
        while (i < j) {
            swap(nums, i++, j--);
        }
    }

    // 36
    char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
    };
    public boolean isValidSudoku(char[][] board) {
        // validation sudoku size
        if (null == board || board.length != 9 || board[0].length != 9) {
            return false;
        }

        // check row
        for (int i = 0; i < board.length; i ++) {
            if (hasDuplicate(board, i, i + 1, 0, board[0].length)) {
                return false;
            }
        }

        // check column
        for (int j = 0; j < board[0].length; j ++) {
            if (hasDuplicate(board, 0, board.length, j, j + 1)) {
                return false;
            }
        }

        // check each 3 by 3 block
        for (int i = 0; i < board.length; i += 3) {
            for (int j = 0; j < board[0].length; j += 3) {
                if (hasDuplicate(board, i, i + 3, j, j + 3)) {
                    return false;
                }
            }
        }

        return true;
    }

    // whether there is duplicate char in range [sRow, eRow), [sCol, eCol)
    private boolean hasDuplicate(char[][] board, int sRow, int eRow, int sCol, int eCol) {
        Set<Character> set = new HashSet<>();
        for (int i = sRow; i < eRow; i ++){
            for (int j = sCol; j < eCol; j ++) {
                if (board[i][j] != '.'){
                    if (set.contains(board[i][j])) {
                        return true;
                    }
                    set.add(board[i][j]);
                }
            }
        }
        return false;
    }

    // 118
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> result = new ArrayList<>();
        if (numRows < 1) return result;

        int row = 1;
        while (row <= numRows) {
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < row; i ++) {
                if (i == 0 || i == row - 1) {
                    level.add(1);
                } else {
                    // get value from previous level
                    // current level is row - 1, previous level is row - 2
                    level.add(result.get(row - 2).get(i - 1) + result.get(row - 2).get(i));
                }
            }
            result.add(level);
            row ++;
        }

        return result;
    }

    // 119
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result;
        List<Integer> curLevel = new ArrayList<>();
        int curRow = 1;

        while (curRow <= rowIndex + 1) {
            result = new ArrayList<>();
            for (int i = 0; i < curRow; i ++) {
                if (i == 0 || i == curRow - 1) {
                    result.add(1);
                } else {
                    result.add(curLevel.get(i - 1) + curLevel.get(i));
                }
            }
            curRow ++;
            curLevel = result;
        }

        return curLevel;
    }

}
