package com.blackhorse.multithreading.semaphor;

/**
 * @author vrudi
 */
public class UnsafeOddGenerator implements OddGenerator {
    private int n = 1;

    public int next() {
        ++n;
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            // ignore
        }
        ++n;
        return n;
    }
}