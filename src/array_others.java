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
}
