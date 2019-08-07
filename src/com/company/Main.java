package com.company;

/**
 * @author chxbca
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Solution.ListNode listNode_1 = solution.intToListNode(243);
        Solution.ListNode listNode_2 = solution.intToListNode(564);
        Solution.ListNode listNode = solution.addTwoNumbers(listNode_1, listNode_2);
    }
}
