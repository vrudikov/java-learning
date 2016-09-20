package com.blackhorse.multithreading.semaphor;

/**
 * @author vrudi
 */
public class TestSafeOdds {

    public static void main(String[] args) {
        ThreadSafeOddGenerator e = new ThreadSafeOddGenerator(1);
        OddThread t1 = new OddThread(e);
        OddThread t2 = new OddThread(e);
        t1.start();
        t2.start();
    }

}