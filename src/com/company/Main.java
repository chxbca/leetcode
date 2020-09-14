package com.company;

/**
 * @author chxbca
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
//        char[][] chars = new char[][]{
//                {'A', 'B', 'C', 'E'},
//                {'S', 'F', 'C', 'S'},
//                {'A', 'D', 'E', 'E'}
//        };

        char[][] chars =
                {
                        {'A', 'B', 'C', 'E'},
                        {'S', 'F', 'E', 'S'},
                        {'A', 'D', 'E', 'E'}
                };
        String word = "ABCESEEEFS";

        System.out.println(solution.exist(chars, word));
    }
}
