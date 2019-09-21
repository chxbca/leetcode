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
}
