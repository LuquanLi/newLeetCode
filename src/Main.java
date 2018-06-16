import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;

        System.out.println();
//        //ListNode r = oddEvenList(n1);
//        while (r != null) {
//            System.out.println(r.val);
//            r = r.next;
//        }

    }

//    Input: 4->2->1->3
//    Output: 1->2->3->4
    public ListNode insertionSortList(ListNode head) {

    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    int getVal() { return  val; }
}