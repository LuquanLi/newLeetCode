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
}
