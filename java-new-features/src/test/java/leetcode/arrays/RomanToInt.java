package leetcode.arrays;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * <p>
 * Constraints:
 * - 1 <= s.length <= 15
 * - s contains only the characters ('I', 'V', 'X', 'L', 'C', 'D', 'M').
 * - It is guaranteed that s is a valid roman numeral in the range [1, 3999].
 */
public class RomanToInt {

    @Test
    void romanToIntTest() {
        assertEquals(3, romanToInt("III"));
        assertEquals(58, romanToInt("LVIII"));
        assertEquals(1994, romanToInt("MCMXCIV"));
    }

//    public int romanToInt(String s) {
//        Map<Character, Integer> romanToIntMappingValues = Map.of(
//                'I', 1,
//                'V', 5,
//                'X', 10,
//                'L', 50,
//                'C', 100,
//                'D', 500,
//                'M', 1000
//        );
//
//        Map<Character, Set<Character>> checkNextRomanLetterMap = Map.of(
//                'I', Set.of('V', 'X'),
//                'X', Set.of('L', 'C'),
//                'C', Set.of('D', 'M')
//        );
//
//        Integer intVal = 0;
//
//        for (int i = 0; i < s.length(); i++) {
//            char charAtI = s.charAt(i);
//
//            Set<Character> availableNextRomanLetters = checkNextRomanLetterMap.get(charAtI);
//
//            if (availableNextRomanLetters == null || i == s.length() - 1) {
//                intVal += romanToIntMappingValues.get(charAtI);
//                continue;
//            }
//
//            char nextChar = s.charAt(i + 1);
//            if (availableNextRomanLetters.contains(nextChar)) {
//                intVal += romanToIntMappingValues.get(nextChar) - romanToIntMappingValues.get(charAtI);
//                i++;
//            } else {
//                intVal += romanToIntMappingValues.get(charAtI);
//            }
//        }
//
//        return intVal;
//    }

    public int romanToInt(String s) {
        int answer = 0, prev = 0;

        for (int i = s.length() - 1; i >= 0; i--) {
            int current = mapRomanCharToInt(s.charAt(i));
            answer = current >= prev
                    ? answer + current
                    : answer - current;
            prev = current;
        }

        return answer;
    }

    private int mapRomanCharToInt(char romanValue) {
        return switch (romanValue) {
            case 'I' -> 1;
            case 'V' -> 5;
            case 'X' -> 10;
            case 'L' -> 50;
            case 'C' -> 100;
            case 'D' -> 500;
            case 'M' -> 1000;
            default -> throw new IllegalArgumentException("Not valid roman char");
        };
    }

}
