package com.blackhorse.rx;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * @author val.rudi
 */
public class Ex3 {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();

        Scheduler scheduler = Schedulers.from(Executors.newFixedThreadPool(10));

        Observable.just(1, 2, 3, 4, 5)
                .flatMap(id -> Observable.merge(
                        Observable.just(id).map(Ex3::getAge),
                        Observable.just(id).map(Ex3::getName)
                ))
//                .groupBy(IdObject::getId)
//                .flatMap(data -> data.take(2))
                .map(IdObject::getId)
                .subscribe(System.out::println, System.err::println);


//        Observable.just(1, 2, 3, 4, 5)
//                .flatMap(id -> Observable.merge(
//                        Observable.just(id).map(Ex3::getAge).subscribeOn(scheduler),
//                        Observable.just(id).map(Ex3::getName).subscribeOn(scheduler)
//                ))
//                .groupBy(IdObject::getId)
//                .flatMap(data -> {
//                    System.out.println(data);
//                    return data.take(2).collect(Collector::new, (collector, idObject) -> collector.append(idObject)).toObservable();
//                })
//                .map(Collector::getMessage)
//                .subscribeOn(scheduler)
//                .subscribe(System.out::println, System.err::println);

        long stopTime = System.currentTimeMillis();
        System.out.println("Method took " + (stopTime - startTime) + " ms");
    }

    private static void sleep() {
        try {
            long sleepTime = Math.round(Math.random() * 3000);
            System.out.println("Gonna sleep for " + sleepTime + " ms");
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static NameResponse getName(long id) {
        sleep();
        return new NameResponse(id, names.get(id));
    }

    private static AgeResponse getAge(long id) {
        sleep();
        return new AgeResponse(id, ages.get(id));
    }

    private static class NameResponse implements IdObject, DataProvider {
        long id;
        String name;

        NameResponse(long id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public long getId() {
            return id;
        }

        @Override
        public Object getData() {
            return name;
        }

        @Override
        public String getDataType() {
            return "name";
        }
    }

    private static class AgeResponse implements IdObject, DataProvider {
        long id;
        Integer age;

        AgeResponse(long id, Integer age) {
            this.id = id;
            this.age = age;
        }

        @Override
        public long getId() {
            return id;
        }

        @Override
        public Object getData() {
            return age;
        }

        @Override
        public String getDataType() {
            return "age";
        }
    }

    private static final Map<Long, String> names = new HashMap<>();
    private static final Map<Long, Integer> ages = new HashMap<>();

    static {
        names.put(1L, "Vasya");
        names.put(2L, "Archibald");
        names.put(3L, "Glasha");
        names.put(4L, "June");
        names.put(5L, "Senior");

        ages.put(1L, 40);
        ages.put(2L, 36);
        ages.put(3L, 43);
        ages.put(4L, 21);
        ages.put(5L, 23);
    }

    public static class Collector {
        private int age;
        private String name;

        public void append(DataProvider dataProvider) {
            if ("name".equals(dataProvider.getDataType())) {
                name = (String) dataProvider.getData();
            } else if ("age".equals(dataProvider.getDataType())) {
                age = (int) dataProvider.getData();
            }
        }

        String getMessage() {
            return "Hi, " + name + ", your age: " + age + "!";
        }
    }

    interface IdObject {
        long getId();
    }

    interface DataProvider {
        Object getData();

        String getDataType();
    }

}
