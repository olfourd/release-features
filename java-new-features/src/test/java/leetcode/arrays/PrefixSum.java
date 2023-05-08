package leetcode.arrays;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Arrays;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

public class PrefixSum {

    /**
     * Example 1: Given an integer array nums, an array queries where queries[i] = [x, y] and an integer limit,
     * return a boolean array that represents the answer to each query.
     * A query is true if the sum of the subarray from x to y is less than limit, or false otherwise.
     * <p>
     * For example, given nums = [1, 6, 3, 2, 7, 2] and queries = [[0, 3], [2, 5], [2, 4]] and limit = 13,
     * the answer is [true, false, true]. For each query, the subarray sums are [12, 14, 12].
     */
    @Test
    void sumBySearchLessThenLimitTest() {
        int[] nums = new int[]{1, 6, 3, 2, 7, 2};
        int[][] queries = new int[][]{{0, 3}, {2, 5}, {2, 4}};
        int limit = 13;
        boolean[] expected = new boolean[]{true, false, true};

        assertEquals(Arrays.toString(expected), Arrays.toString(sumBySearchLessThenLimit(nums, queries, limit)));
    }

    private boolean[] sumBySearchLessThenLimit(int[] nums, int[][] queries, int limit) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = prefix[i - 1] + nums[i];
        }

        boolean[] ans = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int from = queries[i][0], to = queries[i][1];
            ans[i] = prefix[to] - prefix[from] + nums[from] < limit;
        }

        return ans;
    }

    /**
     * 2270. Number of Ways to Split Array
     * You are given a 0-indexed integer array nums of length n.
     * nums contains a valid split at index i if the following are true:
     * - The sum of the first i + 1 elements is greater than or equal to the sum of the last n - i - 1 elements.
     * - There is at least one element to the right of i. That is, 0 <= i < n - 1.
     * <p>
     * Return the number of valid splits in nums.
     * <p>
     * Constraints:
     * - 2 <= nums.length <= 105
     * - -105 <= nums[i] <= 105
     */
    @Test
    void waysToSplitArrayTest() {
        int[] input = new int[]{10, 4, -8, 7};
        int expected = 2;
        int actual = waysToSplitArray(input);
        assertEquals(expected, actual);
    }

    public int waysToSplitArray(int[] nums) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefix[i] = nums[i] + prefix[i - 1];
        }

        int ans = 0;

        for (int i = 0; i < prefix.length - 1; i++) {
            if (prefix[i] >= prefix[prefix.length - 1] - prefix[i]) {
                ans++;
            }
        }

        return ans;
    }

    /**
     * Given an array nums. We define a running sum of an array as runningSum[i] = sum(nums[0]…nums[i]).
     * Return the running sum of nums.
     * <p>
     * Constraints:
     * 1 <= nums.length <= 1000
     * -10^6 <= nums[i] <= 10^6
     */
    @Test
    void runningSumTest() {
        int[] input = new int[]{1, 2, 3, 4};
        int[] expected = new int[]{1, 3, 6, 10};
        int[] actual = runningSum(input);

        assertEquals(Arrays.toString(expected), Arrays.toString(actual));
    }

    public int[] runningSum(int[] nums) {
        int[] prefix = new int[nums.length];
        prefix[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i] = nums[i] + prefix[i - 1];
        }

        return prefix;
    }

    /**
     * Minimum Value to Get Positive Step by Step Sum.
     * <p>
     * Given an array of integers nums, you start with an initial positive value startValue.
     * In each iteration, you calculate the step by step sum of startValue plus elements in nums (from left to right).
     * Return the minimum positive value of startValue such that the step by step sum is never less than 1.
     * <p>
     * Constraints:
     * 1 <= nums.length <= 100
     * -100 <= nums[i] <= 100
     */
    @Test
    void minStartValueTest() {
        int[] input = new int[]{-3, 2, -3, 4, 2};
        int actual = minStartValue(input);
        int expected = 5;

        assertEquals(expected, actual);
    }

    public int minStartValue(int[] nums) {
        int min = 0, sum = 0;

        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if (sum < min) {
                min = sum;
            }
        }

        return min < 0 ? Math.abs(min) + 1 : 1;
    }


    /**
     * There is a biker going on a road trip. The road trip consists of n + 1 points at different altitudes.
     * The biker starts his trip on point 0 with altitude equal 0.
     * You are given an integer array gain of length n where gain[i] is the net gain in altitude between points
     * i and i + 1 for all (0 <= i < n). Return the highest altitude of a point.
     * <p>
     * Constraints:
     * n == gain.length
     * 1 <= n <= 100
     * -100 <= gain[i] <= 100
     */
    @Test
    public void largestAltitudeTest() {
        assertEquals(1, largestAltitude(new int[]{-5, 1, 5, 0, -7}));
        assertEquals(0, largestAltitude(new int[]{-4, -3, -2, -1, 4, 3, 2}));
    }

    public int largestAltitude(int[] gain) {
        int prevSum = gain[0];
        int result = Math.max(prevSum, 0);

        for (int i = 1; i < gain.length; i++) {
            int currentSum = prevSum + gain[i];
            if (currentSum > result) {
                result = currentSum;
            }
            prevSum = currentSum;
        }

        return result;
    }

    /**
     * Given an array of integers nums, calculate the pivot index of this array.
     * The pivot index is the index where the sum of all the numbers strictly to the left of the index is equal to
     * the sum of all the numbers strictly to the index's right.
     * If the index is on the left edge of the array, then the left sum is 0 because there are no elements to the left.
     * This also applies to the right edge of the array.
     * Return the leftmost pivot index. If no such index exists, return -1.
     * <p>
     * Constraints:
     * 1 <= nums.length <= 104
     * -1000 <= nums[i] <= 1000
     */
    @Test
    void pivotIndexTest() {
        assertEquals(3, pivotIndex(new int[]{1, 7, 3, 6, 5, 6}));
        assertEquals(-1, pivotIndex(new int[]{1, 2, 3}));
        assertEquals(0, pivotIndex(new int[]{2, 1, -1}));
    }

    public int pivotIndex(int[] nums) {
        int[] prefix = new int[nums.length + 1];
        prefix[0] = 0;
        prefix[1] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            prefix[i + 1] = nums[i] + prefix[i];
        }

        for (int i = 0; i < prefix.length - 1; i++) {
            int left = prefix[i], right = prefix[prefix.length - 1] - prefix[i + 1];
            if (left == right) {
                return i;
            }
        }

        return -1;
    }

    /**
     * 303. Range Sum Query - Immutable
     * Given an integer array nums, handle multiple queries of the following type:
     * Calculate the sum of the elements of nums between indices left and right inclusive where left <= right.
     * - int sumRange(int left, int right).
     * <p>
     * Constraints:
     * 1 <= nums.length <= 104
     * -105 <= nums[i] <= 105
     * 0 <= left <= right < nums.length
     * At most 104 calls will be made to sumRange.
     */
    @ParameterizedTest
    @CsvSource({
            "0, 2, 1",
            "2, 5, -1",
            "0, 5, -3"
    })
    void numArrayTest(Integer fromInclude, Integer toInclude, Integer expected) {
        NumArray numArray = new NumArray(new int[]{-2, 0, 3, -5, 2, -1});
        int actual = numArray.sumRange(fromInclude, toInclude);
        assertEquals(expected, actual);
    }

    private static class NumArray {

        private int prefix[];

        /**
         * NumArray(int[] nums) Initializes the object with the integer array nums.
         */
        public NumArray(int[] nums) {
            this.prefix = nums;
            for (int i = 1; i < nums.length; i++) {
                this.prefix[i] = nums[i] + this.prefix[i - 1];
            }
        }

        /**
         * Returns the sum of the elements of nums between indices left and right inclusive.
         *
         * @return the sum between indexes left and right inclusive.
         */
        public int sumRange(int left, int right) {
            if (left == 0) {
                return this.prefix[right];
            } else {
                return prefix[right] - prefix[left - 1];
            }
        }
    }
}
