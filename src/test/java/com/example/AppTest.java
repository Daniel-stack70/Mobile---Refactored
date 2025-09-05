package com.example;

import com.example.entities.Phone;
import com.example.interfaces.RankCalculator;
import com.example.utilities.PhoneComparator;
import com.example.utilities.PhoneRanker;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;


public class AppTest
{

    private final Comparator<Phone> phoneComparator = new PhoneComparator();
    private final RankCalculator rankCalculator = new PhoneRanker();


    @Test
    public void testAssertAllCorrectSpecsInsertion() {
        Phone phone = new Phone("Generic", "gen", 5, 64, 4, 4, 4, false, 100);

        assertAll("Valid phone",
            () -> assertEquals("Generic", phone.getBrand()),
            () -> assertEquals("gen", phone.getModel()),
            () -> assertEquals(4, phone.getScreenSize(), 0.001),
            () -> assertEquals(64, phone.getStorage()),
            () -> assertEquals(4, phone.getRam()),
            () -> assertEquals(4, phone.getCameraQuality(), 0.001),
            () -> assertEquals(5, phone.getBatteryLife()),
            () -> assertFalse(phone.is5G())
        );

    }

    @ParameterizedTest
    @MethodSource("com.example.argumentProviders.ComparisonProvider#phoneComparisonProvider")
    public void testIsBetter(Phone p1, Phone p2, int expected) {
        assertEquals(expected, phoneComparator.compare(p1, p2));
    }
}
