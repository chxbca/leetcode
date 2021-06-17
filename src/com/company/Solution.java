package com.company;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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


    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 给出两个 非空 的链表用来表示两个非负的整数。
     * 其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
     * <p>
     * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
     * <p>
     * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     * <p>
     * 示例：
     * <p>
     * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     * 输出：7 -> 0 -> 8
     * 原因：342 + 465 = 807
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/add-two-numbers
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode(0);
        ListNode middlePoint = head;
        while (l1 != null || l2 != null) {
            middlePoint = addTwoNumbers(l1, l2, middlePoint);
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return head;
    }

    private ListNode addTwoNumbers(ListNode l1, ListNode l2, ListNode middlePoint) {

        if (l1 == null) {
            middlePoint.val += l2.val;
        } else if (l2 == null) {
            middlePoint.val += l1.val;
        } else {
            middlePoint.val += l1.val + l2.val;
        }

        if (middlePoint.val / 10 == 1) {
            middlePoint.val = middlePoint.val % 10;
            middlePoint.next = new ListNode(1);
        } else {
            boolean l1Flag = l1 != null && l1.next != null;
            boolean l2Flag = l2 != null && l2.next != null;
            if (l1Flag || l2Flag) {
                middlePoint.next = new ListNode(0);
            }
        }
        return middlePoint.next;
    }

    /**
     * 测试用代码
     *
     * @param num
     * @return
     */
    public ListNode intToListNode(int num) {
        char[] numChars = String.valueOf(num).toCharArray();
        ListNode head = new ListNode(numChars[numChars.length - 1] - '0');
        ListNode middlePoint = head;
        for (int i = numChars.length - 2; i >= 0; i--) {
            middlePoint.next = new ListNode(numChars[i] - '0');
            middlePoint = middlePoint.next;
        }
        return head;
    }

    /**
     * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     * 示例 2:
     * <p>
     * 输入: "bbbbb"
     * 输出: 1
     * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
     * 示例 3:
     * <p>
     * 输入: "pwwkew"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 解题思路：活动窗口
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> charIndexMap = new HashMap<>(s.length());
        int len = s.length();
        int answer = 0;
        for (int start = 0, end = 0; end < len; end++) {
            char c = s.charAt(end);
            if (charIndexMap.containsKey(c)) {
                start = Math.max(start, charIndexMap.get(c));
            }
            answer = Math.max(answer, end - start + 1);
            charIndexMap.put(c, end + 1);
        }
        return answer;
    }

    /**
     * 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
     * <p>
     * 请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
     * <p>
     * 你可以假设 nums1 和 nums2 不会同时为空。
     * <p>
     * 示例 1:
     * <p>
     * nums1 = [1, 3]
     * nums2 = [2]
     * <p>
     * 则中位数是 2.0
     * 示例 2:
     * <p>
     * nums1 = [1, 2]
     * nums2 = [3, 4]
     * <p>
     * 则中位数是 (2 + 3)/2 = 2.5
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        boolean isEvenNum = length % 2 == 0;
        int arrLen = length / 2 + 1;
        int[] ints = new int[arrLen];
        for (int i = 0, j = 0, k = 0; i < arrLen; i++) {
            if (j == nums1.length) {
                ints[i] = nums2[k++];
            } else if (k == nums2.length) {
                ints[i] = nums1[j++];
            } else {
                ints[i] = nums1[j] < nums2[k] ? nums1[j++] : nums2[k++];
            }
        }
        return isEvenNum ? (ints[arrLen - 1] + ints[arrLen - 2]) / 2.0 : ints[arrLen - 1];
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

    /**
     * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 121
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: -121
     * 输出: false
     * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
     * 示例 3:
     * <p>
     * 输入: 10
     * 输出: false
     * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-number
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param x
     * @return
     */
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        int ans = 0, num = x;
        while (x != 0) {
            ans = (ans * 10) + (x % 10);
            x /= 10;
        }
        return ans == num;
    }

    /**
     * 请判断一个链表是否为回文链表。
     * <p>
     * 示例 1:
     * <p>
     * 输入: 1->2
     * 输出: false
     * 示例 2:
     * <p>
     * 输入: 1->2->2->1
     * 输出: true
     * 进阶：
     * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/palindrome-linked-list
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     * <p>
     * 1 2 3 4 5
     * 1 2 3 4
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) {
            return true;
        }
        //快慢指针找到链表的中点
        ListNode fast = head.next.next;
        ListNode slow = head.next;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }

        //翻转链表前半部分
        ListNode pre = null;
        while (head != slow) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        //如果是奇数个节点，去掉后半部分的第一个节点。

        if (fast != null) {
            slow = slow.next;
        }
        //回文校验
        while (pre != null) {
            if (pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }

        return true;

    }

    /**
     * 在英语中，我们有一个叫做 词根(root)的概念。
     * 它可以跟着其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。
     * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
     * <p>
     * 现在，给定一个由许多词根组成的词典和一个句子。你需要将句子中的所有继承词用词根替换掉。
     * 如果继承词有许多可以形成它的词根，则用最短的词根替换它。
     * <p>
     * 你需要输出替换之后的句子。
     * <p>
     * 示例 1:
     * <p>
     * 输入: dict(词典) = ["cat", "bat", "rat"]
     * sentence(句子) = "the cattle was rattled by the battery"
     * 输出: "the cat was rat by the bat"
     * 注:
     * <p>
     * 输入只包含小写字母。
     * 1 <= 字典单词数 <=1000
     * 1 <=  句中词语数 <= 1000
     * 1 <= 词根长度 <= 100
     * 1 <= 句中词语长度 <= 1000
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/replace-words
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param dict
     * @param sentence
     * @return
     */
    public String replaceWords(List<String> dict, String sentence) {
        String[] split = sentence.split(" ");
        for (int i = 0; i < split.length; i++) {
            for (String s : dict) {
                if (split[i].indexOf(s) == 0) {
                    split[i] = s;
                    break;
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        for (String s : split) {
            ans.append(s).append(" ");
        }
        return ans.substring(0, ans.length() - 1);
    }

    /**
     * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
     * <p>
     * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
     * <p>
     * 注意：给定 n 是一个正整数。
     * <p>
     * 示例 1：
     * <p>
     * 输入： 2
     * 输出： 2
     * 解释： 有两种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶
     * 2.  2 阶
     * 示例 2：
     * <p>
     * 输入： 3
     * 输出： 3
     * 解释： 有三种方法可以爬到楼顶。
     * 1.  1 阶 + 1 阶 + 1 阶
     * 2.  1 阶 + 2 阶
     * 3.  2 阶 + 1 阶
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/climbing-stairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] arr = new int[n + 1];
        arr[0] = 0;
        arr[1] = 1;
        for (int i = 2; i <= n; i++) {
            arr[i] = arr[i - 1] + arr[i - 1];
        }
        return arr[n];
    }


    /**
     * 数组的每个索引做为一个阶梯，第 i个阶梯对应着一个非负数的体力花费值 cost[i](索引从0开始)。
     * <p>
     * 每当你爬上一个阶梯你都要花费对应的体力花费值，然后你可以选择继续爬一个阶梯或者爬两个阶梯。
     * <p>
     * 您需要找到达到楼层顶部的最低花费。在开始时，你可以选择从索引为 0 或 1 的元素作为初始阶梯。
     * <p>
     * 示例 1:
     * <p>
     * 输入: cost = [10, 15, 20]
     * 输出: 15
     * 解释: 最低花费是从cost[1]开始，然后走两步即可到阶梯顶，一共花费15。
     *  示例 2:
     * <p>
     * 输入: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
     * 输出: 6
     * 解释: 最低花费方式是从cost[0]开始，逐个经过那些1，跳过cost[3]，一共花费6。
     * 注意：
     * <p>
     * cost 的长度将会在 [2, 1000]。
     * 每一个 cost[i] 将会是一个Integer类型，范围为 [0, 999]。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/min-cost-climbing-stairs
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param cost
     * @return
     */
    public int minCostClimbingStairs(int[] cost) {
        int i = 2;
        while (i < cost.length) {
            cost[i] += Math.min(cost[i - 1], cost[i - 2]);
            i++;
        }
        return Math.min(cost[i - 1], cost[i - 2]);
    }

    /**
     * 包含整数的二维矩阵 M 表示一个图片的灰度。
     * 你需要设计一个平滑器来让每一个单元的灰度成为平均灰度 (向下舍入)
     * 平均灰度的计算是周围的8个单元和它本身的值求平均，如果周围的单元格不足八个，则尽可能多的利用它们。
     * <p>
     * 示例 1:
     * <p>
     * 输入:
     * [[1,1,1],
     * [1,0,1],
     * [1,1,1]]
     * 输出:
     * [[0, 0, 0],
     * [0, 0, 0],
     * [0, 0, 0]]
     * 解释:
     * 对于点 (0,0), (0,2), (2,0), (2,2): 平均(3/4) = 平均(0.75) = 0
     * 对于点 (0,1), (1,0), (1,2), (2,1): 平均(5/6) = 平均(0.83333333) = 0
     * 对于点 (1,1): 平均(8/9) = 平均(0.88888889) = 0
     * 注意:
     * <p>
     * 给定矩阵中的整数范围为 [0, 255]。
     * 矩阵的长和宽的范围均为 [1, 150]。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/image-smoother
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param M
     * @return
     */
    public int[][] imageSmoother(int[][] M) {
        int[][] ans = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                ans[i][j] = calculateAvg(M, i, j);
            }
        }
        return ans;
    }

    private int calculateAvg(int[][] M, int i, int j) {
        int count = 0, num = 0;
        int width = M.length, high = M[0].length;
        if (i - 1 >= 0 && j - 1 >= 0) {
            num += M[i - 1][j - 1];
            count++;
        }
        if (i - 1 >= 0 && j >= 0) {
            num += M[i - 1][j];
            count++;
        }
        if (i - 1 >= 0 && j + 1 < high) {
            num += M[i - 1][j + 1];
            count++;
        }
        if (i >= 0 && j - 1 >= 0) {
            num += M[i][j - 1];
            count++;
        }
        if (i >= 0 && j >= 0) {
            num += M[i][j];
            count++;
        }
        if (i >= 0 && j + 1 < high) {
            num += M[i][j + 1];
            count++;
        }
        if (i + 1 < width && j - 1 >= 0) {
            num += M[i + 1][j - 1];
            count++;
        }
        if (i + 1 < width && j >= 0) {
            num += M[i + 1][j];
            count++;
        }
        if (i + 1 < width && j + 1 < high) {
            num += M[i + 1][j + 1];
            count++;
        }
        return num / count;
    }


    /**
     * 给定长度为 2n 的数组, 你的任务是将这些数分成 n 对
     * 例如 (a1, b1), (a2, b2), ..., (an, bn) ，使得从1 到 n 的 min(ai, bi) 总和最大。
     * <p>
     * 示例 1:
     * <p>
     * 输入: [1,4,3,2]
     * <p>
     * 输出: 4
     * 解释: n 等于 2, 最大总和为 4 = min(1, 2) + min(3, 4).
     * 提示:
     * <p>
     * n 是正整数,范围在 [1, 10000].
     * 数组中的元素范围在 [-10000, 10000].
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/array-partition-i
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        return IntStream.iterate(0, i -> i < nums.length, i -> i + 2).map(i -> nums[i]).sum();
    }

    /**
     * 给定一个长度为 n 的整数数组 A 。
     * <p>
     * 假设 Bk 是数组 A 顺时针旋转 k 个位置后的数组，我们定义 A 的“旋转函数” F 为：
     * <p>
     * F(k) = 0 * Bk[0] + 1 * Bk[1] + ... + (n-1) * Bk[n-1]。
     * <p>
     * 计算F(0), F(1), ..., F(n-1)中的最大值。
     * <p>
     * 注意:
     * 可以认为 n 的值小于 105。
     * <p>
     * 示例:
     * <p>
     * A = [4, 3, 2, 6]
     * <p>
     * F(0) = (0 * 4) + (1 * 3) + (2 * 2) + (3 * 6) = 0 + 3 + 4 + 18 = 25
     * F(1) = (0 * 6) + (1 * 4) + (2 * 3) + (3 * 2) = 0 + 4 + 6 + 6 = 16
     * F(2) = (0 * 2) + (1 * 6) + (2 * 4) + (3 * 3) = 0 + 6 + 8 + 9 = 23
     * F(3) = (0 * 3) + (1 * 2) + (2 * 6) + (3 * 4) = 0 + 2 + 12 + 12 = 26
     * <p>
     * 所以 F(0), F(1), F(2), F(3) 中的最大值是 F(3) = 26 。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/rotate-function
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param A
     * @return
     */
    public int maxRotateFunction(int[] A) {
        int max = 0;
        int sum = 0;
        for (int i = 0; i < A.length; i++) {
            max += i * A[i];
            sum += A[i];
        }
        for (int length = A.length, num = max, i = length - 1; i >= 1; i--) {
            num = num + sum - A[i] * length;
            max = Math.max(max, num);
        }
        return max;
    }

    /**
     * 给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output
     * 其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
     * <p>
     * 示例:
     * <p>
     * 输入: [1,2,3,4]
     * 输出: [24,12,8,6]
     * 说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
     * <p>
     * 进阶：
     * 你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/product-of-array-except-self
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int length = nums.length;
        int left = 1, right = 1;
        int[] res = new int[length];
        Arrays.fill(res, 1);
        for (int i = 0, maxIndex = length - 1; i <= maxIndex; ++i) {
            res[i] *= left;
            left *= nums[i];
            res[maxIndex - i] *= right;
            right *= nums[maxIndex - i];
        }
        return res;
    }

    /**
     * 数轴上放置了一些筹码，每个筹码的位置存在数组 chips 当中。
     * <p>
     * 你可以对 任何筹码 执行下面两种操作之一（不限操作次数，0 次也可以）：
     * <p>
     * 将第 i 个筹码向左或者右移动 2 个单位，代价为 0。
     * 将第 i 个筹码向左或者右移动 1 个单位，代价为 1。
     * 最开始的时候，同一位置上也可能放着两个或者更多的筹码。
     * <p>
     * 返回将所有筹码移动到同一位置（任意位置）上所需要的最小代价。
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/play-with-chips
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param chips
     * @return
     */
    public int minCostToMoveChips(int[] chips) {
        int odd = 0, even = 0;
        for (int chip : chips) {
            if (chip % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }
        return Math.min(odd, even);
    }

    /**
     * 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
     * <p>
     * 比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
     * <p>
     * L   C   I   R
     * E T O E S I I G
     * E   D   H   N
     * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。
     * <p>
     * 请你实现这个将字符串进行指定行数变换的函数：
     * <p>
     * string convert(string s, int numRows);
     * 示例 1:
     * <p>
     * 输入: s = "LEETCODEISHIRING", numRows = 3
     * 输出: "LCIRETOESIIGEDHN"
     * 示例 2:
     * <p>
     * 输入: s = "LEETCODEISHIRING", numRows = 4
     * 输出: "LDREOEIIECIHNTSG"
     * 解释:
     * <p>
     * L     D     R     0     6       12
     * E   O E   I I     1   5 7    11 13
     * E C   I H   N     2 4   8 10    14
     * T     S     G     3     9       15
     * <p>
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/zigzag-conversion
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }
        int step = (numRows - 1) << 1;
        int len = s.length();
        char[] ans = new char[len];
        char[] chars = s.toCharArray();
        int index = -1;
        for (int i = 0; i < numRows; i++) {
            for (int j = i; j < len; j += step) {
                ans[++index] = chars[j];
                if (i != 0 && i != numRows - 1 && step - (i << 1) + j < len) {
                    ans[++index] = chars[step - (i << 1) + j];
                }
            }
        }
        return new String(ans);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     * <p>
     * https://leetcode-cn.com/problems/binary-tree-paths/
     */
    public List<String> binaryTreePaths(TreeNode root) {
        Stack<Integer> stack = new Stack<>();
        List<String> result = new ArrayList<>();
        if (root != null) {
            scanningNode(root, stack, result);
        }
        return result;
    }

    private void scanningNode(TreeNode node, Stack<Integer> stack, List<String> result) {
        stack.push(node.val);
        if (node.left == null && node.right == null) {
            Object[] objects = stack.toArray();
            StringBuilder rs = new StringBuilder(objects[0].toString());
            for (int i = 1; i < objects.length; i++) {
                rs.append("->").append(objects[i]);
            }
            result.add(rs.toString());
        } else {
            if (node.left != null) {
                scanningNode(node.left, stack, result);
            }
            if (node.right != null) {
                scanningNode(node.right, stack, result);
            }
        }
        stack.pop();
    }

    /**
     * #60
     * 第k个排列
     * https://leetcode-cn.com/problems/permutation-sequence/
     */
    public String getPermutation(int n, int k) {
        char[] arr = new char[n];
        for (int i = 0; i < n; i++) {
            arr[i] = (char) ('0' + i + 1);
        }
        int batch = 1;
        for (int i1 = 1; i1 < n; i1++) {
            batch = batch * i1;
        }
        for (int i = 0; i < n - 1; i++) {
            int index = (k - 1) / batch;
            int moveIndex = index + i;
            move(arr, i, moveIndex);
            int nextK = k % batch;
            k = (nextK == 0 ? batch : nextK);
            batch = batch / (n - 1 - i);
        }
        return new String(arr);
    }

    private static void move(char[] arr, int i, int j) {
        char n = arr[j];
        System.arraycopy(arr, i, arr, i + 1, j - i);
        arr[i] = n;
    }

    /**
     * 107. 二叉树的层次遍历 II
     * https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        addByDeep(map, 0, root);
        List<List<Integer>> result = new ArrayList<>(map.values());
        Collections.reverse(result);
        return result;
    }

    private void addByDeep(Map<Integer, List<Integer>> map, int deep, TreeNode root) {
        if (root == null) {
            return;
        }
        map.computeIfAbsent(deep, k -> new LinkedList<>()).add(root.val);
        addByDeep(map, deep + 1, root.left);
        addByDeep(map, deep + 1, root.right);
    }

    /**
     * 347. 前 K 个高频元素
     * https://leetcode-cn.com/problems/top-k-frequent-elements/submissions/
     *
     * @param k
     * @param nums
     */
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numberCountMap = new HashMap<>();
        for (int num : nums) {
            Integer integer = num;
            numberCountMap.merge(integer, 1, Integer::sum);
        }
        List<Integer> toSort = new ArrayList<>(numberCountMap.values());
        Collections.sort(toSort);
        Set<Integer> collect = new HashSet<>();
        int size = toSort.size() - k;
        for (int i = toSort.size() - 1; i >= size; i--) {
            collect.add(toSort.get(i));
        }
        int[] rs = new int[k];
        int count = 0;
        for (Map.Entry<Integer, Integer> entry : numberCountMap.entrySet()) {
            if (collect.contains(entry.getValue())) {
                rs[count++] = entry.getKey();
            }
        }
        return rs;
    }

    /**
     * https://leetcode-cn.com/problems/combinations/
     *
     * @param n
     * @param k
     * @return
     */
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        int length = n - k + 1;
        for (int i = 1; i <= length; i++) {
            List<Integer> list = new ArrayList<>();
            deep(list, i, k - 1, n, result);
        }
        return result;
    }

    private void deep(List<Integer> list, int startNum, int deep, int endNum, List<List<Integer>> result) {
        list.add(startNum);
        if (deep == 0) {
            result.add(list);
            return;
        }
        for (int i = startNum + 1; i <= endNum; i++) {
            deep(new ArrayList<>(list), i, deep - 1, endNum, result);
        }
    }

    /**
     * https://leetcode-cn.com/problems/combination-sum/
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        Set<List<Integer>> result = new HashSet<>();
        for (int candidate : candidates) {
            List<Integer> list = new ArrayList<>();
            list.add(candidate);
            sumNum(candidates, target - candidate, list, result);
        }
        return new ArrayList<>(result);
    }

    private void sumNum(int[] candidates, int target, List<Integer> list, Set<List<Integer>> result) {
        if (target == 0) {
            Collections.sort(list);
            result.add(list);
            return;
        }
        if (target < candidates[0]) {
            return;
        }
        for (int candidate : candidates) {
            List<Integer> newList = new ArrayList<>(list);
            newList.add(candidate);
            sumNum(candidates, target - candidate, newList, result);
        }
    }

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<Integer> arrayList = Arrays.stream(candidates).boxed().sorted().collect(Collectors.toList());
        Set<List<Integer>> result = new HashSet<>();
        for (int candidate : candidates) {
            List<Integer> list = new ArrayList<>();
            List<Integer> candidatesList = new ArrayList<>(arrayList);
            candidatesList.remove(Integer.valueOf(candidate));
            list.add(candidate);
            sumNum2(candidatesList, target - candidate, list, result);
        }
        return new ArrayList<>(result);
    }

    private void sumNum2(List<Integer> candidates, int target, List<Integer> list, Set<List<Integer>> result) {
        if (target == 0) {
            Collections.sort(list);
            result.add(list);
            return;
        }
        if (candidates.isEmpty() || target < candidates.get(0)) {
            return;
        }
        for (int candidate : candidates) {
            List<Integer> candidatesList = new ArrayList<>(candidates);
            candidatesList.remove(Integer.valueOf(candidate));
            List<Integer> newList = new ArrayList<>(list);
            newList.add(candidate);
            sumNum2(candidatesList, target - candidate, newList, result);
        }
    }

    public List<List<Integer>> combinationSum3(int k, int target) {
        Set<List<Integer>> lists = new HashSet<>();
        int length = Math.min(target - k, 9);
        for (int i = 1; i <= length; i++) {
            List<Integer> list = new ArrayList<>();
            list.add(i);
            sumNum3(list, k - 1, target - i, lists);
        }
        return new ArrayList<>(lists);
    }

    private void sumNum3(List<Integer> list, int deep, int target, Set<List<Integer>> result) {
        if (deep == 0) {
            if (target == 0) {
                Collections.sort(list);
                result.add(list);
            }
            return;
        }
        int length = Math.min(target - deep + 1, 9);
        for (int i = 1; i <= length; i++) {
            if (list.contains(i)) {
                continue;
            }
            List<Integer> newList = new ArrayList<>(list);
            newList.add(i);
            sumNum3(newList, deep - 1, target - i, result);
        }
    }

    /**
     * https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        addByDeep(map, 0, root);
        List<List<Integer>> result = new ArrayList<>(map.values());
        return result.stream().mapToDouble(integers -> integers.stream().
                mapToDouble(Integer::doubleValue).average().orElse(0D)).boxed().collect(Collectors.toList());
    }

    /**
     * https://leetcode-cn.com/problems/word-search/
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            char[] charY = board[i];
            for (int j = 0; j < charY.length; j++) {
                if (charY[j] == chars[0]) {
                    boolean[][] isUsed = new boolean[board.length][charY.length];
                    isUsed[i][j] = true;
                    if (exist(board, chars, 1, i, j, isUsed)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, char[] word, int wordIndex, int i, int j, boolean[][] isUsed) {
        if (wordIndex == word.length) {
            return true;
        }
        boolean up = i - 1 >= 0;
        if (up && !isUsed[i - 1][j] && board[i - 1][j] == word[wordIndex]) {
            boolean[][] clone = arrCopy(isUsed);
            clone[i - 1][j] = true;
            if (exist(board, word, wordIndex + 1, i - 1, j, clone)) {
                return true;
            }
        }
        boolean down = i + 1 < board.length;
        if (down && !isUsed[i + 1][j] && board[i + 1][j] == word[wordIndex]) {
            boolean[][] clone = arrCopy(isUsed);
            clone[i + 1][j] = true;
            if (exist(board, word, wordIndex + 1, i + 1, j, clone)) {
                return true;
            }
        }
        boolean left = j - 1 >= 0;
        if (left && !isUsed[i][j - 1] && board[i][j - 1] == word[wordIndex]) {
            boolean[][] clone = arrCopy(isUsed);
            clone[i][j - 1] = true;
            if (exist(board, word, wordIndex + 1, i, j - 1, clone)) {
                return true;
            }
        }
        boolean right = j + 1 < board[i].length;
        if (right && !isUsed[i][j + 1] && board[i][j + 1] == word[wordIndex]) {
            boolean[][] clone = arrCopy(isUsed);
            clone[i][j + 1] = true;
            return exist(board, word, wordIndex + 1, i, j + 1, clone);
        }
        return false;
    }

    private boolean[][] arrCopy(boolean[][] source) {
        int length = source.length;
        boolean[][] result = new boolean[length][];
        for (int i = 0; i < length; i++) {
            result[i] = source[i].clone();
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-inorder-traversal/submissions/
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new LinkedList<>();
        LinkedList<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                TreeNode pop = stack.pop();
                result.add(pop.val);
                root = pop.right;
            }
        }
        return result;
    }

    /**
     * https://leetcode-cn.com/problems/top-k-frequent-words/
     *
     * @param words
     * @param k
     * @return
     */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> wordMap = new TreeMap<>();
        for (String word : words) {
            Integer count = wordMap.computeIfAbsent(word, k1 -> 0);
            wordMap.put(word, count + 1);
        }
        return wordMap.entrySet().stream()
                .sorted((o1, o2) -> Integer.compare(o2.getValue(), o1.getValue()))
                .limit(k)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    /**
     * https://leetcode-cn.com/problems/chalkboard-xor-game/
     *
     * @param nums
     * @return
     */
    public boolean xorGame(int[] nums) {
        return Arrays.stream(nums).reduce(0, (a, b) -> a ^ b) == 0 || (nums.length & 1) != 1;
    }

    /**
     * https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/
     *
     * @param s
     * @return
     */
    public String reverseParentheses(String s) {
        StringBuilder stringBuilder = new StringBuilder(s);
        LinkedList<Integer> indexStack = new LinkedList<>();
        for (int i = 0; i < stringBuilder.length(); i++) {
            char c = stringBuilder.charAt(i);
            if (c == '(') {
                indexStack.addLast(i);
            } else if (c == ')') {
                int left = indexStack.removeLast();
                int right = i;
                reverse(stringBuilder, left, right);
            }
        }
        return stringBuilder.toString().replace("(", "").replace(")", "");
    }

    private void reverse(StringBuilder stringBuilder, int left, int right) {
        while (right >= left) {
            char leftChar = stringBuilder.charAt(left);
            char rightChar = stringBuilder.charAt(right);
            stringBuilder.setCharAt(left++, rightChar);
            stringBuilder.setCharAt(right--, leftChar);
        }
    }

    /**
     * https://leetcode-cn.com/problems/remove-linked-list-elements/
     *
     * @param head
     * @param val
     * @return
     */
    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
    }

    /**
     * https://leetcode-cn.com/problems/peak-index-in-a-mountain-array/
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int left = 0, right = arr.length;
        while (true) {
            int index = (left + right) / 2;
            if (arr[index] > arr[index - 1] && arr[index] > arr[index + 1]) {
                return index;
            }
            if (arr[index] < arr[index - 1]) {
                right = index;
            }
            if (arr[index] < arr[index + 1]) {
                left = index;
            }
        }
    }

    /**
     * https://leetcode-cn.com/problems/stone-game/
     *
     * @param piles
     * @return
     */
    public boolean stoneGame(int[] piles) {
        return true;
    }

    /**
     * https://leetcode-cn.com/problems/relative-sort-array/
     *
     * @param arr1
     * @param arr2
     * @return
     */
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> numberMap = new HashMap<>();
        for (int i : arr2) {
            numberMap.put(i, 0);
        }
        LinkedList<Integer> resultNum = new LinkedList<>();
        for (int i : arr1) {
            Integer count = numberMap.get(i);
            if (count != null) {
                numberMap.put(i, count + 1);
            } else {
                resultNum.add(i);
            }
        }
        Collections.sort(resultNum);
        for (int i = arr2.length - 1; i >= 0; i--) {
            int num = arr2[i];
            int count = numberMap.get(num);
            for (int j = 0; j < count; j++) {
                resultNum.addFirst(num);
            }
        }
        int index = 0;
        int[] resultArray = new int[arr1.length];
        for (Integer num : resultNum) {
            resultArray[index++] = num;
        }
        return resultArray;
    }

    /**
     * https://leetcode-cn.com/problems/valid-number/
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        try {
            if (s.contains("Infinity")) {
                return false;
            }
            s = s.replace('f', 'z')
                    .replace('F', 'z')
                    .replace('d', 'z')
                    .replace('D', 'z');
            Double.parseDouble(s);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
