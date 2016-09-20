package com.blackhorse.multithreading.semaphor;

/**
 * @author vrudi
 */
public class OddThread extends Thread {
    private OddGenerator e;

    public OddThread(OddGenerator e) {
        this.e = e;
    }

    @Override
    public void run() {
        for (int i = 0; i <= 10; i++) {
            System.out.println("result: " + e.next());
        }
    }
}