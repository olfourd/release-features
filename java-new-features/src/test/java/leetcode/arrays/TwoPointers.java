package leetcode.arrays;

import lombok.Value;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TwoPointers {

    /**
     * 557. Reverse Words in a String III
     * Given a string s, reverse the order of characters in each word within a sentence
     * while still preserving whitespace and initial word order.
     * <p>
     * Constraints:
     * - 1 <= s.length <= 5 * 104
     * - s contains printable ASCII characters.
     * - s does not contain any leading or trailing spaces.
     * - There is at least one word in s.
     * - All the words in s are separated by a single space.
     */
    @Test
    void reverseWordsTest() {
        String input = "Let's take LeetCode contest";
        String actual = reverseWords(input);
        String expected = "s'teL ekat edoCteeL tsetnoc";

        assertEquals(expected, actual);
    }

    public String reverseWords(String s) {
        char[] ans = new char[s.length()];
        int leftSpace = 0, rightSpece = s.length() - 1;

        for (int i = 0, j = s.length() - 1; i <= j; i++, j--) {
            if (s.charAt(i) == ' ') {
                addReversedWord(ans, s, leftSpace, i - 1);
                ans[i] = s.charAt(i);
                leftSpace = i + 1;
            }

            if (s.charAt(j) == ' ') {
                addReversedWord(ans, s, j + 1, rightSpece);
                ans[j] = s.charAt(j);
                rightSpece = j - 1;
            }
        }

        addReversedWord(ans, s, leftSpace, rightSpece);

        return String.valueOf(ans);
    }

    private void addReversedWord(char[] value, String s, int from, int to) {
        for (; from <= to; from++, to--) {
            value[from] = s.charAt(to);
            value[to] = s.charAt(from);
        }
    }

    /**
     * Given a string s, reverse the string according to the following rules:
     * All the characters that are not English letters remain in the same position.
     * All the English letters (lowercase or uppercase) should be reversed.
     * Return s after reversing it.
     * <p>
     * Constraints:
     * - 1 <= s.length <= 100
     * - s consists of characters with ASCII values in the range [33, 122].
     * - s does not contain '\"' or '\\'.
     */
    @Test
    void reverseOnlyLettersTest() {
        String input = "?6C40E";
        String actual = reverseOnlyLetters(input);
        String expected = "?6E40C";

        assertEquals(expected, actual);
    }

    public String reverseOnlyLetters(String s) {
        char[] chars = s.toCharArray();
        int left = 0, right = chars.length - 1;
        while (left < right) {
            if (!Character.isLetter(chars[left])) {
                left++;
                continue;
            }
            if (!Character.isLetter(chars[right])) {
                right--;
                continue;
            }

            swap(chars, left, right);
            left++;
            right--;
        }

        return String.valueOf(chars);
    }

    /**
     * 283. Move Zeroes
     * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the
     * non-zero elements. Note that you must do this in-place without making a copy of the array.
     * <p>
     * Constraints:
     * 1 <= nums.length <= 104
     * -231 <= nums[i] <= 231 - 1
     */
    @Test
    void moveZeroesTest() {
        int[] input = new int[]{0, 1, 0, 3, 12};
        moveZeroes(input);
        int[] expected = new int[]{1, 3, 12, 0, 0};

        assertEquals(Arrays.toString(expected), Arrays.toString(input));
    }

    private void moveZeroes(int[] nums) {
        for (int i = 0, notZeroPointer = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                swap(nums, i, notZeroPointer);
                notZeroPointer++;
            }
        }
    }

    /**
     * 2000. Reverse Prefix of Word.
     * Given a 0-indexed string word and a character ch, reverse the segment of word that starts at index 0 and ends
     * at the index of the first occurrence of ch (inclusive). If the character ch does not exist in word, do nothing.
     * <p>
     * Constraints:
     * 1 <= word.length <= 250
     * word consists of lowercase English letters.
     * ch is a lowercase English letter.
     */
    @Test
    void reversePrefixTest() {
        assertEquals("dcbaefd", reversePrefix("abcdefd", 'd'));
        assertEquals("zxyxxe", reversePrefix("xyxzxe", 'z'));
        assertEquals("abcd", reversePrefix("abcd", 'z'));
    }

    public String reversePrefix(String word, char ch) {
        int charIndex = word.indexOf(ch);
        if (charIndex == -1) {
            return word;
        }

        StringBuilder sb = new StringBuilder(word.substring(0, charIndex + 1));
        sb.reverse();
        sb.append(word.substring(charIndex+1));

        return sb.toString();
    }

    private void swap(char[] value, int from, int to) {
        char tmp = value[from];
        value[from] = value[to];
        value[to] = tmp;
    }

    private void swap(int[] value, int from, int to) {
        int tmp = value[from];
        value[from] = value[to];
        value[to] = tmp;
    }
}
