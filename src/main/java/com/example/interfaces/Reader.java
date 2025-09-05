package com.example.interfaces;

import com.example.entities.Phone;

import java.util.List;

public interface Reader {
    public List<Phone> read(String path);
}
