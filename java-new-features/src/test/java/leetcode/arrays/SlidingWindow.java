package leetcode.arrays;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SlidingWindow {

    /**
     * Example 1: Given an array of positive integers nums and an integer k,
     * find the length of the longest subarray whose sum is less than or equal to k.
     */
    @Test
    void longestSubarrayLessOrEqualTaskRunner() {
        int[] positiveInts = Stream.generate(() -> RandomUtils.nextInt(0, 10))
                .limit(10)
                .mapToInt(value -> value)
                .toArray();

        int[] ints = {3, 1, 2, 7, 4, 2, 1, 1, 5};
        longestSubarrayLessOrEqual(ints, 8);

    }

    int longestSubarrayLessOrEqual(int[] nums, int k) {
        int left = 0, right = 0;
        int currentSum = 0;
        int longestSubarrayLength = 0;

        while (right < nums.length) {
            currentSum += nums[right];
            if (currentSum > k) {
                currentSum -= nums[left];
                left++;
            }

            longestSubarrayLength = Math.max(longestSubarrayLength, right - left + 1);
            right++;
        }

        return longestSubarrayLength;
    }

    /**
     * Example 2: You are given a binary substring s (a string containing only "0" and "1").
     * An operation involves flipping a "0" into a "1".
     * What is the length of the longest substring containing only "1" after performing at most one operation?
     * <p>
     * For example, given s = "1101100111", the answer is 5. If you perform the operation at index 2, the string becomes 1111100111.
     */
    @Test
    void longestSubstringContainingOnlyAfterFlippingTaskRunner() {
        String input = "1101100111";
        assertEquals(5, longestSubstringContainingOnlyAfterFlipping(input.toCharArray()));
    }

    private int longestSubstringContainingOnlyAfterFlipping(char[] array) {
        int left = 0, right = 0;
        int current = 0;
        int longestSubarrayLength = 0;

        while (right < array.length) {
            if (array[right] == '0') {
                current++;
            }

            while (current > 1) {
                if (array[left] == '0') {
                    current--;
                }
                left++;
            }

            longestSubarrayLength = Math.max(longestSubarrayLength, right - left + 1);
            right++;
        }

        return longestSubarrayLength;
    }

    /**
     * Given an array of integers nums and an integer k, return the number of contiguous subarrays
     * where the product of all the elements in the subarray is strictly less than k.
     * <p>
     * Explanation: The 8 subarrays that have product less than 100 are:
     * [10], [5], [2], [6], [10, 5], [5, 2], [2, 6], [5, 2, 6]
     * Note that [10, 5, 2] is not included as the product of 100 is not strictly less than k.
     */
    @Test
    void numSubarrayProductLessThanKTaskRunner() {
        int[] inputArray = new int[]{10, 5, 2, 6};
        int k = 100;
        int expected = 8;
        assertEquals(expected, numSubarrayProductLessThanK(inputArray, k));
    }

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 0) {
            return 0;
        }

        int left = 0, right = 0;
        int current = 1;
        int numSubarrayProductLessThanK = 0;

        while (right < nums.length) {
            current *= nums[right];

            while (left <= right && current >= k) {
                current /= nums[left];
                left++;
            }

            numSubarrayProductLessThanK += right - left + 1;
            right++;
        }

        return numSubarrayProductLessThanK;
    }

    /**
     * Example 4: Given an integer array nums and an integer k,
     * find the sum of the subarray with the largest sum whose length is k.
     */
    @Test
    void findLargestSumSubarrayWithLengthTaskRunner() {
        int[] input = new int[]{3, -1, 4, 12, -8, 5, 6};
        int k = 4;
        int expectedSum = 18;
        assertEquals(expectedSum, findLargestSumSubarrayWithLength(input, k));
    }

    int findLargestSumSubarrayWithLength(int[] nums, int k) {
        int curr = 0;
        for (int i = 0; i < k; i++) {
            curr += nums[i];
        }

        int largestSumSubarray = curr;
        for (int i = k; i < nums.length; i++) {
            curr += nums[i] - nums[i - k];
            largestSumSubarray = Math.max(largestSumSubarray, curr);
        }

        return largestSumSubarray;
    }

    /**
     * You are given an integer array nums consisting of n elements, and an integer k.
     * <p>
     * Find a contiguous subarray whose length is equal to k that has the maximum average value and return this value.
     * Any answer with a calculation error less than '0,00000' will be accepted.
     */
    @Test
    void findMaxAvgSubarrayValueWithLengthTaskRunner() {
        int[] input = new int[]{1, 12, -5, -6, 50, 3};
        int k = 4;
        System.out.println(findMaxAvgSubarrayValueWithLength(input, k));
    }

    double findMaxAvgSubarrayValueWithLength(int[] nums, int k) {
        double currentSum = 0;
        for (int i = 0; i < k; i++) {
            currentSum += nums[i];
        }

        double maxSumSubarrayValuesWithLengthK = currentSum;
        for (int i = k; i < nums.length; i++) {
            currentSum += nums[i] - nums[i - k];
            maxSumSubarrayValuesWithLengthK = Math.max(maxSumSubarrayValuesWithLengthK, currentSum);
        }

        return maxSumSubarrayValuesWithLengthK / k;
    }

    /**
     * Given a binary array nums and an integer k,
     * return the maximum number of consecutive 1's in the array if you can flip at most k 0's.
     */
    @Test
    void findMaxConsecutiveOneWithAvailableFlippingKTimesTaskRunner() {
        int[] input = new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0};
        int k = 2;
        int expected = 6;
        assertEquals(expected, findMaxConsecutiveOneWithAvailableFlippingKTimes(input, k));
    }

    int findMaxConsecutiveOneWithAvailableFlippingKTimes(int[] nums, int k) {
        int left = 0, right = 0;
        int current = 0;
        int ans = 0;

        while (right < nums.length) {
            if (nums[right] == 0) {
                current++;
            }

            while (current > k) {
                if (nums[left] == 0) {
                    current--;
                }
                left++;
            }

            ans = Math.max(ans, right - left + 1);
            right++;
        }

        return ans;
    }

    /**
     * 209. Minimum Size Subarray Sum
     * Given an array of positive integers nums and a positive integer target, return the minimal length of a
     * subarray whose sum is greater than or equal to target. If there is no such subarray, return 0 instead.
     * <p>
     * Constraints:
     * 1 <= target <= 109
     * 1 <= nums.length <= 105
     * 1 <= nums[i] <= 104
     */
    @Test
    void minSubArrayLenTest() {
        int[] input = {2, 3, 1, 2, 4, 3};
        int target = 7;
        int expected = 2;

        assertEquals(expected, minSubArrayLen(target, input));
    }

    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, right = 0;
        int currentSum = 0;
        int ans = Integer.MAX_VALUE;

        while (right < nums.length) {
            currentSum += nums[right];
            while (currentSum >= target) {
                ans = Math.min(ans, right - left + 1);
                currentSum -= nums[left];
                left++;
            }
            right++;
        }

        return ans != Integer.MAX_VALUE ? ans : 0;
    }

    /**
     * 1456. Maximum Number of Vowels in a Substring of Given Length
     * Given a string s and an integer k, return the maximum number of vowel letters in any substring of s with length k.
     * Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.
     * <p>
     * Constraints:
     * 1 <= s.length <= 105
     * s consists of lowercase English letters.
     * 1 <= k <= s.length
     */
    @Test
    void maxVowels() {
        assertEquals(3, maxVowels("abciiidef", 3));
        assertEquals(2, maxVowels("aeiou", 2));
        assertEquals(2, maxVowels("leetcode", 3));
        assertEquals(7, maxVowels("ibpbhixfiouhdljnjfflpapptrxgcomvnb", 33));
    }

    public int maxVowels(String s, int k) {
        int currentVowels = 0, maxVowels = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i >= k) {
                if (isVowel(s.charAt(i - k))) {
                    currentVowels--;
                }
            }
            if (isVowel(s.charAt(i))) {
                currentVowels++;
            }

            maxVowels = Math.max(maxVowels, currentVowels);
        }

        return maxVowels;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }

    /**
     * You are given two strings s and t of the same length and an integer maxCost.
     * You want to change s to t. Changing the i^th character of s to i^th character of t costs |s[i] - t[i]|
     * (i.e., the absolute difference between the ASCII values of the characters).
     * Return the maximum length of a substring of s that can be changed to be the same
     * as the corresponding substring of t with a cost less than or equal to maxCost.
     * If there is no substring from s that can be changed to its corresponding substring from t, return 0.
     * <p>
     * Constraints:
     * 1 <= s.length <= 105
     * t.length == s.length
     * 0 <= maxCost <= 106
     * s and t consist of only lowercase English letters.
     */
    @Test
    void equalSubstring() {
        assertEquals(3, equalSubstring("abcd", "bcdf", 3));
        assertEquals(1, equalSubstring("abcd", "cdef", 3));
        assertEquals(1, equalSubstring("abcd", "acde", 0));
    }

    public int equalSubstring(String s, String t, int maxCost) {
        return 0;
    }

    @Test
    void some() {
        Integer i1 = 128;
        Integer i2 = 128;

        Integer i3 = -128;
        Integer i4 = -128;

        System.out.println((i1 == i2) + ", " + (i3 == i4));
    }
}
