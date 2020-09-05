package com.company;

/**
 * @author chxbca
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int n = 9, k = 16;
        for (int i = 1; i <= k; i++) {
            String permutation = solution.getPermutation(n, i);
            System.out.println(permutation);
        }
    }



}
