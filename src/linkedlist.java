import java.util.Stack;

public class linkedlist {

    // 445
    //    Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    //    Output: 7 -> 8 -> 0 -> 7
    // The most significant digit comes first
    // 1. reverse linked list
    // 2. using stack, pop 3 + 4, the new node will on the top of existing node head
    // but the time/space is as same as reverse linkedlist
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> stackL1 = new Stack<>();
        Stack<Integer> stackL2 = new Stack<>();

        while (l1 != null) {
            stackL1.push(l1.val);
            l1 = l1.next;
        }

        while (l2 != null) {
            stackL2.push(l2.val);
            l2 = l2.next;
        }

        ListNode head = new ListNode(0);
        while (!stackL1.empty() || !stackL2.empty()) {
            int valL1 = stackL1.empty() ? 0 : stackL1.pop();
            int valL2 = stackL2.empty() ? 0 : stackL2.pop();

            ListNode pre = new ListNode((valL1 + valL2 + head.val) / 10); // pre -> head
            head.val = (valL1 + valL2 + head.val) % 10;
            pre.next = head;
            head = pre; // pre is the new head
        }

        // remove the leading 0
        return head.val == 0 ? head.next : head;
    }
}
