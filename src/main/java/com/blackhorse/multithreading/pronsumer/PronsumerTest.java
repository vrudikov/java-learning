package com.blackhorse.multithreading.pronsumer;

import java.util.Random;
import java.util.Vector;

public class PronsumerTest {

    public static void main(String[] args) {
        Vector<Integer> sQueue = new Vector<Integer>();
        int size = 4;
        Thread producerThread = new Thread(new Producer(sQueue, size), "Producer");
        Thread consumerThread = new Thread(new Consumer(sQueue, size), "Consumer");
        producerThread.start();
        consumerThread.start();
    }

    private static class Producer implements Runnable {
        private final Vector<Integer> sQueue;
        private final int SIZE;


        public Producer(Vector<Integer> sQueue, int size) {
            this.sQueue = sQueue;
            this.SIZE = size;
        }

        public void run() {
            int i = 0;
            while (true) {
                System.out.println("Producer trying to insert item: " + i);
                try {
                    produce(i);
                    Thread.sleep(new Random().nextInt(50));
                    i++;
                } catch (InterruptedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }

        private void produce(int i) throws InterruptedException {
            while (sQueue.size() == SIZE) {
                synchronized (sQueue) {
                    System.out.println("Queue is full " + Thread.currentThread().getName()
                            + " is waiting, size: " + sQueue.size());
                    sQueue.wait();
                }
            }

            synchronized (sQueue) {
                sQueue.add(i);
                sQueue.notifyAll();
            }
        }
    }

    private static class Consumer implements Runnable {
        private final Vector<Integer> sQueue;
        private final int SIZE;

        public Consumer(Vector<Integer> sQueue, int size) {
            this.sQueue = sQueue;
            this.SIZE = size;
        }

        public void run() {
            while (true) {
                try {
                    consume(10);
                    System.out.println("Consuming element: " + consume(10));
                    Thread.sleep(new Random().nextInt(100));
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                }
            }
        }

        private int consume(int t) throws InterruptedException {
            while (sQueue.isEmpty()) {
                synchronized (sQueue) {
                    System.out.println("Queue is empty " + Thread.currentThread().getName()
                            + " is waiting for next element inserted by a producer, size: " + sQueue.size());
                    sQueue.wait();
                }
            }

            synchronized (sQueue) {
                sQueue.notifyAll();
                return sQueue.remove(0);
            }
        }
    }
}