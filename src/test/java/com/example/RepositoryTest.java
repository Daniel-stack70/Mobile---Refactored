package com.example;

import com.example.abstractTests.AbstractRepositoryTest;
import com.example.interfaces.PhoneRepository;
import com.example.repositories.InMemoryRepository;
import com.example.repositories.SqlPhoneRepository;
import org.junit.jupiter.api.Nested;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RepositoryTest {
    @Nested
    class InMemoryRepositoryTest extends AbstractRepositoryTest {
        @Override
        protected PhoneRepository createRepository() {
            return new InMemoryRepository();
        }
    }

    @Nested
    class SqlAbstractRepositoryTest extends AbstractRepositoryTest {
        @Override
        protected PhoneRepository createRepository() throws Exception {
            Connection conn = DriverManager.getConnection("jdbc:sqlite::memory:");
            try (Statement stmt = conn.createStatement()) {
                stmt.execute("CREATE TABLE phones (" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "brand TEXT, " +
                        "model TEXT, " +
                        "batteryLife INTEGER, " +
                        "storage INTEGER, " +
                        "ram INTEGER, " +
                        "cameraQuality REAL, " +
                        "screenSize REAL, " +
                        "has5G BOOLEAN, " +
                        "price INTEGER" +
                        ")");
            }
            return new SqlPhoneRepository(conn);
        }
    }
}
