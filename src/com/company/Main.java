package com.company;

/**
 * @author chxbca
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.threeSum(new int[]{-2, 0, 1, 1, 2}));
        System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
        System.out.println(solution.threeSum(new int[]{0, 0, 0, 0}));
        System.out.println(solution.threeSum(new int[]{1, -1, -1, 0}));
        System.out.println(solution.threeSum(new int[]{-2, 0, 0, 2, 2}));
        System.out.println(solution.threeSum(new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0}));
    }
}
