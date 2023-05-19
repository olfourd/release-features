package leetcode.hashing;

import org.junit.gen5.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertFalse;
import static org.springframework.test.util.AssertionErrors.assertTrue;

public class Hashing {

    /**
     * Given an array of integers nums and an integer target, return indices of two numbers such that they add up
     * to target. You cannot use the same index twice.
     */
    @Test
    void twoSumTest() {
        int[] input = {5, 2, 7, 10, 3, 9};
        int target = 8;
        int[] expected = {0, 4};

        assertEquals("", expected, twoSum(input, target));
    }

    public int[] twoSum(int[] arr, int target) {
        HashMap<Integer, Integer> valIndexMap = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            int val = arr[i];
            int diff = target - val;
            if (valIndexMap.containsKey(diff)) {
                return new int[]{valIndexMap.get(diff), i};
            }
            valIndexMap.put(val, i);
        }

        return new int[]{-1, -1};
    }

    /**
     * Given a string s consisting of lowercase English letters, return the first letter to appear twice.
     * Note:
     * - A letter a appears twice before another letter b if the second occurrence of a is before the second occurrence of b.
     * - s will contain at least one letter that appears twice.
     */
    @Test
    public void repeatedCharacterTest() {
        String input = "abccbaacz";
        assertEquals("", 'c', repeatedCharacterTwice(input));
    }

    public char repeatedCharacterTwice(String s) {
        Set<Character> charSet = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char charAt = s.charAt(i);
            if (charSet.contains(charAt)) {
                return charAt;
            }
            charSet.add(charAt);
        }

        return '-';
    }

    /**
     * A pangram is a sentence where every letter of the English alphabet appears at least once.
     * Given a string sentence containing only lowercase English letters, return true if sentence is a pangram,
     * or false otherwise.
     */
    @Test
    public void checkIfPangramTest() {
        assertTrue("", checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
        assertFalse("", checkIfPangram("leetcode"));
    }


    public boolean checkIfPangram(String sentence) {
        int alphabetMaxCount = 26;
        Set<Character> charSet = new HashSet<>(alphabetMaxCount);
        for (int i = 0; i < sentence.length(); i++) {
            charSet.add(sentence.charAt(i));
        }

        return charSet.size() == alphabetMaxCount;
    }

    /**
     * Missing Number.
     * Given an array nums containing n distinct numbers in the range [0, n], return the only number in the range that is missing from the array.
     * n == nums.length
     * 1 <= n <= 104
     * 0 <= nums[i] <= n
     * All the numbers of nums are unique.
     */
    public int missingNumber(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }

        for (int i = 0; i < set.size(); i++) {
            if (!set.contains(i)) {
                return i;
            }
        }

        return nums.length;
    }

    /**
     * Counting Elements.
     * <p>
     * Given an integer array arr, count how many elements x there are, such that x + 1 is also in arr.
     * If there are duplicates in arr, count them separately.
     * <p>
     * 1 <= arr.length <= 1000
     * 0 <= arr[i] <= 1000
     */
    public int countElements(int[] arr) {
        Set<Integer> set = new HashSet<>();
        for (int el : arr) {
            set.add(el);
        }
        int count = 0;
        for (int x : arr) {
            if (set.contains(x + 1)){
                count++;
            }
        }

        return count;
    }
}
