package com.my;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class MainTests {

    public static void main(String[] args) {
        Map<Age, List<Person>> result = IntStream.range(1, 100)
                .boxed()
                .map(Person::random)
                .sorted(Comparator.comparing(Person::years).thenComparing(Person::id))
                .filter(person -> person.years > 18 && person.years < 80)
                .collect(Collectors.groupingBy(person -> Age.define(person.years)));

        result.forEach((age, people) -> {
            System.out.println(age);
            people.forEach(person -> System.out.println("----" + person));
        });
    }

    public record Person(int id, String name, String surname, int years) {

        public static Person random(int id) {
            return new Person(id, RandomStringUtils.random(5), RandomStringUtils.random(5), RandomUtils.nextInt(1, 100));
        }
    }

    private enum Age {
        UNDERAGE,
        ADULT,
        OLD;

        public static Age define(int years) {
            if (isUnderage(years)) {
                return UNDERAGE;
            } else if (isOld(years)) {
                return OLD;
            } else {
                return ADULT;
            }
        }

        private static boolean isOld(int years) {
            return years >= 55;
        }

        private static boolean isUnderage(int years) {
            return years < 18;
        }
    }
}
