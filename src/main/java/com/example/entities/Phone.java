package com.example.entities;

import com.example.interfaces.RankCalculator;
import com.example.utilities.PhoneComparator;
import com.example.utilities.PhoneRanker;

import java.util.Comparator;

public class Phone {
    int id;
    String brand;
    String model;
    int batteryLife;       // hours
    int storage;           // GB
    int ram;               // GB
    double cameraQuality;  // MP
    double screenSize;     // inches
    boolean has5G;
    int price;

    public Phone(String brand,
                 String model,
                 int batteryLife,
                 int storage,
                 int ram,
                 double cameraQuality,
                 double screenSize,
                 boolean is5G,
                 int price
    ) {
        this.brand = brand;
        this.model = model;
        this.batteryLife = batteryLife;
        this.storage = storage;
        this.ram = ram;
        this.cameraQuality = cameraQuality;
        this.screenSize = screenSize;
        this.has5G = is5G;
        this.price = price;
    }

    public boolean isCombo() {
        return has5G && storage >= 128 && ram >= 8;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public int getBatteryLife() {
        return batteryLife;
    }

    public int getStorage() {
        return storage;
    }

    public int getRam() {
        return ram;
    }

    public double getCameraQuality() {
        return cameraQuality;
    }

    public double getScreenSize() {
        return screenSize;
    }

    public boolean is5G() {
        return has5G;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return  "id=" + id +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", batteryLife=" + batteryLife +
                ", storage=" + storage +
                ", ram=" + ram +
                ", cameraQuality=" + cameraQuality +
                ", screenSize=" + screenSize +
                ", has5G=" + has5G +
                ", price=" + price;
    }
}
