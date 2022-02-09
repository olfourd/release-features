package com.my.javafeatures.java_8;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;

import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

//some
class FunctionalInterfacesTest {

    @Test
    void predicate() {
        Predicate<String> predicate = str -> StringUtils.contains(str, "a");

        assertThat(predicate.test("aaa"), is(Boolean.TRUE));
        assertThat(predicate.test("bbb"), is(Boolean.FALSE));
    }

    @Test
    void consumer() {
        var firstList = Lists.newArrayList();
        final var someString = "some";

        Consumer<String> addToFirsListConsumer = str -> firstList.add(str);
        addToFirsListConsumer.accept(someString);

        assertThat(firstList, hasSize(1));
        assertThat(firstList, hasItem(someString));

        var secondList = Lists.newArrayList();
        final var helloString = "hello";
        Consumer<String> addToBothListsConsumer = addToFirsListConsumer.andThen(str -> secondList.add(str));
        addToBothListsConsumer.accept(helloString);

        assertThat(firstList, hasSize(2));
        assertThat(firstList, containsInAnyOrder(someString, helloString));
        assertThat(secondList, hasSize(1));
        assertThat(secondList, hasItem(helloString));
    }

    @Test
    void function() {
        Function<Long, String> func = num -> String.format("str_%s", num);
        assertThat(func.apply(5L), is("str_5"));

        Function<TestData, String> composeFunc = func.compose(TestData::getId);
        assertThat(composeFunc.apply(new TestData(10L, "name")), is("str_10"));

        Function<TestData, Long> thenFunc =
                composeFunc.andThen(str -> Long.valueOf(StringUtils.substringAfter(str, "_")));
        assertThat(thenFunc.apply(new TestData(20L, "name")), is(20L));
    }

    @Test
    void supplier() {
        final var str = "str from supplier";
        Supplier<String> supplier = () -> str;

        assertThat(supplier.get(), is(str));
    }

    @Test
    void operator() {
        UnaryOperator<String> unaryOperator = str -> str.concat("_");
        assertThat(unaryOperator.apply("some"), is("some_"));

        BinaryOperator<String> binaryOperator = (str, str2) -> unaryOperator.apply(str).concat(str2);
        assertThat(binaryOperator.apply("some", "one"), is("some_one"));
    }

    @Test
    void customFuncInterface() {
        TestDataCreator<String, Long, TestData> dataCreatorFunc = TestDataCreator::staticCreate;

        final var str = "some";
        final var testData = dataCreatorFunc.create(str);
        assertThat(testData.getId(), nullValue());

        assertThat(testData.getName(), is(str));
        assertThat(dataCreatorFunc.create("").getName(), is(TestDataCreator.DEFAULT_NAME));

        final var withIdData = dataCreatorFunc.createWithId(str, 10L);
        assertThat(withIdData.getId(), is(10L));
        assertThat(withIdData.getName(), is(str));
    }

    @FunctionalInterface
    interface TestDataCreator<T extends String, E extends Long, R extends TestData> {

        String DEFAULT_NAME = "defaultName";

        R create(T name);

        default R createWithId(T name, E id) {
            R result = create(name);
            result.setId(id);
            return result;
        }

        static TestData staticCreate(String name) {
            return TestData.builder()
                    .name(StringUtils.isNoneBlank(name) ? name: DEFAULT_NAME)
                    .build();
        }
    }

    @Builder
    @Data
    private static class TestData {
        private Long id;
        private String name;
    }
}
