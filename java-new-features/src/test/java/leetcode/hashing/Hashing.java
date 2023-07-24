package leetcode.hashing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.tuple.Pair;
import org.junit.gen5.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

class HashingTasksTest {

    /**
     * Given an array of integers nums and an integer target, return indices of two numbers such that they add up
     * to target. You cannot use the same index twice.
     */
    @Test
    void twoSumTest() {
        int[] input = {5, 2, 7, 10, 3, 9};
        int target = 8;
        int[] expected = {0, 4};

        Assertions.assertTrue(Arrays.equals(expected, twoSum(input, target)));
    }

    public int[] twoSum(int[] arr, int target) {
        Map<Integer, Integer> valIndexMap = new HashMap<>();

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
    void repeatedCharacterTest() {
        String input = "abccbaacz";
        Assertions.assertEquals('c', repeatedCharacterTwice(input));
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
    void checkIfPangramTest() {
        Assertions.assertTrue(checkIfPangram("thequickbrownfoxjumpsoverthelazydog"));
        Assertions.assertFalse(checkIfPangram("leetcode"));
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
            if (set.contains(x + 1)) {
                count++;
            }
        }

        return count;
    }

    @Test
    void findWinnersTest() {
        final int[][] input = new int[][]{{26651, 85186}, {19790, 34845}, {20238, 59388}, {12788, 40994}, {46703, 28184}, {49936, 83960}, {76158, 33038}, {96005, 2100}, {86989, 72152}, {73342, 82931}, {90852, 54532}, {61649, 18655}, {9506, 73692}, {56189, 5503}, {34939, 78765}, {98315, 19818}, {57974, 42491}, {5906, 8266}, {97215, 95437}, {48266, 87323}, {86578, 82992}, {29919, 70323}, {38749, 98079}, {10259, 27750}, {977, 39147}, {43625, 38229}, {56044, 32634}, {53509, 13100}, {59446, 68045}, {38379, 7712}, {87511, 62110}, {35456, 508}, {76381, 89490}, {73270, 55169}, {82004, 20887}, {11239, 24195}, {56561, 57161}, {96112, 81582}, {20403, 76188}, {77876, 61782}, {99546, 39653}, {68650, 57489}, {27952, 11410}, {10595, 73394}, {98154, 49624}, {10866, 3758}, {70902, 97205}, {43360, 93218}, {59119, 82709}, {7379, 47660}, {76950, 1884}, {20120, 87138}, {23010, 5729}, {96553, 12208}, {70423, 36052}, {98588, 30989}, {87447, 24694}, {90748, 39031}, {17455, 13250}, {49557, 4501}, {99624, 72185}, {24791, 99744}, {80458, 57635}, {16175, 29359}, {53170, 83581}, {95936, 39348}, {5634, 95357}, {58517, 73348}, {54861, 34521}, {86692, 23939}, {11623, 85398}, {7562, 4748}, {13217, 29610}, {52705, 67263}, {95277, 68332}, {50498, 96263}, {18070, 33256}, {1901, 4237}, {11894, 97936}, {22135, 34362}, {7698, 64698}, {22893, 62847}, {2294, 14712}, {69996, 48778}, {340, 93723}, {42138, 33976}, {53231, 33755}, {29512, 501}, {81773, 58971}, {19189, 20321}, {30220, 37726}, {78678, 17938}, {79172, 26444}, {67695, 39219}, {23206, 40923}, {5651, 8928}, {49333, 29553}, {98589, 75052}, {99636, 94502}, {38455, 66022}, {3654, 56685}, {42118, 65465}, {83831, 62461}, {81301, 90996}, {35209, 23863}, {99223, 69931}, {5206, 42971}, {2143, 97501}, {91454, 60002}, {6307, 70975}, {48194, 6557}, {60125, 70237}, {60551, 85062}, {58731, 86177}, {98064, 94854}, {63923, 51970}, {87687, 36064}, {37926, 97759}, {87685, 22212}, {62051, 7149}, {1430, 13255}, {55928, 48428}, {8319, 99922}, {58503, 69220}, {45821, 70178}, {56815, 31101}, {77963, 7364}, {28828, 65658}, {92037, 99306}, {97928, 41984}, {47086, 73658}, {35491, 79727}, {24688, 42610}, {32304, 18289}, {52770, 31966}, {96613, 96122}, {73643, 49573}, {27623, 89143}, {62163, 93629}, {41018, 53285}, {59655, 4055}, {80777, 17295}, {9744, 38601}};
        final List<Integer> notLostExpected = List.of(340, 977, 1430, 1901, 2143, 2294, 3654, 5206, 5634, 5651, 5906, 6307, 7379, 7562, 7698, 8319, 9506, 9744, 10259, 10595, 10866, 11239, 11623, 11894, 12788, 13217, 16175, 17455, 18070, 19189, 19790, 20120, 20238, 20403, 22135, 22893, 23010, 23206, 24688, 24791, 26651, 27623, 27952, 28828, 29512, 29919, 30220, 32304, 34939, 35209, 35456, 35491, 37926, 38379, 38455, 38749, 41018, 42118, 42138, 43360, 43625, 45821, 46703, 47086, 48194, 48266, 49333, 49557, 49936, 50498, 52705, 52770, 53170, 53231, 53509, 54861, 55928, 56044, 56189, 56561, 56815, 57974, 58503, 58517, 58731, 59119, 59446, 59655, 60125, 60551, 61649, 62051, 62163, 63923, 67695, 68650, 69996, 70423, 70902, 73270, 73342, 73643, 76158, 76381, 76950, 77876, 77963, 78678, 79172, 80458, 80777, 81301, 81773, 82004, 83831, 86578, 86692, 86989, 87447, 87511, 87685, 87687, 90748, 90852, 91454, 92037, 95277, 95936, 96005, 96112, 96553, 96613, 97215, 97928, 98064, 98154, 98315, 98588, 98589, 99223, 99546, 99624, 99636);
        final List<Integer> oneLostExpected = List.of(501, 508, 1884, 2100, 3758, 4055, 4237, 4501, 4748, 5503, 5729, 6557, 7149, 7364, 7712, 8266, 8928, 11410, 12208, 13100, 13250, 13255, 14712, 17295, 17938, 18289, 18655, 19818, 20321, 20887, 22212, 23863, 23939, 24195, 24694, 26444, 27750, 28184, 29359, 29553, 29610, 30989, 31101, 31966, 32634, 33038, 33256, 33755, 33976, 34362, 34521, 34845, 36052, 36064, 37726, 38229, 38601, 39031, 39147, 39219, 39348, 39653, 40923, 40994, 41984, 42491, 42610, 42971, 47660, 48428, 48778, 49573, 49624, 51970, 53285, 54532, 55169, 56685, 57161, 57489, 57635, 58971, 59388, 60002, 61782, 62110, 62461, 62847, 64698, 65465, 65658, 66022, 67263, 68045, 68332, 69220, 69931, 70178, 70237, 70323, 70975, 72152, 72185, 73348, 73394, 73658, 73692, 75052, 76188, 78765, 79727, 81582, 82709, 82931, 82992, 83581, 83960, 85062, 85186, 85398, 86177, 87138, 87323, 89143, 89490, 90996, 93218, 93629, 93723, 94502, 94854, 95357, 95437, 96122, 96263, 97205, 97501, 97759, 97936, 98079, 99306, 99744, 99922);

        List<List<Integer>> actual = findWinners(input);

        Assertions.assertNotNull(actual);
        Assertions.assertNotNull(actual.get(0));
        Assertions.assertEquals(notLostExpected, actual.get(0));
        Assertions.assertNotNull(actual.get(1));
        Assertions.assertEquals(oneLostExpected, actual.get(1));
    }

    public List<List<Integer>> findWinners(int[][] findWinners) {
        Map<Integer, LooseCounter> idScoreMap = new TreeMap<>(Comparator.naturalOrder());
        Stream.of(findWinners)
                .forEach(score -> {
                    LooseCounter looserLooseCounter = idScoreMap.getOrDefault(score[1], new LooseCounter());
                    looserLooseCounter.increaseLoose();
                    idScoreMap.putIfAbsent(score[1], looserLooseCounter);
                });

        List<Integer> first = idScoreMap.entrySet()
                .stream()
                .filter(idScoreEntry -> idScoreEntry.getValue().getLoose() == 0)
                .map(Map.Entry::getKey)
                .toList();

        List<Integer> second = idScoreMap.entrySet()
                .stream()
                .filter(idScoreEntry -> idScoreEntry.getValue().getLoose() == 1)
                .map(Map.Entry::getKey)
                .toList();

        return List.of(first, second);
    }

    private static class LooseCounter {
        private Integer loose;

        public LooseCounter() {
            this.loose = 0;
        }

        public Integer getLoose() {
            return loose;
        }

        public void increaseLoose() {
            this.loose++;
        }
    }

    @ParameterizedTest
    @MethodSource("largestUniqueNumberTestProvider")
    void largestUniqueNumberTest(int[] input, int expected) {
        int actual = largestUniqueNumber(input);

        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> largestUniqueNumberTestProvider() {
        return Stream.of(
                Arguments.of(new int[]{5, 7, 3, 9, 4, 9, 8, 3, 1}, 8),
                Arguments.of(new int[]{9, 9, 8, 8}, -1)
        );
    }

    public int largestUniqueNumber(int[] nums) {
        Map<Integer, Integer> countMap = new HashMap<>();
        int result = -1;

        for (int value : nums) {
            Integer count = countMap.getOrDefault(value, 0);
            countMap.put(value, count + 1);
        }

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (entry.getValue() == 1) {
                result = Math.max(result, entry.getKey());
            }
        }

        return result;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "nlaebolko:1",
            "loonbalxballpoon:2",
            "leetcode:0"
    }, delimiter = ':')
    void maxNumberOfBalloonsTest(String input, Integer expectedBalloonsWordCount) {
        int actual = maxNumberOfBalloons(input);

        Assertions.assertEquals(expectedBalloonsWordCount, actual);
    }

    public int maxNumberOfBalloons(String text) {
        Map<Character, Integer> textCharCountMap = new HashMap<>(Map.of(
                'b', 0,
                'a', 0,
                'l', 0,
                'o', 0,
                'n', 0
        ));

        for (char c : text.toCharArray()) {
            if (textCharCountMap.containsKey(c)) {
                textCharCountMap.put(c, textCharCountMap.get(c) + 1);
            }
        }

        int balloonCount = Integer.MAX_VALUE;


        for (Character c : textCharCountMap.keySet()) {
            int targetCharMultiplier = c == 'l' || c == 'o' ? 2 : 1;
            balloonCount = Math.min(balloonCount, textCharCountMap.get(c) / targetCharMultiplier);
        }

        return balloonCount;
    }

    @ParameterizedTest
    @MethodSource("maximumSumTestData")
    void maximumSumTest(int[] input, int expected) {
        int actual = maximumSum(input);

        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> maximumSumTestData() {
        return Stream.of(
                Arguments.of(new int[]{18, 43, 36, 13, 7}, 54),
                Arguments.of(new int[]{10, 12, 19, 14}, -1)
        );
    }

    public int maximumSum(int[] nums) {
        Map<Integer, Integer> digitSumNumsMap = new HashMap<>();
        int ans = -1;

        for (int num : nums) {
            int digitSum = getDigitSum(num);

            if (digitSumNumsMap.containsKey(digitSum)) {
                ans = Math.max(ans, num + digitSumNumsMap.get(digitSum));
            }

            digitSumNumsMap.put(digitSum, Math.max(digitSumNumsMap.getOrDefault(digitSum, 0), num));
        }

        return ans;
    }

    private int getDigitSum(int num) {
        int digitSum = 0;
        while (num > 0) {
            digitSum += num % 10;
            num /= 10;
        }

        return digitSum;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "a:b:false",
            "aa:ab:false",
            "aa:aab:true",
            "bg:efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfgdiaigdadhcfcj: true"
    }, delimiter = ':')
    void canConstructTest(String ransomNote, String magazine, Boolean expected) {
        boolean actual = canConstruct(ransomNote, magazine);

        Assertions.assertEquals(expected, actual);
    }

    public boolean canConstruct(String ransomNote, String magazine) {
        Map<Character, Integer> ransomNoteCharCountMap = toCharCountMap(ransomNote);
        Map<Character, Integer> magazineCharCountMap = toCharCountMap(magazine);

        for (Map.Entry<Character, Integer> entry : ransomNoteCharCountMap.entrySet()) {
            if (!magazineCharCountMap.containsKey(entry.getKey()) ||
                    magazineCharCountMap.get(entry.getKey()) < entry.getValue()) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    private Map<Character, Integer> toCharCountMap(String value) {
        Map<Character, Integer> charCountMap = new HashMap<>();
        for (Character character : value.toCharArray()) {
            charCountMap.put(character, charCountMap.getOrDefault(character, 0) + 1);
        }
        return charCountMap;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "aA:aAAbbbb:3",
            "z:ZZ:0",
    }, delimiter = ':')
    void numJewelsInStonesTest(String jewels, String stones, int expected) {
        int actual = numJewelsInStones(jewels, stones);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * You're given strings jewels representing the types of stones that are jewels, and stones representing
     * the stones you have. Each character in stones is a type of stone you have.
     * You want to know how many of the stones you have are also jewels.
     * <p>
     * Letters are case sensitive, so "a" is considered a different type of stone from "A".
     */
    public int numJewelsInStones(String jewels, String stones) {
        Map<Character, Integer> stonesCharCountMap = new HashMap<>();
        for (Character character : stones.toCharArray()) {
            stonesCharCountMap.put(character, stonesCharCountMap.getOrDefault(character, 0) + 1);
        }

        int ans = 0;
        for (Character character : jewels.toCharArray()) {
            Integer count = stonesCharCountMap.get(character);
            if (count != null && count > 0) {
                ans += count;
            }
        }
        return ans;
    }

    @ParameterizedTest
    @CsvSource(value = {
            "abcabcbb:3",
            "bbbbb:1",
            "pwwkew:3",
            "aab:2",
            "qrsvbspk:5"
    }, delimiter = ':')
    void lengthOfLongestSubstringTest(String s, Integer expected) {
        int actual = lengthOfLongestSubstring(s);
        Assertions.assertEquals(expected, actual);
    }

    /**
     * Given a string s, find the length of the longest substring without repeating characters.
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> slidingWindowChars = new HashSet<>();
        int ans = 0;

        for (int i = 0, j = 0; j < s.length(); ) {
            char c = s.charAt(j);
            if (slidingWindowChars.contains(c)) {
                slidingWindowChars.remove(s.charAt(i));
                i++;
            } else {
                j++;
                slidingWindowChars.add(c);
            }

            ans = Math.max(ans, slidingWindowChars.size());
        }

        return ans;
    }

    @ParameterizedTest
    @MethodSource("containsDuplicateTestData")
    void containsDuplicateTest(int[] nums, boolean expected) {
        boolean actual = containsDuplicate(nums);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> containsDuplicateTestData() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 1}, true),
                Arguments.of(new int[]{1, 2, 3, 4}, false),
                Arguments.of(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}, true)
        );
    }

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> numsSet = new HashSet<>();
        for (int num : nums) {
            if (!numsSet.add(num)) {
                return true;
            }
        }

        return false;
    }

    @ParameterizedTest
    @MethodSource("destCityTestData")
    void destCityTest(List<List<String>> args, String expected) {
        String actual = destCity(args);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> destCityTestData() {
        return Stream.of(
                Arguments.of(List.of(
                        List.of("London", "New York"),
                        List.of("New York", "Lima"),
                        List.of("Lima", "Sao Paulo")
                ), "Sao Paulo"),
                Arguments.of(List.of(
                        List.of("B", "C"),
                        List.of("D", "B"),
                        List.of("C", "A")
                ), "A"),
                Arguments.of(List.of(
                        List.of("A", "Z")
                ), "Z")
        );
    }

    public String destCity(List<List<String>> paths) {
        Map<String, String> fromToPathsMap = new HashMap<>();
        for (List<String> fromToPath : paths) {
            fromToPathsMap.put(fromToPath.get(0), fromToPath.get(1));
        }

        for (String destination : fromToPathsMap.values()) {
            if (!fromToPathsMap.containsKey(destination)) {
                return destination;
            }
        }

        throw new IllegalArgumentException("data is not correct");
    }

    @ParameterizedTest
    @CsvSource(value = {
            "NES:false",
            "NESWW:true",
            "SS:false"
    }, delimiter = ':')
    void isPathCrossingTest(String path, Boolean expected) {
        boolean actual = isPathCrossing(path);
        Assertions.assertEquals(expected, actual);
    }

    public boolean isPathCrossing(String path) {
        Point prewPoint = new Point(0, 0);

        Set<Point> pointSet = new HashSet<>();
        pointSet.add(prewPoint);

        for (int i = 0; i < path.length(); i++) {
            Point point = prewPoint.makeStepReturnNew(path.charAt(i));
            if (!pointSet.add(point)) {
                return true;
            }
            prewPoint = point;
        }
        return false;
    }

    private record Point(Integer x, Integer y) {

        public Point makeStepReturnNew(Character fix) {
            Function<Point, Point> func = switch (fix) {
                case 'N' -> point -> new Point(point.x, point.y + 1);
                case 'S' -> point -> new Point(point.x, point.y - 1);
                case 'W' -> point -> new Point(point.x - 1, point.y);
                case 'E' -> point -> new Point(point.x + 1, point.y);
                default -> throw new IllegalArgumentException("data is not correct");
            };
            return func.apply(this);
        }
    }

    @ParameterizedTest
    @MethodSource("sumOfUniqueTestData")
    void sumOfUniqueTest(int[] nums, int expected) {
        int actual = sumOfUniqueStream(nums);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> sumOfUniqueTestData() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 3, 2}, 4),
                Arguments.of(new int[]{1, 1, 1, 1, 1}, 0),
                Arguments.of(new int[]{1, 2, 3, 4, 5}, 15)
        );
    }

    /**
     * 1748. Sum of Unique Elements.
     * <p>
     * You are given an integer array nums.
     * The unique elements of an array are the elements that appear exactly once in the array.
     * <p>
     * Return the sum of all the unique elements of nums.
     */
    public int sumOfUnique(int[] nums) {
        Map<Integer, Integer> numsCountMap = new HashMap<>();

        for (int num : nums) {
            Integer val = numsCountMap.getOrDefault(num, 0);
            numsCountMap.put(num, val + 1);
        }

        int ans = 0;
        for (Map.Entry<Integer, Integer> entry : numsCountMap.entrySet()) {
            if (entry.getValue() == 1) {
                ans += entry.getKey();
            }
        }

        return ans;
    }

    public int sumOfUniqueStream(int[] nums) {
        return Arrays.stream(nums)
                .boxed()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() == 1)
                .map(Map.Entry::getKey)
                .reduce(0, Integer::sum);
    }

    @ParameterizedTest
    @MethodSource("findLuckyTestData")
    void findLuckyTest(int[] arr, int expected) {
        int actual = findLucky(arr);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> findLuckyTestData() {
        return Stream.of(
                Arguments.of(new int[]{2, 2, 3, 4}, 2),
                Arguments.of(new int[]{1, 2, 2, 3, 3, 3}, 3),
                Arguments.of(new int[]{2, 2, 2, 3, 3}, -1)
        );
    }

    /**
     * 1394. Find Lucky Integer in an Array.
     * Given an array of integers arr, a lucky integer is an integer that has a frequency in the array equal to its value.
     * Return the largest lucky integer in the array. If there is no lucky integer return -1.
     */
    public int findLucky(int[] arr) {
        Map<Integer, Integer> numCountMap = new HashMap<>();

        for (int num : arr) {
            Integer val = numCountMap.getOrDefault(num, 0);
            numCountMap.put(num, val + 1);
        }
        int ans = -1;

        for (Map.Entry<Integer, Integer> entry : numCountMap.entrySet()) {
            if (entry.getValue() == entry.getKey()) {
                ans = Math.max(ans, entry.getKey());
            }
        }

        return ans;
    }

    @ParameterizedTest
    @MethodSource("uniqueOccurrencesTestData")
    void uniqueOccurrencesTest(int[] arr, boolean expected) {
        boolean actual = uniqueOccurrences(arr);
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> uniqueOccurrencesTestData() {
        return Stream.of(
                Arguments.of(new int[]{1, 2, 2, 1, 1, 3}, true),
                Arguments.of(new int[]{1, 2}, false),
                Arguments.of(new int[]{-3, 0, 1, -3, 1, 1, 1, -3, 10, 0}, true)
        );
    }

    /**
     * 1207. Unique Number of Occurrences.
     * <p>
     * Given an array of integers arr, return true if the number of occurrences of each value in the array
     * is unique or false otherwise.
     */
    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> numCountMap = new HashMap<>();
        for (int num : arr) {
            Integer val = numCountMap.getOrDefault(num, 0);
            numCountMap.put(num, val + 1);
        }

        Set<Integer> uniqueCount = new HashSet<>();
        for (Map.Entry<Integer, Integer> entry : numCountMap.entrySet()) {
            if (!uniqueCount.add(entry.getValue())) {
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }
}
