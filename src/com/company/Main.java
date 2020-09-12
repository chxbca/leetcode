package com.company;

import java.util.List;

/**
 * @author chxbca
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        List<List<Integer>> lists = solution.combinationSum3(3, 7);
        for (List<Integer> list : lists) {
            System.out.println(list);
        }
    }
}
