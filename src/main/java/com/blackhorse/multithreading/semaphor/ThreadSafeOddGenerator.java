package com.blackhorse.multithreading.semaphor;

/**
 * @author vrudi
 */
public class ThreadSafeOddGenerator implements OddGenerator {
    private int n = 1;
    private Semaphore mutex;

    public ThreadSafeOddGenerator(int initial) {
        this.mutex = new Semaphore(1);
        this.n = initial;
    }

    public int next() {
        mutex.acquireLock();

        ++n;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // ignore
        }
        ++n;

        mutex.releaseLock();

        return n;
    }
}