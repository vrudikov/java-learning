package com.blackhorse.multithreading.semaphor;

public class Semaphore {

    private int value;

    public Semaphore(int init) {
        value = init < 0 ? 0 : init;
    }

    public synchronized void acquireLock() {
        while (value == 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        value--;
    }

    public synchronized void releaseLock() {
        value++;
        notify();
    }
}
