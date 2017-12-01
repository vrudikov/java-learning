package com.blackhorse.streams;

import com.blackhorse.utils.tuples.Tuple3;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Class shows that streams is optimal for string search within object of strings
 *
 * @author val.rudi
 */
public class OptimalSearchOfStringInEntityOfStrings {

    public static void main(String[] args) {
        searchInStream(createStream(), "5");
        searchInStream(createStream(), "16");
        searchInStream(createStream(), "22");
    }

    private static Stream<Tuple3<String, String, String>> createStream() {
        return Stream.of(
                new Tuple3<>("1", "2", "3"),
                new Tuple3<>(null, "5", null),
                new Tuple3<>("7", null, "9"),
                new Tuple3<>(null, "11", null),
                new Tuple3<>("13", null, "15"),
                new Tuple3<>("16", null, null),
                new Tuple3<>("19", "20", "21")
        );
    }

    private static void searchInStream(Stream<Tuple3<String, String, String>> stream, String query) {
        System.out.println("Search started.");
        Optional<String> firstOpt = stream
                .peek(System.out::println)
                .map((tupleOf3) -> Stream.of(tupleOf3._1(), tupleOf3._2(), tupleOf3._3())
                        .filter(query::equals)
                        .findFirst()
                )
                .peek(System.out::println)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();

        System.out.println("Search finished. Result: " + firstOpt);
    }

}
