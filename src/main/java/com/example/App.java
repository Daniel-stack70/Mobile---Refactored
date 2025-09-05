package com.example;

import com.example.entities.Phone;
import com.example.interfaces.PhoneRepository;
import com.example.interfaces.Reader;
import com.example.readers.CsvReader;
import com.example.repositories.SqlPhoneRepository;

import java.sql.*;
import java.util.List;

public class App 
{
    public static void main( String[] args ) throws SQLException {
        String url = "jdbc:sqlite:mydb.db";
        String file = "/phones.csv";
        PhoneRepository repo;

        Reader reader = new CsvReader();

        List<Phone> phones = reader.read(file);

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                createTable(conn);

                repo = new SqlPhoneRepository(conn);
                phones.forEach(p -> {
                    try {
                        repo.add(p);
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                repo.getAll().forEach(System.out::println);
            }
        }
    }

    static void createTable(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement();

        stmt.execute("DROP TABLE IF EXISTS phones");
        stmt.execute(
                "CREATE TABLE phones (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                        "brand TEXT, " +
                        "model TEXT, " +
                        "batteryLife INTEGER, " +
                        "storage INTEGER, " +
                        "ram INTEGER, " +
                        "cameraQuality REAL, " +
                        "screenSize REAL, " +
                        "has5G BOOLEAN, " +
                        "price INTEGER)"
        );
    }
}
