package com.my.javafeatures.concurent;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;

import java.util.ArrayList;
import java.util.Collection;
import java.util.ConcurrentModificationException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class ConcurrentCollections {

    private static final String ELEMENT_TO_REPLACE = "Concurrent";
    private static final List<String> TEST_DATA = List.of("Java", "Collection", ELEMENT_TO_REPLACE);

    @Test
    @Disabled
    void copyOnWriteArrayList() {
        Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            final var simpleList = new ArrayList<>(TEST_DATA);
            printCollection(true, simpleList);
            printCollection(false, simpleList);
        });

        Assertions.assertDoesNotThrow(() -> {
            final var concurrentArrayList = new CopyOnWriteArrayList<>(TEST_DATA);
            printCollection(true, concurrentArrayList);
            printCollection(false, concurrentArrayList);
        });
    }

    private void printCollection(boolean change, Collection<String> list) {
        final var replacement = "NEW";

        var iterator = list.iterator();
        while (iterator.hasNext()) {
            String element = iterator.next();
            if (change) {
                if (element.equals(ELEMENT_TO_REPLACE)) {
                    list.add(replacement);
                    list.remove(element);
                }
            }
        }
    }

    @Test
    void copyOnWriteArraySer() {
        Assertions.assertThrows(ConcurrentModificationException.class, () -> {
            var dataSet = new HashSet<>(TEST_DATA);
            addElementInIteration(dataSet);
        });

        Assertions.assertDoesNotThrow(() -> {
            var dataSet = new CopyOnWriteArraySet<>(TEST_DATA);
            addElementInIteration(dataSet);
            assertThat(dataSet, hasSize(4));
        });
    }

    private void addElementInIteration(Set<String> dataSet) {
        var counter = dataSet.size();
        for (Iterator<String> i = dataSet.iterator(); i.hasNext(); ) {
            System.out.println(i.next());
            if (--counter == 1) {
                dataSet.add("added in iteration");
            }
        }
    }
}
