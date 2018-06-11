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

    // 21
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(-1);
        ListNode iter = dummyHead;

        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                iter.next = l1;
                l1 = l1.next;
            } else {
                iter.next = l2;
                l2 = l2.next;
            }
            iter = iter.next;
        }

        iter.next = (l1 != null) ? l1 : l2;
        return dummyHead.next;
    }

    //    237
    //    Input: head = [4,5,1,9], node = 1
    //    Output: [4,5,9]
    //    The given node will not be the tail and it will always be a valid node of the linked list.
    //    Instead, we have to replace the value of the node we want to delete with the value in the node after it, and then delete the node after it.
    public void deleteNode(ListNode node) {
        if (node == null || node.next == null) {
            node = null;
            return;
        }

        node.val = node.next.val;
        node.next = node.next.next;
    }

    // 206
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p = reverseList2(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    // 148
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        // 1. separate into two
        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        // attention here
        ListNode newHead2 = slow.next;
        slow.next = null;

        // head1: head, head2: slow
        ListNode l1 = sortList(head);
        ListNode l2 = sortList(newHead2);
        ListNode newHead = mergeTwoLists(l1, l2);

        return newHead;
    }

    // 141
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null && slow != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }

        return false;
    }
}
