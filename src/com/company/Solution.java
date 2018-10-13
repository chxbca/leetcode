package com.company;

class Solution {
    /**
     * 输入："Test1ng-Leet=code-Q!"
     * 输出："Qedo1ct-eeLg=ntse-T!"
     */
    public String reverseOnlyLetters(String S) {
        char[] split = S.toCharArray();
        int i = 0, j = split.length - 1;
        while (i <= j) {
            if (isLetter(split[i])) {
                if (isLetter(split[j])) {
                    char temp = split[i];
                    split[i] = split[j];
                    split[j] = temp;
                    i++;
                    j--;
                } else {
                    j--;
                }
            } else {
                i++;
            }
        }
        return new String(split);
    }

    private boolean isLetter(char s) {
        return (s >= 'a' && s <= 'z') || (s >= 'A' && s <= 'Z');
    }
}
