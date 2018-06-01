import java.util.Arrays;

public class math {
    // 67
    public String addBinary(String a, String b) {
        // append to sb first, then reverse
        StringBuilder result = new StringBuilder();

        // start with least important digit
        int i = a.length() - 1, j = b.length() - 1;
        char carry = '0';

        while (i >= 0 || j >= 0) {
            char chA = (i >= 0) ? a.charAt(i--) : '0';
            char chB = (j >= 0) ? b.charAt(j--) : '0';

            // count '1'
            int count = (chA - '0') + (chB - '0') + (carry - '0');
//            if (count == 3) {
//                result.append('1'); // carry is still '1'
//            } else if (count == 2) {
//                result.append('0');
//                carry = '1';
//            } else if (count == 1) {
//                result.append('1');
//                carry = '0';
//            } else { // count == 0
//                result.append('0'); //carry is still '0';
//            }

            result.append(count % 2 == 1 ? '1' : '0');
            carry = count >= 2 ? '1' : '0';
        }

        if (carry == '1') {
            result.append(carry);
        }

        return result.reverse().toString();
    }

    // 2
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode fakeHead = new ListNode(-1);
        ListNode pointer = fakeHead;
        int carry = 0;

        // one is longer than another
        while (l1 != null || l2 != null) {
            if (l1 == null) l1 = new ListNode(0);
            if (l2 == null) l2 = new ListNode(0);

            ListNode node = new ListNode((l1.val + l2.val + carry) % 10);
            carry = (l1.val + l2.val + carry) / 10;

            pointer.next = node;
            pointer = pointer.next;
            l1 = l1.next;
            l2 = l2.next;
        }

        if (carry == 1) {
            pointer.next = new ListNode(1);
        }

        return fakeHead.next;
    }

    // 204
    public int countPrimes(int n) {
        // 0 and 1 is not prime
        if (n < 2) {
            return 0;
        }

        int count = n - 2;
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for (int i = 2; i < n; i ++) {
            if (isPrime[i]) {
                for (int j = i * 2; j < n; j += i) {
                    if (isPrime[j]) count --;
                    isPrime[j] = false;
                }
            }
        }

        return count;
    }

    // 263
    public boolean isUgly(int num) {
        if (num < 1) {
            return false;
        }

        while (num > 1) {
            if (num % 5 == 0) {
                num /= 5;
            } else if (num % 3 == 0) {
                num /= 3;
            } else if (num % 2 == 0) {
                num /= 2;
            } else {
                return false;
            }
        }

        return true;
    }

    // 264
    // we know the first seq of ugly num is : 1 2 3 4 5
    // seq2: 2 4 6 8 10
    // seq3: 3 6 9 12 15
    // seq5: 5 10 15 20 25
    public int nthUglyNumber(int n) {
        int[] ugly = new int[n];
        ugly[0] = 1;
        int index2 = 0, index3 = 0, index5 = 0;
        int factor2 = 2, factor3 = 3, factor5 = 5;

        for (int i = 1; i < n; i ++) {
            int min = Math.min(factor2, Math.min(factor3, factor5));
            ugly[i] = min;

            // can not be else if here, like 6 appears for both factor, else if will add 6 twice
            if (factor2 == min) {
                factor2 = 2 * ugly[++ index2];
            }

            if (factor3 == min) {
                factor3 = 3 * ugly[++ index3];
            }

            if (factor5 == min) {
                factor5 = 5 * ugly[++ index5];
            }
        }
        return ugly[n - 1];
    }
}
