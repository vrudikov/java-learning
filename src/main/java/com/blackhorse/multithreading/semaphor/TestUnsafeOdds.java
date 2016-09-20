package com.blackhorse.multithreading.semaphor;

/**
 * @author vrudi
 */
public class TestUnsafeOdds {

    public static void main(String[] args) {
        UnsafeOddGenerator e = new UnsafeOddGenerator();
        OddThread t1 = new OddThread(e);
        OddThread t2 = new OddThread(e);
        t1.start();
        t2.start();
    }

}