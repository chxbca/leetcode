package com.company;

import java.util.Arrays;

/**
 * @author chxbca
 */
public class Main {
    public static void main(String[] args) {
        String s = "a-bC-dEf=ghIj";
        Solution solution = new Solution();
        int[] arrays = solution.twoSum(new int[]{2, 5, 5, 11}, 10);
        System.out.println(Arrays.toString(arrays));
    }
}
