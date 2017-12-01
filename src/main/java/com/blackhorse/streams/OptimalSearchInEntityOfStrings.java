package com.blackhorse.streams;

import com.blackhorse.utils.tuples.Tuple3;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Class shows that streams is optimal for string search within object of strings
 *
 * @author val.rudi
 */
public class OptimalSearchInEntityOfStrings {

    public static void main(String[] args) {
        searchInStream(createStream(), "4");
        searchInStream(createStream(), "16");
        searchInStream(createStream(), "22");
    }

    private static Stream<Tuple3<String, String, String>> createStream() {
        return Stream.of(
                new Tuple3<>("1", "2", "3"),
                new Tuple3<>("4", "5", "6"),
                new Tuple3<>("7", "8", "9"),
                new Tuple3<>("10", "11", "12"),
                new Tuple3<>("13", "14", "15"),
                new Tuple3<>("16", "17", "18"),
                new Tuple3<>("19", "20", "21")
        );
    }

    private static void searchInStream(Stream<Tuple3<String, String, String>> stream, String query) {
        System.out.println("Search started.");
        Optional<String> firstOpt = stream
                .peek(System.out::println)
                .flatMap((tupleOf3) -> Stream.of(
                        tupleOf3._1(),
                        tupleOf3._2(),
                        tupleOf3._3()
                ))
                .peek(System.out::println)
                .filter((str) -> str.equals(query))
                .findFirst();

        System.out.println("Search finished. Result: " + firstOpt);
    }

}
