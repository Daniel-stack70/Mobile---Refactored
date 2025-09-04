package com.example;

import com.example.entities.Phone;
import com.example.repositories.PhoneRepository;
import com.example.services.PhoneService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MockitoTest {

    @Mock
    private PhoneRepository repo;

    @InjectMocks
    private PhoneService service;

    @Test
    void testFindByModel() {
        Phone phone = new Phone("Samsung", "S10", 5, 128, 8, 12, 24, true, 899);

        when(repo.findPhoneByModel("Samsung", "S10")).thenReturn(phone);

        Phone result = service.findByModel("Samsung", "S10");

        assertEquals("Samsung", result.getBrand());
        assertEquals("S10", result.getModel());

        verify(repo, times(1)).findPhoneByModel("Samsung", "S10");
    }
}
