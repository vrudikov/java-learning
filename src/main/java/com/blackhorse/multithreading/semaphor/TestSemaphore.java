package com.blackhorse.multithreading.semaphor;

public class TestSemaphore {

    public static void main(String[] args) {
        int noThreadsInCriticalSection = 3;

        Semaphore mutex = new Semaphore(noThreadsInCriticalSection);
        for (int i = 0; i <= 10; i++) {
            new MutexThread(mutex, "Thread " + i);
        }
    }

}
