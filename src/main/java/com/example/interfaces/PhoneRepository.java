package com.example.interfaces;

import com.example.entities.Phone;

import java.sql.SQLException;
import java.util.List;

public interface PhoneRepository {

    void add(Phone phone) throws SQLException;
    void remove(Phone phone) throws SQLException;
    Phone findById(int id) throws SQLException;
    List<Phone> getAll() throws SQLException;

}
