package com.example;

import com.example.entities.Phone;
import org.junit.jupiter.api.Test;  // ✅ JUnit 5 Test
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;  // ✅ JUnit 5 assertions



public class AppTest 
{
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

        Phone phone2 = new Phone(null, null, 0, 0, 0, 0, 0, false, 0);
        assertAll("Null phone",
            () -> assertNull(phone2.getBrand()),
            () -> assertNull(phone2.getModel()),
            () -> assertEquals(0, phone2.getScreenSize(), 0.001),
            () -> assertEquals(0, phone2.getStorage()),
            () -> assertEquals(0, phone2.getRam()),
            () -> assertEquals(0, phone2.getCameraQuality(), 0.001),
            () -> assertEquals(0, phone2.getBatteryLife()),
            () -> assertFalse(phone2.is5G())
        );
    }

    @ParameterizedTest
    @MethodSource("com.example.argumentProviders.ComparisonProvider#phoneComparisonProvider")
    public void testIsBetter(Phone p1, Phone p2, int expected) {
        assertEquals(expected, p1.compareTo(p2));
    }
}
