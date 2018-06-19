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
        ListNode n6 = new ListNode(6);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n6;

//        ListNode head = swapPairs(n1);
//        while (head != null) {
//            System.out.println(head.val);
//            head = head.next;
//        }

    }


}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
    int getVal() { return  val; }
}