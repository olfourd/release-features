package leetcode.arrays;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 26. Remove Duplicates from Sorted Array.
 * Given an integer array nums sorted in non-decreasing order,
 * remove the duplicates in-place such that each unique element appears only once.
 * The relative order of the elements should be kept the same. Then return the number of unique elements in nums.
 * <p>
 * Consider the number of unique elements of nums be k, to get accepted, you need to do the following things:
 * - Change the array nums such that the first k elements of nums contain the unique elements in the order
 * they were present in nums initially. The remaining elements of nums are not important as well as the size of nums.
 * - Return k.
 * <p>
 * Constraints:
 * 1 <= nums.length <= 3 * 10^4
 * -100 <= nums[i] <= 100
 * nums is sorted in non-decreasing order.
 */
public class RemoveDuplicates {

    @Test
    void test() {
        int[] input = new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int expected = 5;

        assertEquals(expected, getUniqueElementCount(input));
    }

    int getUniqueElementCount(int[] nums) {
        int uniqueCount = 0;
        int prev = -101;

        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            if (val != prev) {
                nums[uniqueCount] = val;
                uniqueCount++;
            }
            prev = val;
        }

        return uniqueCount;
    }

    /**
     * Given an integer array nums and an integer val, remove all occurrences of val in nums in-place.
     * The order of the elements may be changed. Then return the number of elements in nums which are not equal to val.
     * <p>
     * Consider the number of elements in nums which are not equal to val be k,
     * to get accepted, you need to do the following things:
     * - Change the array nums such that the first k elements of nums contain the elements which are not equal to val.
     * The remaining elements of nums are not important as well as the size of nums.
     * - Return k.
     * <p>
     * Constraints:
     * - 0 <= nums.length <= 100
     * - 0 <= nums[i] <= 50
     * - 0 <= val <= 100
     */
    @Test
    void test2() {
        int[] input = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        int val = 2;
        int expected = 5;

        assertEquals(expected, removeValAndGetGetCount(input, val));
        System.out.println(Arrays.toString(input));
    }

    private int removeValAndGetGetCount(int[] nums, int val) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num != val) {
                nums[count] = num;
                count++;
            }
        }
        return count;
    }
}
