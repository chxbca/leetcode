package com.company;

import java.util.Arrays;

/**
 * @author chxbca
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] ans = solution.imageSmoother(new int[][]{
                        {2, 3, 4},
                        {5, 6, 7},
                        {8, 9, 10},
                        {11, 12, 13},
                        {14, 15, 16}
                }
        );
        System.out.println(Arrays.deepToString(ans));
    }
}
