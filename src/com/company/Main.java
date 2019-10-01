package com.company;

import java.util.Arrays;

/**
 * @author chxbca
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = {1, 2, 3, 4};
        int[] ans = solution.productExceptSelf(a);
        System.out.println(Arrays.toString(ans));
    }
}
