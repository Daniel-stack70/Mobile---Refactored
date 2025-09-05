package com.example.services;

import com.example.entities.Phone;
import com.example.interfaces.PhoneRepository;
import com.example.interfaces.RankCalculator;
import com.example.interfaces.Service;
import com.example.repositories.InMemoryRepository;
import com.example.utilities.PhoneComparator;
import com.example.utilities.PhoneRanker;

import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class PhoneService implements Service {

    PhoneRepository pr;
    private final Comparator<Phone> phoneComparator;
    private final RankCalculator rankCalculator;


    PhoneService(PhoneRepository pr){
        this.pr = pr;
        this.phoneComparator = new PhoneComparator();
        this.rankCalculator = new PhoneRanker();
    }

    public void addPhone(Phone phone) throws SQLException {
        pr.add(phone);
    }

    public void removePhone(Phone phone) throws SQLException {
        pr.remove(phone);
    }

    public List<Phone> getPhones() throws SQLException {
        return pr.getAll();
    }

    public int compare(Phone p1, Phone p2) {
        return phoneComparator.compare(p1, p2);
    }

    public double getValueForMoney(Phone p) {
        if (p.getPrice() <= 0) return rankCalculator.getRating(p);
        return rankCalculator.getRating(p) / p.getPrice();
    }

}
