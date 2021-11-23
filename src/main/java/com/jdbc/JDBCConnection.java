package com.jdbc;

import java.sql.*;
import java.util.Enumeration;

public class JDBCConnection {

    public static void main(String[] args) throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/employee_payroll";
        String userName = "root";
        String password = "shailesh@96";
        Connection connection = null;
        String sql = "select * from emplyees";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
            listDrivers();
            connection = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection is successfull " + connection);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath", e);
        } catch (Exception e) {
            System.out.println("the connection not built");
        }
        PreparedStatement statement = connection.prepareStatement(sql);
        ResultSet resultSet= statement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            Double salary = resultSet.getDouble("salary");
            Date date = resultSet.getDate("start");
            System.out.println(id + "\t\t" + name
                    + "\t\t" + salary+"\t\t"+date);
        }
        connection.close();
    }

    private static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = (Driver) driverList.nextElement();
            System.out.println(" " + driverClass.getClass().getName());
        }
    }
}
