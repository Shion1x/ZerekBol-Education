package org.example.db;

import org.example.config.AppConfig;

import java.sql.Connection;
import java.sql.DriverManager;

public final class Database {
    private Database() {}

    public static Connection getConnection() throws Exception {
        return DriverManager.getConnection(
                AppConfig.get("db.url"),
                AppConfig.get("db.user"),
                AppConfig.get("db.password")
        );
    }
}
