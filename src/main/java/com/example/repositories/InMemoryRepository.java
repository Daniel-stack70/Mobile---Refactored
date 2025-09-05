package com.example.repositories;

import com.example.entities.Phone;
import com.example.interfaces.PhoneRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InMemoryRepository implements PhoneRepository {
    List<Phone> phonesList = new ArrayList<>();

    @Override
    public void add(Phone p){
        phonesList.add(p);
    }

    @Override
    public void remove(Phone p){
        phonesList.remove(p);
    }

    @Override
    public Phone findById(int id) throws SQLException {
        for(Phone p : phonesList){
            if(p.getId() == id){
                return p;
            }
        }
        return null;
    }

    @Override
    public List<Phone> getAll() {
        return phonesList;
    }

    public Phone findPhoneByModel(String brand, String model){
        for(Phone p : phonesList){
            if(brand.equals(p.getBrand()) && model.equals(p.getModel()))
                return p;
        }

        return null;
    }
}
