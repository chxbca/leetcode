package com.company;

import java.util.Objects;

/**
 * @author chxbca
 */
public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String convert = solution.convert("LEETCODEISHIRING", 4);
        System.out.println(Objects.equals(convert, "LDREOEIIECIHNTSG"));
    }
}
