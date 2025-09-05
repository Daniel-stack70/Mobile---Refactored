package com.example.readers;

import com.example.App;
import com.example.entities.Phone;
import com.example.interfaces.Reader;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReader implements Reader {
    @Override
    public List<Phone> read(String path) {
        List<Phone> phoneList = new ArrayList<>();

        try (InputStream in = App.class.getResourceAsStream(path)) {
            assert in != null;
            try (CSVReader reader = new CSVReader(new InputStreamReader(in))) {

                String[] line;
                reader.readNext();
                while ((line = reader.readNext()) != null) {
                    Phone p = new Phone(
                            line[0],                          // brand (String)
                            line[1],                          // model (String)
                            Integer.parseInt(line[2]),        // batteryLife (int)
                            Integer.parseInt(line[3]),        // storage (int)
                            Integer.parseInt(line[4]),        // ram (int)
                            Double.parseDouble(line[5]),      // cameraQuality (double)
                            Double.parseDouble(line[6]),      // screenSize (double)
                            Boolean.parseBoolean(line[7]),    // is5G (boolean)
                            Integer.parseInt(line[8])         // price (int)
                    );

                    phoneList.add(p);
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e.getMessage());
        }

        return phoneList;
    }
}
