package com.my.javafeatures.generic;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

class CreateNewGenericTest {

    @Test
    void createT() {
        Class<String> testClass = String.class;
        TestClass<String> stringTestClass = new TestClass<>(testClass);
        assertThat(stringTestClass.createNewT().getClass(), is(testClass));
    }

    private record TestClass<T>(Class<T> parametrizedClass) {

        @SneakyThrows
        public T createNewT() {
            return parametrizedClass.getDeclaredConstructor().newInstance();
        }
    }

}
