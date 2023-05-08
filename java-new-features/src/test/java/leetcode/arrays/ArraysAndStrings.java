package leetcode.arrays;

import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ArraysAndStrings {

    @Test
    public void fibonacciTaskRunner() {
        System.out.println(Arrays.toString(getFibonacciArray(6)));
        System.out.println(getFibonacciByIndex(6));
    }

    private int getFibonacciByIndex(int n) {
        if (n <= 1) {
            return n;
        }

        int oneBack = getFibonacciByIndex(n - 1);
        int twoBack = getFibonacciByIndex(n - 2);
        return oneBack + twoBack;
    }

    private int[] getFibonacciArray(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("N can't be less zero. Current value: " + n);
        }

        int[] result = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            result[i] = getFibonacciByIndex(i);
        }

        return result;
    }

    /**
     * Start the pointers at the edges of the input. Move them towards each other until they meet.
     */
    @Test
    void twoPointersTaskRunner() {
        assertEquals(0, getIterateCountTwoPointers(1));
        assertEquals(4, getIterateCountTwoPointers(8));
        assertEquals(4, getIterateCountTwoPointers(9));
        assertEquals(5, getIterateCountTwoPointers(10));

    }

    private int getIterateCountTwoPointers(int arrayLength) {
        if (arrayLength < 1) {
            throw new IllegalArgumentException("Minimal array length is 1. Current value: " + arrayLength);
        }

        int result = 0;
        int left = 0, right = arrayLength - 1;

        while (left < right) {
            result++;
            left++;
            right--;
            System.out.println(result);
        }

        return result;
    }

    /**
     * Example 2: Given a sorted array of unique integers and a target integer,
     * return true if there exists a pair of numbers that sum to target, false otherwise.
     */
    @Test
    void hasSumTwoElementsEqualsTargetInSortedArrayTaskRunner() {
        int[] sortedArray = getSortedIntArray(30, 1, 30);

        System.out.println(Arrays.toString(sortedArray));
        hasSumTwoElementsEqualsTarget(sortedArray, 30);
    }

    private boolean hasSumTwoElementsEqualsTarget(int[] sortedArray, int target) {
        int left = 0, right = sortedArray.length - 1;

        while (left < right) {
            int sumTwoElements = sortedArray[left] + sortedArray[right];
            if (sumTwoElements == target) {
                System.out.printf("TRUE!!! left index = {%s}, right index = {%s}", left, right);
                return true;
            }

            if (sumTwoElements > target) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println("FALSE!!!");
        return false;
    }

    /**
     * Example 3: Given two sorted integer arrays, return an array that combines both of them and is also sorted.
     */
    @Test
    void combineTwoSortedArraysIntoOneSortedArrayTaskRunner() {
        int[] firstArray = getSortedIntArray(10, 1, 30);
        int[] secondArray = getSortedIntArray(10, 1, 30);

        System.out.printf("FirstArray={%s} \n", Arrays.toString(firstArray));
        System.out.printf("SecondArray={%s} \n", Arrays.toString(secondArray));

        int[] combinedArray = combineTwoSortedArraysIntoOneSortedArray(firstArray, secondArray);
        System.out.printf("CombinedArray={%s} \n", Arrays.toString(combinedArray));
    }

    private int[] combineTwoSortedArraysIntoOneSortedArray(int[] sortedArray1, int[] sortedArray2) {
        int i = 0, j = 0;
        int[] result = new int[sortedArray1.length + sortedArray2.length];

        while (i < sortedArray1.length && j < sortedArray2.length) {
            int val1 = sortedArray1[i];
            int val2 = sortedArray2[j];

            if (val1 < val2) {
                result[i + j] = val1;
                i++;
            } else {
                result[i + j] = val2;
                j++;
            }
        }

        while (i < sortedArray1.length) {
            result[i + j] = sortedArray1[i];
            i++;
        }

        while (j < sortedArray2.length) {
            result[i + j] = sortedArray2[j];
            j++;
        }

        return result;
    }

    /**
     * Example 4: 392. Is Subsequence.
     * Given two strings s and t, return true if s is a subsequence of t, or false otherwise.
     */
    @Test
    void isSubsequenceTaskRunner() {
        assertTrue(isSubsequence("acebca", "ac"));
        assertFalse(isSubsequence("some", "boo"));
    }

    boolean isSubsequence(String t, String s) {
        int i = 0, j = 0;

        while ((i < t.length()) && (j < s.length())) {
            if (s.charAt(j) == t.charAt(i)) {
                j++;
            }
            i++;
        }

        return j == s.length();
    }

    /**
     * Write a function that reverses a string. The input string is given as an array of characters s.
     * You must do this by modifying the input array in-place with O(1) extra memory.
     */
    @Test
    void reverseStringTaskRunner() {
        char[] input = "Hello".toCharArray();
        reverseString(input);
        assertEquals(Arrays.toString(new char[]{'o', 'l', 'l', 'e', 'H'}), Arrays.toString(input));
    }

    public void reverseString(char[] s) {
        int left = 0, right = s.length - 1;

        while (left < right) {
            char tmp = s[left];
            s[left] = s[right];
            s[right] = tmp;

            left++;
            right--;
        }
    }

    /**
     * Squares of a Sorted Array.
     * Given an integer array nums sorted in non-decreasing order,
     * return an array of the squares of each number sorted in non-decreasing order.
     */
    @Test
    void sortedSquaresTaskRunner() {
        int[] input = new int[]{-4, -1, 0, 3, 10};
        int[] output = sortedSquares(input);
        int[] expected = new int[]{0, 1, 9, 16, 100};

        assertEquals(Arrays.toString(expected), Arrays.toString(output));

        input = new int[]{-7, -3, 2, 3, 11};
        output = sortedSquares(input);
        expected = new int[]{4, 9, 9, 49, 121};

        assertEquals(Arrays.toString(expected), Arrays.toString(output));
    }

    public int[] sortedSquares(int[] nums) {
        int[] output = new int[nums.length];
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            if (Math.abs(nums[left]) > nums[right]) {
                output[right - left] = nums[left] * nums[left];
                left++;
            } else {
                output[right - left] = nums[right] * nums[right];
                right--;
            }
        }

        return output;
    }

    private static int[] getSortedIntArray(int size, int startInclusive, int endExclusive) {
        return Stream.generate(() -> RandomUtils.nextInt(startInclusive, endExclusive))
                .limit(size)
                .sorted()
                .mapToInt(value -> value)
                .toArray();
    }

    /**
     * 28. Find the Index of the First Occurrence in a String
     * Given two strings needle and haystack, return the index of the first occurrence of needle in haystack,
     * or -1 if needle is not part of haystack.
     * <p>
     * Constraints:
     * - 1 <= haystack.length, needle.length <= 104
     * - haystack and needle consist of only lowercase English characters.
     */
    @Test
    void firstOccurrenceTest() {
        String haystack = "mississippi", needle = "issip";
        int expected = 4;
        assertEquals(expected, getFirstOccurrenceIndex(haystack, needle));
    }

    private int getFirstOccurrenceIndex(String haystack, String needle) {
        if (haystack.length() < needle.length()) {
            return -1;
        }

        for (int i = 0, j = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) != needle.charAt(j)) {
                i = i - j;
                j = 0;
            } else {
                if (j == needle.length() - 1) {
                    return i - j;
                }
                j++;
            }
        }

        return -1;
    }


}
