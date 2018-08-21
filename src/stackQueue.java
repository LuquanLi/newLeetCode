import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class stackQueue {
    // 20
    //    Input: "()[]{}"
    //    Output: true
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();

        for (char ch: s.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack.push(ch);
            } else {
                if (stack.isEmpty()
                        || (ch == ')' && stack.pop() != '(')
                        || (ch == '}' && stack.pop() != '{')
                        || (ch == ']' && stack.pop() != '[')) {
                    return false;
                }
            }
        }

        // do not forget check whether the stack is empty
        return stack.isEmpty();
    }

    // 155
    //    push(x) -- Push element x onto stack.
    //    pop() -- Removes the element on top of the stack.
    //    top() -- Get the top element.
    //    getMin() -- Retrieve the minimum element in the stack.
    static class MinStack {
        int min = Integer.MAX_VALUE;
        Stack<Integer> stack = new Stack<Integer>();
        public void push(int x) {
            // only push the old minimum value when the current
            // minimum value changes after pushing the new value x
            // 把它前一个最小的也存在stack里， pop时 要是pop的是min，把上一个也pop出来 这时最小的就是他前一个最小的
//            MinStack minStack = new MinStack();
//            minStack.push(0);
//            minStack.push(-1);
//            minStack.push(-2);
//            minStack.push(-3);
            // stack 里：MAX, 0,0,-1,-1,-2,-2,-3
            if(x <= min){
                stack.push(min);
                min=x;
            }
            stack.push(x);
        }

        public void pop() {
            // if pop operation could result in the changing of the current minimum value,
            // pop twice and change the current minimum value to the last minimum value.
            if(stack.pop() == min) min=stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    // 316
    // https://leetcode.com/problems/remove-duplicate-letters/discuss/76762/Java-O(n)-solution-using-stack-with-detail-explanation
/*    Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once.
    You must make sure your result is the smallest in lexicographical order among all possible results.
    Input: "bcabc"
    Output: "abc"*/
    public static String removeDuplicateLetters(String s) {
        Stack<Character> stack = new Stack<>();
        int[] count = new int[26];
        char[] arr = s.toCharArray();
        for(char c : arr) {
            count[c-'a']++;
        }
        boolean[] visited = new boolean[26]; // there is this char in stack
        for(char c : arr) {
            count[c-'a']--;
            if(visited[c-'a']) {
                continue;
            }
            while(!stack.isEmpty() && stack.peek() > c && count[stack.peek()-'a'] > 0) {
                visited[stack.peek()-'a'] = false;
                stack.pop();
            }
            stack.push(c);
            visited[c-'a'] = true;
        }
        StringBuilder sb = new StringBuilder();
        for(char c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }

    // basic calculater generic solution
    public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> nums = new Stack<>(); // the stack that stores numbers
        Stack<Character> ops = new Stack<>(); // the stack that stores operators (including parentheses)
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = c - '0';
                // iteratively calculate each number
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i+1))) {
                    num = num * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                nums.push(num);
                num = 0; // reset the number to 0 before next calculation
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // do the math when we encounter a ')' until '('
                while (ops.peek() != '(')
                    nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                ops.pop(); // get rid of '(' in the ops stack
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && precedence(c, ops.peek()))
                    nums.push(operation(ops.pop(), nums.pop(),nums.pop()));
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }

    private static int operation(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // assume b is not 0
        }
        return 0;
    }
    // helper function to check precedence of current operator and the uppermost operator in the ops stack
    // whether calculate op2 first
    private static boolean precedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }

    //301. Remove Invalid Parentheses
//
//    Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.
//
//    Note: The input string may contain letters other than the parentheses ( and ).
//
//    Example 1:
//
//    Input: "()())()"
//    Output: ["()()()", "(())()"]
//    Example 2:
//
//    Input: "(a)())()"
//    Output: ["(a)()()", "(a())()"]
//    Example 3:
//
//    Input: ")("
//    Output: [""]

    // contain not only ( )
    // remove min parentheses
    // return all possible result

    // (())) ... one extra ), remove any ), make sure there is no dupe
    // therefore, we only remove the first one in consecutive )))
    // ()()) ... one extra ), remove any ), make sure there is no dupe

    // first loop we can only remove dupe ), what if (()
    // reverse the string: )((, find pair )(, then remove dupe (
    // when finish -> )( -> reverse it back to add to result list ()
    // TOO FKING HARD!
    public static List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();

        // last_i and last_j means
        // last_i is the start point we start to loop, before last_i, is valid
        // last_j is we have remove ) in this position, we should only remove ) after
        remove(s, ans, 0, 0, new char[]{'(', ')'});
        return ans;
    }

    public static void remove(String s, List<String> ans, int last_i, int last_j,  char[] par) {
        // ex pair = ( )
        int stack = 0; // stack is how many ( left
        for (int i = last_i; i < s.length(); i++) {
            if (s.charAt(i) == par[0]) stack++; // get one (
            if (s.charAt(i) == par[1]) stack--; // get one )
            if (stack >= 0) continue; // looks good for now
            for (int j = last_j; j <= i; ++j) // remove one ) from last_j to i
                // Thus, we restrict ourself to remove the first ) in a series of concecutive )s
                if (s.charAt(j) == par[1] && (j == last_j || s.charAt(j - 1) != par[1]))
                    // remove char at j
                    remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
            return;
        }
        String reversed = new StringBuilder(s).reverse().toString();
        if (par[0] == '(') // finished left to right
            remove(reversed, ans, 0, 0, new char[]{')', '('});
        else // finished right to left
            ans.add(reversed);
    }

    // easy understand way
    // brute force
    public static List<String> removeInvalidParentheses2(String s) {
        List<String> res = new ArrayList<>();

        // sanity check
        if (s == null) return res;

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>(); // candidates

        // initialize
        queue.add(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            s = queue.poll();

            // found an answer, add to the result
            if (isValid2(s)) {
                res.add(s);
                found = true;
            }

            // if we already found there is valid str, just keep checking whatever left in queue, no need to keep remove more
            if (found) continue;

            // generate all possible states
            for (int i = 0; i < s.length(); i++) {
                // ignore other chars
                if (s.charAt(i) != '(' && s.charAt(i) != ')') continue;

                String t = s.substring(0, i) + s.substring(i + 1);

                // for each state, if it's not visited, add it to the queue
                if (visited.add(t)) {
                    queue.add(t);
                }
            }
        }

        return res;
    }

    // helper function checks if string s contains valid parantheses
    static boolean isValid2(String s) {
        int count = 0;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') count++;
            if (c == ')' && count-- == 0) return false;
        }

        return count == 0;
    }
}
