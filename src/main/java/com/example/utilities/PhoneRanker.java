package com.example.utilities;

import com.example.entities.Phone;
import com.example.interfaces.RankCalculator;

import static com.example.utilities.Coefficients.*;
import static com.example.utilities.Coefficients.COEFF_5G;
import static com.example.utilities.Coefficients.COEFF_CAMERA;
import static com.example.utilities.Coefficients.COEFF_HAS_ALL;
import static com.example.utilities.Coefficients.COEFF_PRICE;
import static com.example.utilities.Coefficients.COEFF_SCREEN;

public class PhoneRanker implements RankCalculator {
    @Override
    public double getRating(Phone p) {

        double baseScore = (p.getBatteryLife() * COEFF_BATTERY)
                + (p.getStorage() * COEFF_STORAGE)
                + (p.getRam() * COEFF_RAM)
                + (p.getCameraQuality() * COEFF_CAMERA)
                + (p.getScreenSize() * COEFF_SCREEN)
                + (p.is5G() ? COEFF_5G : 0)
                + (p.getPrice() * COEFF_PRICE);

        return baseScore + (p.isCombo() ? COEFF_HAS_ALL : 0);
    }
}
