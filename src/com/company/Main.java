package com.company;

/**
 * @author chxbca
 */
public class Main {
    public static void main(String[] args) {
        Solution.TreeNode node = new Solution.TreeNode(1);
        node.left = new Solution.TreeNode(2);
        node.right = new Solution.TreeNode(3);
        node.left.right = new Solution.TreeNode(5);
        Solution solution = new Solution();
        solution.inorderTraversal(node).forEach(System.out::println);
    }
}
