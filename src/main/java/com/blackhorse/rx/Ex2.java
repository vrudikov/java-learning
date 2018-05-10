package com.blackhorse.rx;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;

/**
 * @author val.rudi
 */
public class Ex2 {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<String> names = Arrays.asList("Ann", "Vasya", "John", "Bill", "Kat");

        Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(10));

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

        Observable.<String>create(observer -> {
//            names.forEach(observer::onNext);
//            observer.onComplete();

            while (true) {
                observer.onNext(queue.take());
            }
        })
                .flatMap(name -> Observable.just(name).map(RxUtils::sayHello).subscribeOn(scheduler))
                .subscribeOn(scheduler)
                .subscribe(System.out::println);

        names.forEach((n) -> {
            try {
                queue.add(n);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });

        long stopTime = System.currentTimeMillis();
        System.out.println("Method took " + (stopTime - startTime) + " ms");
    }
}
