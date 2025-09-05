package com.example;

import com.example.entities.Phone;
import com.example.interfaces.PhoneRepository;
import com.example.services.PhoneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceTests {

    @Mock
    private PhoneRepository repo;

    @InjectMocks
    private PhoneService service;

    @Test
    void testAddPhone() throws SQLException {
        Phone phone = new Phone("Apple", "iPhone", 20, 128, 6, 12.0, 6.1, true, 1200);

        service.addPhone(phone);

        verify(repo, times(1)).add(phone);
    }

    @Test
    void testRemovePhone() throws SQLException {
        Phone phone = new Phone("Samsung", "Note", 18, 256, 8, 48.0, 6.8, true, 999);

        service.removePhone(phone);

        verify(repo, times(1)).remove(phone);
    }

    @Test
    void testGetPhones() throws SQLException {
        List<Phone> phones = List.of(
                new Phone("Samsung", "S10", 5, 128, 8, 12, 24, true, 899),
                new Phone("Apple", "iPhone 14", 20, 256, 6, 12, 6.1, true, 1200)
        );

        when(repo.getAll()).thenReturn(phones);

        List<Phone> result = service.getPhones();

        assertEquals(2, result.size());
        assertEquals("Samsung", result.get(0).getBrand());
        verify(repo, times(1)).getAll();
    }

    @Test
    void testGetValueForMoneyWithPrice() {
        Phone p = new Phone("BrandA", "M1", 10, 64, 4, 12, 6.0, false, 400);

        double result = service.getValueForMoney(p);

        assertTrue(result > 0);
    }

    @Test
    void testGetValueForMoneyWithZeroPrice() {
        Phone p = new Phone("BrandA", "M1", 10, 64, 4, 12, 6.0, false, 0);

        double result = service.getValueForMoney(p);

        assertTrue(result > 0);
    }

    @Test
    void testCompare() {
        Phone p1 = new Phone("BrandA", "M1", 10, 64, 4, 12, 6.0, false, 300);
        Phone p2 = new Phone("BrandB", "M2", 15, 128, 6, 24, 6.5, true, 600);

        int result = service.compare(p1, p2);

        assertNotEquals(0, result);
    }
}
