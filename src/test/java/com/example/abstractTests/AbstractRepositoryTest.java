package com.example.abstractTests;

import com.example.entities.Phone;
import com.example.interfaces.PhoneRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractRepositoryTest {

    protected abstract PhoneRepository createRepository() throws Exception;

    private Phone samplePhone() {
        return new Phone("Apple", "iPhone", 20, 128, 6, 12.0, 6.1, true, 1200);
    }

    @Test
    void testAddAndGetAll() throws Exception {
        PhoneRepository repo = createRepository();
        Phone phone = samplePhone();

        repo.add(phone);
        List<Phone> all = repo.getAll();

        assertEquals(1, all.size());
        assertEquals("Apple", all.get(0).getBrand());
        assertEquals("iPhone", all.get(0).getModel());
    }

    @Test
    void testRemove() throws Exception {
        PhoneRepository repo = createRepository();
        Phone phone = samplePhone();

        repo.add(phone);
        List<Phone> all = repo.getAll();
        assertEquals(1, all.size());

        repo.remove(all.get(0));
        List<Phone> afterRemove = repo.getAll();
        assertTrue(afterRemove.isEmpty());
    }

    @Test
    void testFindById() throws Exception {
        PhoneRepository repo = createRepository();
        Phone phone = samplePhone();

        repo.add(phone);
        List<Phone> all = repo.getAll();
        assertEquals(1, all.size());

        int id = all.get(0).getId();
        Phone found = repo.findById(id);

        assertNotNull(found);
        assertEquals("Apple", found.getBrand());
        assertEquals("iPhone", found.getModel());
    }

    @Test
    void testAddMultiplePhones() throws Exception {
        PhoneRepository repo = createRepository();
        Phone p1 = new Phone("Apple", "iPhone", 20, 128, 6, 12.0, 6.1, true, 1200);
        Phone p2 = new Phone("Samsung", "S22", 24, 256, 8, 50.0, 6.7, true, 999);

        repo.add(p1);
        repo.add(p2);

        List<Phone> all = repo.getAll();
        assertEquals(2, all.size());
    }

    @Test
    void testFindByIdNotExisting() throws Exception {
        PhoneRepository repo = createRepository();
        Phone found = repo.findById(999);
        assertNull(found);
    }
}
