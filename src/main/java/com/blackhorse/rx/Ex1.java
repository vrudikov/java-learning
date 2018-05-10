package com.blackhorse.rx;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.List;

/**
 * @author val.rudi
 */
public class Ex1 {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        List<String> names = Arrays.asList("Ann", "Vasya", "John", "Bill", "Kat");

        Observable.<String>create(observer -> {
            names.forEach(observer::onNext);
            observer.onComplete();
        })
                .map(RxUtils::sayHello)
                .subscribe(System.out::println);

        long stopTime = System.currentTimeMillis();
        System.out.println("Method took " + (stopTime - startTime) + " ms");
    }
}
