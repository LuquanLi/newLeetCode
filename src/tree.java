import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class tree {
    // 94
    //            1
//              \
//              2
//              /
//              3
//
//    Output: [1,3,2]
// left, root, right
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        inorderTraversal(root, result);

        return result;
    }

    private static void inorderTraversal(TreeNode root, List<Integer> list) {
        if (root == null) return;

        inorderTraversal(root.left, list);
        list.add(root.val);
        inorderTraversal(root.right, list);
    }

    //    Input: [1,null,2,3]
//            1
//            \
//            2
//            /
//            3
//
//    Output: [1,2,3]
//    root, left, right
    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        preorderTraversal(root, result);
        return result;
    }

    private static void preorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) return;

        list.add(node.val);
        preorderTraversal(node.left, list);
        preorderTraversal(node.right, list);
    }

    //    Input: [1,null,2,3]
//            1
//            \
//            2
//            /
//            3
//
//    Output: [3,2,1]
//  left, right, root
    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        postorderTraversal(root, result);
        return result;
    }

    private static void postorderTraversal(TreeNode node, List<Integer> list) {
        if (node == null) return;

        postorderTraversal(node.left, list);
        postorderTraversal(node.right, list);
        list.add(node.val);
    }

    //                  1
//                 / \
//                2   2
//               / \ / \
//              3  4 4  3
    public boolean isSymmetric(TreeNode root) {
        return root == null || isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null) return true;
        if (node1 == null || node2 == null || node1.val != node2.val) return false;
        return isSymmetric(node1.left, node2.right) && isSymmetric(node1.right, node2.left);
    }

    //    Input:     1         1
//              / \       / \
//             2   3     2   3
//
//            [1,2,3],   [1,2,3]
//
//    Output: true
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    //         3
//            / \
//           9  20
//             /  \
//            15   7
//          return its depth = 3
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0);
    }

    private int maxDepth(TreeNode node, int depth) {
        if (node == null) return depth;
        return Math.max(maxDepth(node.left, depth + 1), maxDepth(node.right, depth + 1));
    }

    public boolean isBalanced(TreeNode root) {
        return getBalancedHeight(root) != -1;
    }

    // return the height of subtree with root node
    // return -1 if this subtree is not balanced
    private int getBalancedHeight(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = getBalancedHeight(node.left);
        if (leftHeight == -1) return -1;

        int rightHeight = getBalancedHeight(node.right);
        if (rightHeight == -1) return -1;

        if (Math.abs(leftHeight - rightHeight) > 1) return -1;

        return Math.max(leftHeight, rightHeight) + 1;
    }

    //    Given the below binary tree and sum = 22,
//
//            5
//            / \
//            4   8
//            /   / \
//            11  13  4
//            /  \      \
//            7    2      1
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        // if root is leaf
        if (root.left == null && root.right == null) return sum == root.val;

        // check children
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    //    Given the below binary tree and sum = 22,
//
//            5
//            / \
//            4   8
//            /   / \
//            11  13  4
//            /  \    / \
//            7    2  5   1
//    Return:
//
//            [
//            [5,4,11,2],
//            [5,8,4,5]
//            ]
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();

        pathSum(root, sum, result, currentPath);

        return result;
    }

    private void pathSum(TreeNode node, int target, List<List<Integer>> result, List<Integer> currentPath) {
        if (node == null) return;

        // if node is leaf
        if (node.left == null && node.right == null) {
            // if the path sum equals target
            if (node.val == target) {
                currentPath.add(node.val);
                result.add(currentPath);
            }
            return;
        }

        // move to children
        currentPath.add(node.val);
        pathSum(node.left, target - node.val, result, new ArrayList<>(currentPath));
        pathSum(node.right, target - node.val, result, new ArrayList<>(currentPath));
    }

    // O(n^2)
    public int pathSum3(TreeNode root, int sum) {
        if (root == null) return 0;
        return pathSumFrom3(root, sum) + pathSum3(root.left, sum) + pathSum3(root.right, sum);
    }

    // this path include node
    private int pathSumFrom3(TreeNode node, int sum) {
        if (node == null) return 0;
        return (node.val == sum ? 1 : 0)
                + pathSumFrom3(node.left, sum - node.val) + pathSumFrom3(node.right, sum - node.val);
    }

    //    Input:
//
//               4
//            /   \
//            2     7
//            / \   / \
//           1   3 6   9
//    Output:
//
//              4
//            /   \
//            7     2
//            / \   / \
//           9   6 3   1
// swap the left and right child of root
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;

        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    //    Input:
//    Tree 1                     Tree 2
//              1                         2
//             / \                       / \
//            3   2                     1   3
//            /                           \   \
//            5                             4   7
//    Output:
//    Merged tree:
//             3
//            / \
//            4   5
//            / \   \
//            5   4   7
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;

        // merge t2 to t1
        if (t1 != null && t2 != null) {
            t1.val = t1.val + t2.val;
        } else if (t1 == null) {
            t1 = new TreeNode(t2.val);
        }

        t1.left = mergeTrees(t1.left, t2 != null ? t2.left: null);
        t1.right = mergeTrees(t1.right, t2 != null ? t2.right: null);

        return t1;
    }

    //        _______6______
//       /              \
//    ___2__          ___8__
//   /      \        /      \
//  0       4       7        9
//         /  \
//        3   5
//    Input: root = [6,2,8,0,4,7,9,null,null,3,5], p = 2, q = 4
//    Output: 2
//    Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself
//    according to the LCA definition.
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return (left == null) ? right : ((right == null) ? left : root);
    }

    public int minDepth(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> nodePerLevel = new LinkedList<>();
        nodePerLevel.add(root);
        int level = 1;

        while (!nodePerLevel.isEmpty()) {
            Queue<TreeNode> temp = new LinkedList<>();
            for(TreeNode node : nodePerLevel) {
                if (node.left == null && node.right == null) return level;
                if (node.left != null) temp.add(node.left);
                if (node.right != null) temp.add(node.right);
            }

            nodePerLevel = temp;
            level ++;
        }
        return level;
    }

    //    Input:
//
//              1
//            /   \
//            2     3
//             \
//              5
//
//    Output: ["1->2->5", "1->3"]
//
//    Explanation: All root-to-leaf paths are: 1->2->5, 1->3

    public List<String> binaryTreePaths(TreeNode root) {
        List<String> result = new ArrayList<>();

        binaryTreePathsHelper(root, new ArrayList<>(), result);
        return result;
    }

    private void binaryTreePathsHelper(TreeNode node, List<TreeNode> path, List<String> result) {
        if (node == null) return;
        path.add(node);

        // if node is a leaf
        if (node.left == null && node.right == null) {
            result.add(pathWriter(path));
        }

        // continue write
        binaryTreePathsHelper(node.left, new ArrayList<>(path), result);
        binaryTreePathsHelper(node.right, new ArrayList<>(path), result);
    }

    private String pathWriter(List<TreeNode> path) {
        StringBuilder output = new StringBuilder();
        String arrow = "->";

        for (int i = 0; i < path.size(); i ++) {
            output.append(path.get(i).val);
            output.append("->");
        }

        return output.toString().substring(0, output.length() - 2);
    }

    // 654. Maximum Binary Tree
//    Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
//
//    The root is the maximum number in the array.
//    The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
//    The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
//    Construct the maximum tree by the given array and output the root node of this tree.
//
//            Example 1:
//    Input: [3,2,1,6,0,5]
//    Output: return the tree root node representing the following tree:
//
//            6
//            /   \
//            3     5
//            \    /
//            2  0
//            \
//            1

    public static TreeNode constructMaximumBinaryTree(int[] nums) {
        // input validation
        if (nums == null || nums.length == 0) return null;
        return constructTreeHelper(nums, 0, nums.length);
    }

    // [start, end)
    private static TreeNode constructTreeHelper(int[] nums, int start, int end) {
        if (start >= end) return null;

        int maxValue = findMaxValue(nums, start, end);
        TreeNode root = new TreeNode(nums[maxValue]);
        TreeNode left = constructTreeHelper(nums, start, maxValue);
        TreeNode right = constructTreeHelper(nums, maxValue + 1, end);

        root.left = left;
        root.right = right;
        return root;
    }

    // return index of max value in [start, end)
    private static int findMaxValue(int[] nums, int start, int end) {
        int maxValue = Integer.MIN_VALUE;
        int index = start;

        for (int i = start; i < end; i ++) {
            if (nums[i] >= maxValue) {
                maxValue = nums[i];
                index = i;
            }
        }

        return index;
    }


//    Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.
//
//    Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.
//
//            Example:
//
//    You may serialize the following tree:
//
//            1
//            / \
//           2   3
//                / \
//               4   5
//
//    as "[1,2,3,null,null,4,5]"
//    Clarification: The above format is the same as how LeetCode serializes a binary tree. You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
//
//            Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder result = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.append("null,");
            } else {
                result.append(node.val + ",");
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        // remove last ,
        return result.toString().substring(0, result.length() - 1);
    }


    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.isEmpty()) return null;
        String[] values = data.split(",");

        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode root =  new TreeNode(Integer.valueOf(values[0])); // root is not null
        queue.add(root);

        for (int i = 1; i < values.length; i ++ ) {
            TreeNode parent = queue.poll();
            if (!values[i].equals("null")) {
                TreeNode left = new TreeNode(Integer.valueOf(values[i]));
                parent.left = left;
                queue.add(left);
            }
            if (!values[++i].equals("null")) {
                TreeNode right = new TreeNode(Integer.valueOf(values[i]));
                parent.right = right;
                queue.add(right);
            }
        }

        return root;
    }

    //    Given a complete binary tree, count the number of nodes.
//
//            Note:
//
//    Definition of a complete binary tree from Wikipedia:
//    In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive at the last level h.
//
//    Example:
//
//    Input:
//             1
//            / \
//            2   3
//           / \  /
//          4  5 6
//
//    Output: 6

    // binary search
    // compare the left height or subtree root.left and root.right
    // if they are same, then left tree is full, check right tree only
    private int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }
    public int countNodes(TreeNode root) {
        int nodes = 0, h = height(root);
        while (root != null) {
            if (height(root.right) == h - 1) { // root.left tree is full
                nodes += Math.pow(2, h); // total nodes for left tree is 2^h - 1, then + 1 (root)
                root = root.right; // check right tree
            } else { // root.left tree is not full
                nodes += Math.pow(2, h - 1); // 2 ^ h - 1 -> right tree is a complete tree
                root = root.left; // check left tree
            }
            h--;
        }
        return nodes;
    }
}
