package com.company;

/**
 * @author chxbca
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        Object ans = solution.minCostClimbingStairs(new int[]{
                0, 0, 1, 1
        });
        System.out.println(ans);
    }
}
