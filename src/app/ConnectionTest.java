package app;

import java.sql.Connection;

import util.DBConnection;

public class ConnectionTest {

    public static void main(String[] args) {

        Connection connection = DBConnection.getConnection();

        if (connection != null) {
            System.out.println("Connection Test Passed.");
        } else {
            System.out.println("Connection Test Failed.");
        }
    }
}