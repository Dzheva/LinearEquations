package org.example;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.*;

public class Repository {

    private static final String URL = "jdbc:postgresql://localhost:5432/equations";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "root";
    private static Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void saveEquation(String equation) {
        try {
            String SQL = "INSERT INTO math_equations (equation) VALUES (?)";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1,equation);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException exception) {
            System.out.println("The equation isn't unique");
        }
    }

    public void saveRoot(String equation, double root) {
        try {
            String SQL = "UPDATE math_equations SET root = ? WHERE equation = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setDouble(1, root);
            preparedStatement.setString(2, equation);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public void getAllEquationsByRoot(double root) {
        try {
            String SQL = "SELECT * FROM math_equations WHERE root = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setDouble(1, root);
            var resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt(1);
                String equation = resultSet.getString(2);
                System.out.printf("%d. %s - %.2f \n", id, equation, root);
            }
            preparedStatement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
