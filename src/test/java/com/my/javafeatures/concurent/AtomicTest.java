package com.my.javafeatures.concurent;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.Test;

public class AtomicTest {

    @Test
    void test() {
        final var sequenceGenerator = new SequenceGenerator();

        ArrayList<Sequence> sequences = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            sequences.add(new Sequence(i, 3, sequenceGenerator));
        }

        // W8 for threads completing
        int completedThreadCounter = 0;
        do {
            for (Sequence sequence : sequences) {
                if (!sequence.thread.isAlive()) {
                    sequence.printSequence();
                    completedThreadCounter++;
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ignored) {
            }
        } while (completedThreadCounter < sequences.size());
    }

    private final class Sequence implements Runnable {
        Thread thread;
        int id;
        int count;
        SequenceGenerator sequenceGenerator;
        List<BigInteger> sequence;
        boolean printed;

        public Sequence(int id, int count, SequenceGenerator sequenceGenerator) {

            this.thread = new Thread(this);
            this.id = id;
            this.count = count;
            this.sequenceGenerator = sequenceGenerator;
            this.sequence = new ArrayList<>();
            this.printed = false;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < count; i++) {
                    sequence.add(sequenceGenerator.next());
                    Thread.sleep((long) ((Math.random() * 2 + 1) * 30));
                }
            } catch (InterruptedException e) {
                System.out.printf("Thread %s interrupted%n", id);
            }
            System.out.printf("Thread %s completed%n", id);
            printSequence();
        }

        private void printSequence() {
            if (printed) {
                return;
            }
            System.out.println(String.format("Thread id={%s} sequence: [%s]", id, sequence.toString()));
            printed = true;
        }
    }

    private static final class SequenceGenerator {
        private final AtomicReference<BigInteger> value;

        public SequenceGenerator() {
            this.value = new AtomicReference<>(BigInteger.ONE);
        }

        //optimistic block pattern
        public BigInteger next() {
            BigInteger newValue;
            BigInteger next;

            do {
                newValue = value.get();
                next = newValue.multiply(BigInteger.TWO);
            } while (!value.compareAndSet(newValue, next));

            return newValue;
        }
    }

}


