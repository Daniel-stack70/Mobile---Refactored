package com.example.interfaces;

import com.example.entities.Phone;

import java.sql.SQLException;
import java.util.List;

public interface PhoneRepository {

    void add(Phone phone);
    void remove(Phone phone);
    Phone findById(int id);
    List<Phone> getAll();

}
