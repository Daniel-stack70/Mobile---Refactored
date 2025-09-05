package com.example.repositories;

import com.example.entities.Phone;
import com.example.interfaces.PhoneRepository;
import com.example.interfaces.Validator;
import com.example.validators.PhoneValidator;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlPhoneRepository implements PhoneRepository {

    private final Connection connection;
    private final Validator validator;

    public SqlPhoneRepository(Connection connection) {
        this.connection = connection;
        validator = new PhoneValidator();
    }

    @Override
    public void add(Phone p) throws SQLException {
        if (!validator.allIsValid(p)) return;
        try (PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO phones (brand, model, batteryLife, storage, ram, cameraQuality, screenSize, has5G, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            stmt.setString(1, p.getBrand());
            stmt.setString(2, p.getModel());
            stmt.setInt(3, p.getBatteryLife());
            stmt.setInt(4, p.getStorage());
            stmt.setInt(5, p.getRam());
            stmt.setDouble(6, p.getCameraQuality());
            stmt.setDouble(7, p.getScreenSize());
            stmt.setBoolean(8, p.is5G());
            stmt.setInt(9, p.getPrice());
            stmt.executeUpdate();

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    p.setId(keys.getInt(1));
                }
            }
        }

    }

    @Override
    public void remove(Phone p) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(
                "DELETE FROM phones WHERE id = ?")) {
            stmt.setInt(1, p.getId());
            stmt.executeUpdate();
        }
    }

    @Override
    public Phone findById(int id) throws SQLException {
        String sql = "SELECT id, brand, model, batteryLife, storage, ram, " +
                "cameraQuality, screenSize, has5G, price FROM phones " +
                "WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Phone p = new Phone(
                            rs.getString("brand"),
                            rs.getString("model"),
                            rs.getInt("batteryLife"),
                            rs.getInt("storage"),
                            rs.getInt("ram"),
                            rs.getDouble("cameraQuality"),
                            rs.getDouble("screenSize"),
                            rs.getBoolean("has5G"),
                            rs.getInt("price")
                    );
                    p.setId(rs.getInt("id"));
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public List<Phone> getAll() throws SQLException {
        List<Phone> phones = new ArrayList<>();
        String sql = "SELECT id, brand, model, batteryLife, storage, ram, " +
                "cameraQuality, screenSize, has5G, price FROM phones";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)
        ) {
            while (rs.next()) {
                Phone p = new Phone(
                        rs.getString("brand"),
                        rs.getString("model"),
                        rs.getInt("batteryLife"),
                        rs.getInt("storage"),
                        rs.getInt("ram"),
                        rs.getDouble("cameraQuality"),
                        rs.getDouble("screenSize"),
                        rs.getBoolean("has5G"),
                        rs.getInt("price")
                );
                p.setId(rs.getInt("id"));
                phones.add(p);
            }
        }

        return phones;
    }
}
