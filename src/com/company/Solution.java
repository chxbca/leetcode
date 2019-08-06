package com.company;

import java.util.*;

class Solution {
    /**
     * 输入："Test1ng-Leet=code-Q!"
     * 输出："Qedo1ct-eeLg=ntse-T!"
     */
    public String reverseOnlyLetters(String s) {
        char[] split = s.toCharArray();
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

    /**
     * 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的 两个 整数。
     * <p>
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>(2048);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[]{map.get(target - nums[i]), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    /**
     * https://leetcode-cn.com/problems/trapping-rain-water/submissions/
     * <p>
     * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
     * <p>
     * 输入: [0,1,0,2,1,0,1,3,2,1,2,1]
     * 输出: 6
     *
     * @param height
     * @return sum
     */
    public int trap(int[] height) {
        int sum = 0, lastIndex = 0;
        for (int i = 1; i < height.length; i++) {
            if (height[i] >= height[lastIndex]) {
                sum += trap(height, lastIndex, i);
                lastIndex = i;
            }
        }
        int max = lastIndex;
        lastIndex = height.length - 1;
        for (int i = lastIndex; i >= max; i--) {
            if (height[i] > height[lastIndex]) {
                sum += trap(height, i, lastIndex);
                lastIndex = i;
            }
        }
        return sum;
    }

    private int trap(int[] height, int start, int end) {
        int max = Math.max(height[start], height[end]);
        int second = Math.min(height[start], height[end]);
        int length = end - start + 1;
        int sum = second * length + max - second;
        for (int i = start; i <= end; i++) {
            sum -= height[i];
        }
        return sum;
    }

    /**
     * https://leetcode-cn.com/problems/3sum/
     * <p>
     * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
     * <p>
     * 注意：答案中不可以包含重复的三元组。
     * <p>
     * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]
     * <p>
     * 满足要求的三元组集合为：
     * [
     * [-1, 0, 1],
     * [-1, -1, 2]
     * ]
     */
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> answer = new ArrayList<>();
        int length = nums.length;
        Arrays.sort(nums);
        int arrSum = 3;
        if (length < arrSum) {
            return answer;
        }
        for (int i = 0; i < length; i++) {
            if (nums[i] > 0) {
                return answer;
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = length - 1;
            while (right > left) {
                int sum = nums[left] + nums[right] + nums[i];
                if (sum == 0) {
                    answer.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left + 1 < length && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right - 1 > i && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum > 0) {
                    right--;
                } else {
                    left++;
                }
            }

        }
        return answer;
    }
}
