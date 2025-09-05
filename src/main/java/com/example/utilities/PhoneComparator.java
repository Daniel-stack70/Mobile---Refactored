package com.example.utilities;

import com.example.entities.Phone;
import com.example.interfaces.RankCalculator;

import java.util.Comparator;

public class PhoneComparator implements Comparator<Phone> {

    private final RankCalculator rankCalculator;

    public PhoneComparator(){
        rankCalculator = new PhoneRanker();
    }

    @Override
    public int compare(Phone o1, Phone o2) {
        return Double.compare(rankCalculator.getRating(o1), rankCalculator.getRating(o2));
    }
}
