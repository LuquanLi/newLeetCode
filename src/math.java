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
}
