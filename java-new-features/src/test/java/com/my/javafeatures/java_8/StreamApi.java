package com.my.javafeatures.java_8;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.containsInRelativeOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

class StreamApi {

    @Test
    void filter() {
        final var testData = generateData();

        Predicate<Long> idPredicate = aLong -> aLong % 2 == 0;
        testData.stream()
                .filter(data -> idPredicate.test(data.getId()))
                .forEach(data -> assertThat(idPredicate.test(data.getId()), is(Boolean.TRUE)));

        Predicate<String> namePredicate = str -> StringUtils.split(str, "_")[1].length() > 1;
        testData.stream()
                .filter(data -> namePredicate.test(data.getName()))
                .forEach(data -> assertThat(data.getId() >= 10, is(Boolean.TRUE)));
    }

    @Test
    void map() {
        final var testData = generateData();
        //since 16
        final var multiMapResult = testData.stream()
                .<Pair<String, Set<String>>>mapMulti((data, consumer) ->
                        consumer.accept(new ImmutablePair<>(data.getName(), data.getParams().keySet())))
                .collect(Collectors.toList());
        final var mapResult = testData.stream()
                .map(data -> new ImmutablePair<>(data.getName(), data.getParams().keySet()))
                .collect(Collectors.toList());

        assertEquals(multiMapResult, mapResult);
    }

    @Test
    void flatMap() {
        final var testData = generateData();

        final var keySets = testData.stream()
                .map(TestData::getParams)
                .map(Map::keySet)
                .collect(Collectors.toList());
        final var flatMapResult = keySets.stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        keySets.forEach(keySet -> assertThat(flatMapResult.containsAll(keySet), is(Boolean.TRUE)));
    }

    @Test
    void distinct() {
        final var elementData = "some";
        final var elementData1 = "some1";
        final var testData = List.of(elementData, elementData, elementData1, elementData1);

        final var distinctResult = testData.stream()
                .distinct()
                .toList();

        assertThat(distinctResult, hasSize(2));
        assertThat(distinctResult, contains(elementData, elementData1));
    }

    @Test
    void sorted_limit() {
        final var testData = generateData();

        final var reversedDataIds = testData.stream()
                .map(TestData::getId)
                .sorted(Collections.reverseOrder())
                .toList();

        var expectedIdList = new ArrayList<>();
        for (long i = reversedDataIds.size(); i > 0; i--) {
            expectedIdList.add(i);
        }

        assertThat(reversedDataIds, containsInRelativeOrder(expectedIdList.toArray()));
    }

    @Test
    void limit_skip() {
        final var testData = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        final var limit = 5;
        final var limitedTestData = testData.stream()
                .limit(limit)
                .toList();
        assertThat(limitedTestData, hasSize(limit));
        assertThat(limitedTestData, containsInRelativeOrder(1, 2, 3, 4, 5));

        final var skip = 3;
        final var skippedLimitedTestData = limitedTestData.stream()
                .skip(skip)
                .toList();
        assertThat(skippedLimitedTestData, hasSize(limitedTestData.size() - skip));
        assertThat(skippedLimitedTestData, containsInRelativeOrder(4, 5));
    }

    @Test
    void peek() {
        generateData().stream()
                .peek(data -> data.setName(String.valueOf(data.getId())))
                .forEach(data -> assertThat(data.getName(), is(String.valueOf(data.getId()))));
    }

    @Test
    void takeAndDropWhile() {
        final var testData = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final var limit = 5;

        //Since: 9
        List<Integer> takeWhileResult = testData.stream()
                .takeWhile(el -> el <= limit)
                .toList();
        assertThat(takeWhileResult, containsInRelativeOrder(1, 2, 3, 4, 5));

        //Since: 9
        List<Integer> dropWhileResult = testData.stream()
                .dropWhile(el -> el <= limit)
                .toList();
        assertThat(dropWhileResult, containsInRelativeOrder(6, 7, 8, 9, 10));
    }

    @Test
    void reduce() {
        final var testData = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        final var expectedIntMultiply = 3_628_800;
        final var expectedIntSum = 55;
        final var expectedStringSum = "12345678910";

        assertThat(testData.stream().reduce(Math::multiplyExact).orElseThrow(), is(expectedIntMultiply));
        assertThat(testData.stream().reduce(0, Math::addExact), is(expectedIntSum));

        assertThat(testData.stream().reduce("", (identity, num) -> identity + num, String::concat),
                is(expectedStringSum));
    }

    @Test
    void min_max_count() {
        final var testData = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assertThat(testData.stream().min(Comparator.naturalOrder()).get(), is(1));
        assertThat(testData.stream().max(Comparator.naturalOrder()).get(), is(10));
        assertThat(testData.stream().count(), is((long) testData.size()));
    }

    @Test
    void collect() {
        final var testData = List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);

        //since 16
        assertThat(testData.stream().toList(), containsInRelativeOrder(testData.toArray()));
        assertThat(testData.stream().collect(Collectors.toList()), containsInRelativeOrder(testData.toArray()));
        assertThat(testData.stream().collect(Lists::newArrayList, ArrayList::add, ArrayList::addAll),
                containsInRelativeOrder(testData.toArray()));

        assertThat(testData.stream().collect(Collectors.toSet()), containsInRelativeOrder(1, 2, 3, 4, 5));
        assertThat(testData.stream().collect(Collectors.toCollection(HashSet::new)),
                containsInRelativeOrder(1, 2, 3, 4, 5));

        Map<Integer, String> collectedMap = testData.stream()
                .distinct()
                .collect(Collectors.toMap(num -> num, String::valueOf));
        assertThat(collectedMap.keySet(), contains(1, 2, 3, 4, 5));
        assertThat(collectedMap.values(), contains("1", "2", "3", "4", "5"));
    }

    @Test
    void match() {
        final var testData = List.of("some", "hello");

        assertThat(testData.stream().anyMatch(str -> StringUtils.contains(str, "some")), is(Boolean.TRUE));

        assertThat(testData.stream().allMatch(str -> StringUtils.contains(str, "some")), is(Boolean.FALSE));
        assertThat(testData.stream().allMatch(str -> str instanceof String), is(Boolean.TRUE));

        assertThat(testData.stream().noneMatch(str -> StringUtils.contains(str, "frog")), is(Boolean.TRUE));
    }

    @Test
    void find() {
        final var testData = List.of(1, 1, 2, 2, 3, 3, 4, 4, 5, 5);

        assertThat(testData.stream().findFirst().get(), is(1));
        assertThat(testData, hasItem(testData.stream().findAny().get()));
    }

    private List<TestData> generateData() {
        long size = new Random().nextLong(10, 20);

        ArrayList<TestData> testData = new ArrayList<>();

        for (int i = 1; i < size; i++) {
            testData.add(
                    TestData.builder()
                            .id((long) i)
                            .name(String.format("name_%s", i))
                            .params(Map.of(
                                    String.format("key%s", i), String.format("value%s", i),
                                    String.format("key%s", i + 1), String.format("value%s", i + 1))
                            ).build()
            );
        }
        return testData;
    }

    @AllArgsConstructor
    @Builder
    @Data
    private static class TestData {
        private Long id;
        private String name;
        private Map<String, String> params;
    }

}
