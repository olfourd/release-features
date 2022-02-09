package com.my.javafeatures.concurent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

class ConcurrentTests {

    @SneakyThrows
    @Test
    void threadLocalTest() {
        List<Integer> testData = List.of(10, 20, 30, 40);

        Calculator calculator = new Calculator();
        List<Callable<Integer>> callableList = new ArrayList<>();
        for (int i = 0; i < 100000; i++) {
            Callable<Integer> callable = () -> {
                testData.forEach(calculator::add);
                return calculator.getCurrentSum();
            };
            callableList.add(callable);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        List<Future<Integer>> futureList = callableList.stream()
                .map(executorService::submit)
                .collect(Collectors.toList());

        while (futureList.stream().allMatch(Future::isDone)) {
            wait(10);
        }

        executorService.shutdown();

        for (Future<Integer> integerFuture : futureList) {
            System.out.println(integerFuture.get());
        }

        List<Integer> result = new ArrayList<>();
        for (Future<Integer> future : futureList) {
            result.add(future.get());
        }

        result.forEach(integer -> {
            assertThat(integer, is(testData.stream().reduce(0, Integer::sum)));
        });
    }

    private static final class Calculator {
        private final ThreadLocal<List<Integer>> storage = new ThreadLocal<>();

        private void add(int num) {
            log(new Throwable().getStackTrace()[0].getMethodName() + " " + num);
            List<Integer> integers = storage.get();
            if (CollectionUtils.isEmpty(integers)) {
                ArrayList<Integer> newList = new ArrayList<>();
                storage.set(newList);
            }
            storage.get().add(num);
        }

        private Integer getCurrentSum() {
            log(null);
            List<Integer> integers = storage.get();
            Integer integer = integers
                    .stream()
                    .reduce(0, Integer::sum);
            integers.clear();
            return integer;
        }

        private void log(String info) {
            String message = info != null
                    ? Thread.currentThread().getName() + " " + info + " "
                    : Thread.currentThread().getName();
            System.out.println(message);
        }
    }

    @SneakyThrows
    @Test
    void synchronizedTest() {
        List<String> dataTest = List.of("one", "two", "three", "four", "five");
        int executableCount = 100000;

        RoundRobin robin = new RoundRobin(dataTest);
        List<Callable<String>> callableList = new ArrayList<>();
        for (int i = 0; i < executableCount; i++) {
            Callable<String> callable = () -> {
                String result = robin.get();
                System.out.println(Thread.currentThread().getName() + " " + " " + result);
                return result;
            };
            callableList.add(callable);
        }

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        List<Future<String>> futures = callableList.stream()
                .map(executorService::submit)
                .collect(Collectors.toList());
        while (futures.stream().allMatch(Future::isDone)) {
            wait(10);
        }

        List<String> result = new ArrayList<>();
        for (Future<String> future : futures) {
            result.add(future.get());
        }
        Map<String, Long> resultMap = new HashMap<>();

        result.forEach(val -> {
            Long count = resultMap.get(val);
            if (count == null) {
                resultMap.put(val, 1L);
            } else {
                resultMap.put(val, resultMap.get(val) + 1);
            }
        });

        System.out.println(resultMap);
        executorService.shutdown();

        resultMap.forEach((s, aLong) -> {
            assertThat(aLong, is((long) (executableCount / dataTest.size())));
        });
    }

    private static class RoundRobin {
        private final List<String> strings;
        private Iterator<String> iterator;

        public RoundRobin(List<String> strings) {
            this.strings = new ArrayList<>(strings);
            iterator = strings.iterator();
        }

        public synchronized String get() {
            if (!iterator.hasNext()) {
                iterator = strings.iterator();
            }
            return iterator.next();
        }
    }
}
