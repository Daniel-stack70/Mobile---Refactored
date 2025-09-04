package com.example.entities;


import static com.example.entities.Coefficients.*;

public class Phone {
    String brand;
    String model;
    int batteryLife;       // hours
    int storage;           // GB
    int ram;               // GB
    double cameraQuality;  // MP
    double screenSize;     // inches
    boolean has5G;
    int price;

    public Phone(String brand, String model, int batteryLife, int storage, int ram, double cameraQuality, double screenSize, boolean is5G, int price) {
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

    public double getPhoneRating() {
        double baseScore = (batteryLife * COEFF_BATTERY)
                + (storage * COEFF_STORAGE)
                + (ram * COEFF_RAM)
                + (cameraQuality * COEFF_CAMERA)
                + (screenSize * COEFF_SCREEN)
                + (has5G ? COEFF_5G : 0)
                + (price * COEFF_PRICE);

        return baseScore + (isCombo() ? COEFF_HAS_ALL : 0);
    }

    public int compareTo(Phone other) {
        return Double.compare(this.getPhoneRating(), other.getPhoneRating());
    }

    public double getValueForMoney() {
        if (price <= 0) return getPhoneRating();
        return getPhoneRating() / price;
    }

    private boolean isCombo() {
        return has5G && storage >= 128 && ram >= 8;
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
        return brand + " " + model + " -> Rating: " + getPhoneRating()
                + ", Value for Money: " + getValueForMoney();
    }
}
