package com.my;

public class SharedResource {

    private int count = 0;
    private final Object lock = new Object();

    public void incCount() {
        synchronized (lock) {
            this.count++;
        }
    }

    public int getCount() {
        synchronized (lock) {
            return this.count;
        }
    }
}
