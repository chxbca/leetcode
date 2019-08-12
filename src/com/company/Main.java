package com.company;

/**
 * @author chxbca
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Object ans = solution.findMedianSortedArrays(new int[]{1,3,5},new int[]{2});
        System.out.println(ans);
    }
}
