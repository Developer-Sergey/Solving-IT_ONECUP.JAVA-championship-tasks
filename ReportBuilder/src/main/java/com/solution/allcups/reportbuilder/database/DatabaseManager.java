package com.solution.allcups.reportbuilder.database;

import com.solution.allcups.reportbuilder.exception.NotAcceptableException;
import lombok.Getter;

import java.sql.*;

public class DatabaseManager {
    private final Connection connection;
    @Getter
    private final Statement statement;

    public DatabaseManager(String url, String user, String password) throws NotAcceptableException {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            throw new NotAcceptableException();
        }

        try {
            statement = connection.createStatement();
        } catch (SQLException e) {
            throw new NotAcceptableException();
        }
    }

    public void execute(String sql) throws NotAcceptableException {
        try {
            statement.execute(sql);
        } catch (SQLException e) {
            throw new NotAcceptableException();
        }
    }

    public ResultSet executeQuery(String sql) throws NotAcceptableException {
        try {
            return statement.executeQuery(sql);
        } catch (SQLException e) {
            throw new NotAcceptableException();
        }
    }

    public void checkIfQueryIsCorrect(String sql) throws NotAcceptableException {
        try {
            connection.prepareStatement(sql);
        } catch (SQLException e) {
            throw new NotAcceptableException();
        }
    }

    public void close() throws NotAcceptableException {
        try {
            statement.close();
            connection.close();
        } catch (SQLException e) {
            throw new NotAcceptableException();
        }
    }
}
