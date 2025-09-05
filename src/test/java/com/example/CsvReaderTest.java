package com.example;

import com.example.entities.Phone;
import com.example.readers.CsvReader;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvReaderTest {

    @Test
    void testReadValidCsv() {
        CsvReader reader = new CsvReader();

        List<Phone> phones = reader.read("/phoneTEST.csv");

        assertEquals(2, phones.size());

        Phone first = phones.get(0);
        assertEquals("Apple", first.getBrand());
        assertEquals("Edge18", first.getModel());
        assertEquals(23, first.getBatteryLife());
        assertEquals(256, first.getStorage());
        assertEquals(4, first.getRam());
        assertEquals(55.7, first.getCameraQuality());
        assertEquals(6.27, first.getScreenSize());
        assertFalse(first.is5G());
        assertEquals(1659, first.getPrice());

        Phone second = phones.get(1);
        assertEquals("Samsung", second.getBrand());
        assertEquals("Ultra15", second.getModel());
    }

    @Test
    void testReadNonExistingFileThrows() {
        CsvReader reader = new CsvReader();

        assertThrows(AssertionError.class,
                () -> reader.read("/non_existing.csv"));
    }
}
