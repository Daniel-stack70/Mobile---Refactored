package com.example.repositories;

import com.example.entities.Phone;
import java.util.ArrayList;
import java.util.List;

public class PhoneRepository {
    List<Phone> phonesList = new ArrayList<>();

    public void addPhone(Phone p){
        phonesList.add(p);
    }

    public void removePhone(Phone p){
        phonesList.remove(p);
    }

    public List<Phone> getPhones() {
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
