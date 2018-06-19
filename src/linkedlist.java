import java.util.PriorityQueue;
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

    // 23
    //    Input:
//            [
//            1->4->5,
//            1->3->4,
//            2->6
//            ]
//    Output: 1->1->2->3->4->4->5->6
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;

        ListNode dummyHead = new ListNode(-1);
        ListNode iter = dummyHead;
        PriorityQueue<ListNode> queue = new PriorityQueue<>(lists.length, (ListNode l1, ListNode l2) -> l1.val - l2.val);

        // add the nodes from lists to priority queue
        for (ListNode node: lists) {
            if (node != null) {
                queue.add(node);
            }
        }

        while (!queue.isEmpty()) {
            ListNode curNode = queue.poll();
            iter.next = curNode;
            iter = iter.next;

            if (curNode.next != null) {
                queue.add(curNode.next);
            }
        }

        return dummyHead.next;
    }

    // 234
    // check whether a linkedlist is palindrome
    // 1. break the list into two
    // 2. reverse the second half
    // 3. compare one by one
    public static boolean isPalindrome(ListNode head) {
        if (head == null) return false;

        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        ListNode secondHead = slow.next;
        slow.next = null;
        secondHead = reverseLinkedList(secondHead);

        // compare one by one
        while (head != null && secondHead != null) {
            if (head.val != secondHead.val) return false;
            head = head.next;
            secondHead = secondHead.next;
        }

        return true;
    }

    // dummy -> 1 -> 2 -> 3 -> 4
    // prev = dummy, cur = 1, next = 2
    // dummmy <- 1 <- 2
    // cur.next = dummy, 2.next = 1
    private static ListNode reverseLinkedList(ListNode head) {
        ListNode cur = head;
        ListNode prev = null;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }

        return prev;
    }

    // 328
    //    Input: 1->2->3->4->5->NULL
    //    Output: 1->3->5->2->4->NULL
    public static ListNode oddEvenList(ListNode head) {
        ListNode oddHead = new ListNode(-1);
        ListNode evenHead = new ListNode(-1);
        ListNode oddIter = oddHead;
        ListNode evenIter = evenHead;

        int count = 1;
        while (head != null) {
            if (count % 2 == 1) { // odd
                oddIter.next = head;
                oddIter = oddIter.next;
            } else {
                evenIter.next = head;
                evenIter = evenIter.next;
            }
            // notice!
            ListNode next = head.next;
            head.next = null;
            head = next;
            count ++;
        }

        // connect
        oddIter.next = evenHead.next;
        return oddHead.next;
    }

    // 328
    public ListNode oddEvenList2(ListNode head) {
        if (head == null) return null;
        ListNode odd = head, even = head.next, evenHead = even;
        while (even != null && even.next != null) {
            odd.next = even.next;
            odd = odd.next;
            even.next = odd.next;
            even = even.next;
        }
        odd.next = evenHead;
        return head;
    }

    // 147
    public static ListNode insertionSortList(ListNode head) {
        ListNode dummyHead = new ListNode(Integer.MIN_VALUE);
        dummyHead.next = head;
        ListNode cur = head;

        while (cur != null) {
            ListNode next = cur.next;
            cur.next = null;
            insertToSortedList(dummyHead, cur);
            cur = next;
        }

        return dummyHead.next;
    }

    private static void insertToSortedList(ListNode dummyHead, ListNode node) {
        ListNode iter = dummyHead;

        while (iter.next != null) {
            if (node.val >= iter.val && node.val < iter.next.val) break;
            iter = iter.next;
        }

        ListNode next = iter.next;
        iter.next = node;
        node.next = next;
    }

    // 160
    //    A:      a1 → a2
//                   ↘
//                      c1 → c2 → c3
//                   ↗
//    B: b1 → b2 → b3
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        // 1. since there is no cycle, get length from each head
        int countA = getLength(headA);
        int countB = getLength(headB);

        // 2. the longer on move <diff> steps
        int diff = Math.abs(countA - countB);
        while (diff-- > 0) {
            if (countA > countB) {
                headA = headA.next;
            } else {
                headB = headB.next;
            }
        }

        // 3. move together until they met, return null if both reach the end
        while (headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }

        return headA;
    }

    private int getLength(ListNode head) {
        ListNode iter = head;
        int count = 0;

        while (iter != null) {
            iter = iter.next;
            count ++;
        }

        return count;
    }

    // 24
    // Given 1->2->3->4, you should return the list as 2->1->4->3.
    public static ListNode swapPairs(ListNode head) {
        ListNode dummyHead = new ListNode(-1);
        ListNode iter = dummyHead;
        dummyHead.next = head;

        while (iter.next != null && iter.next.next != null) {
            // swap iter.next and iter.next.next
            ListNode next = iter.next;
            iter.next = next.next;
            ListNode nnext = iter.next.next;
            iter.next.next = next;
            next.next = nnext;

            iter = iter.next.next;
        }

        return dummyHead.next;
    }

    // 142
    // Given a linked list, return the node where the cycle begins. If there is no cycle, return null.
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;

            if (slow == fast) {
                fast = head;
                while (slow != fast) {
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;
            }
        }
        return null;
    }
}
