package com.example.argumentProviders;

import com.example.entities.Phone;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class ComparisonProvider {
    static Stream<Arguments> phoneComparisonProvider() {
        return Stream.of(
                // p1 better than p2
                org.junit.jupiter.params.provider.Arguments.of(
                        new Phone("Galaxy", "S10", 999, 999, 999, 999, 999, true, 1),
                        new Phone("Generic", "gen", 1, 1, 1, 1, 1, false, Integer.MAX_VALUE),
                        1
                ),
                // p2 better than p1
                org.junit.jupiter.params.provider.Arguments.of(
                        new Phone("Cheap", "C1", 1, 1, 1, 1, 1, false, 999),
                        new Phone("Flagship", "F1", 500, 500, 500, 500, 500, true, 10),
                        -1
                ),
                // p1 equal to p2
                org.junit.jupiter.params.provider.Arguments.of(
                        new Phone("Same", "S", 5, 5, 5, 5, 5, false, 100),
                        new Phone("Same", "S", 5, 5, 5, 5, 5, false, 100),
                        0
                )
        );
    }
}
