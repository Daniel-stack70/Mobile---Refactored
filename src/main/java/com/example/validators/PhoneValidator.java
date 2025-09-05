package com.example.validators;

import com.example.entities.Phone;
import com.example.interfaces.Validator;

public class PhoneValidator implements Validator {

    @Override
    public boolean brandIsValid(Phone p) {
        String s = p.getBrand();
        return s != null && !s.isBlank();
    }

    @Override
    public boolean modelIsValid(Phone p) {
        String s = p.getModel();
        if(s != null && !s.isBlank())
            return true;
        System.out.println("Model invalid");
        return false;
    }

    @Override
    public boolean priceIsValid(Phone p) {
        return p.getPrice() > 0;
    }

    @Override
    public boolean ramIsValid(Phone p) {
        return (p.getRam() % 2) == 0;
    }

    @Override
    public boolean storageIsValid(Phone p) {
        int n = p.getStorage();
        return n > 0 && (n & (n - 1)) == 0;
    }

    @Override
    public boolean allIsValid(Phone p) {
        return brandIsValid(p)
                && modelIsValid(p)
                && priceIsValid(p)
                && ramIsValid(p)
                && storageIsValid(p);
    }
}
