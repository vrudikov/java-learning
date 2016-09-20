package com.blackhorse.multithreading.deadlock;

/**
 * @author vrudi
 */
public class Deadlock {

    public static void main(String[] args) {
        final Object r1 = "Resource1";
        final Object r2 = "Resource2";

        Thread thr1 = new Thread() {
            @Override
            public void run() {
                synchronized (r1) {
                    System.out.println("Thread 1: Acuired and Locked resource 1");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Thread 1: Trying to acquire lock on r2");

                    synchronized (r2) {
                        System.out.println("Thread 1: Acuired and Locked resource 2");
                    }
                }
            }
        };
        Thread thr2 = new Thread() {
            @Override
            public void run() {
                synchronized (r2) {
                    System.out.println("Thread 2: Acuired and Locked resource 2");

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    System.out.println("Thread 2: Trying to acquire lock on r1");

                    synchronized (r1) {
                        System.out.println("Thread 2: Acuired and Locked resource 1");
                    }
                }
            }
        };

        thr1.start();
        thr2.start();
    }

    public static byte[] encode(String pText, String pKey) {
        byte[] txt = pText.getBytes();
        byte[] key = pKey.getBytes();
        byte[] res = new byte[pText.length()];

        for (int i = 0; i < txt.length; i++) {
            res[i] = (byte) (txt[i] ^ key[i % key.length]);
        }

        return res;
    }

    public static String decode(byte[] pText, String pKey) {
        byte[] res = new byte[pText.length];
        byte[] key = pKey.getBytes();

        for (int i = 0; i < pText.length; i++) {
            res[i] = (byte) (pText[i] ^ key[i % key.length]);
        }

        return new String(res);
    }
}