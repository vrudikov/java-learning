package com.blackhorse.multithreading.semaphor;

import com.blackhorse.multithreading.pronsumer.PronsumerTest;

public class MutexThread extends Thread {
    private Semaphore mutex;

    public MutexThread(Semaphore mutex, String name) {
        super(name);
        this.mutex = mutex;
        start();
    }

    public void run() {
        while (true) {
            mutex.acquireLock();
            System.out.println("Enter critical section: " + getName());

            try {
                sleep((int)(Math.random() + 1000));
            } catch (InterruptedException e) {

            }

            System.out.println("Leave critical section: " + getName());
            mutex.releaseLock();
        }
    }

    @Override
    public String toString() {
        return getName();
    }
}
