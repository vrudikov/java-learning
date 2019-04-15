package com.blackhorse.multithreading.cf;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutureDepsTask implements Runnable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println(new CompletableFutureDepsTask().process());
        Thread.sleep(5000);
    }

    private String process() {
        log("checks before");
        CompletableFuture.runAsync(this);
        log("after");
        return "processing";
    }

    @Override
    public void run() {
        log("LOCKED! Running something async and cool...");

        String pollResult = null;
        try {
            pollResult = CompletableFuture.supplyAsync(() -> {
                log("Polling for result...");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log("Finished polling");

                if (true) {
                    throw new IllegalStateException("polling error");
                }

                return "Some Result";
            }).exceptionally(throwable -> {
                log("poller. got error: " + throwable.getMessage());
                return null;
            }).get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        log("UNLOCKED! Got " + pollResult);

//            throw new IllegalStateException("error");

//        CompletableFuture.runAsync(() -> {
//
//        }).exceptionally(throwable -> {
//            log("got error: " + throwable.getMessage());
//            return null;
//        });
    }

    private static void log(String msg) {
        System.out.println(Thread.currentThread().getName() + ": " + msg);
    }
}
