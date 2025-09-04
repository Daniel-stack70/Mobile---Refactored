package com.example.services;

import com.example.entities.Phone;
import com.example.repositories.PhoneRepository;

import java.util.ArrayList;
import java.util.List;

public class PhoneService {

    PhoneRepository pr;

    PhoneService(PhoneRepository pr){
        this.pr = pr;
    }

    public Phone findByModel(String brand, String model){
        return pr.findPhoneByModel(brand, model);
    }

}
