package com.my;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class JavaFeaturesApplication {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        SharedResource sharedResource = new SharedResource();
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Callable<Map<String, Map<Integer, Long>>> callable = () -> {
            List<Integer> integers = new ArrayList<>();
            for (int i = 0; i < 1000000; i++) {
                sharedResource.incCount();
                integers.add(sharedResource.getCount());
            }

            return Map.of(
                    Thread.currentThread().getName(),
                    integers.stream()
                            .sorted(Comparator.naturalOrder())
                            .collect(Collectors.groupingBy(w -> w, Collectors.counting()))
            );
        };

        Future<Map<String, Map<Integer, Long>>> future1 = executorService.submit(callable);
        Future<Map<String, Map<Integer, Long>>> future2 = executorService.submit(callable);

        print(future1.get());
        print(future2.get());

        executorService.shutdown();
    }

    public static void print(Map<String, Map<Integer, Long>> val) {

        System.out.println("________________________________________________________________________________________________________________________");
        val.forEach((key, value) -> {
            System.out.println(key);
            value.forEach((integer, aLong) -> {
                if (aLong > 1) {
                    System.out.println("value " + integer + " called " + aLong + " times");
                }
            });
        });
    }
}
