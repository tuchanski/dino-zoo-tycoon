package application;

import java.sql.SQLException;

import config.DB;

public class Main {
    public static void main(String[] args) {
        try (var connection = DB.connect()) {
            System.out.println("Connected to the PostgreSQL database.");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}