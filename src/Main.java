import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

public class Main {

    public static void main(String[] args) {
        System.out.println(getRow(5));
    }

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> result;
        List<Integer> curLevel = new ArrayList<>();
        int curRow = 1;

        while (curRow <= rowIndex) {
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

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}