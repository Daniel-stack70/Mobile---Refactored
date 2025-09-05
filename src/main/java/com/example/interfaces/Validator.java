package com.example.interfaces;

import com.example.entities.Phone;

public interface Validator {

    boolean brandIsValid(Phone p);
    boolean modelIsValid(Phone p);
    boolean priceIsValid(Phone p);
    boolean ramIsValid(Phone p);
    boolean storageIsValid(Phone p);

    boolean allIsValid(Phone p);
}
