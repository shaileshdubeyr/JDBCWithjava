package com.jdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class EmloyeepayrollDBService {

    public List<EmployeePayrollData> readData() {
        String sql = "select * from emplyees;";
        List<EmployeePayrollData> emploueePayrollDataList = new ArrayList<>();
        try {
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double salary = resultSet.getDouble("salary");
                LocalDate date = resultSet.getDate("start").toLocalDate();
                System.out.println("id " + id + " \tname " + name + " \t salary " + salary + " \tdate " + date);
                emploueePayrollDataList.add(new EmployeePayrollData(id, name, salary, date));
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emploueePayrollDataList;
    }

    private Connection getConnection() throws SQLException {
        String jdbcURL = "jdbc:mysql://localhost:3306/employee_payroll";
        String userName = "root";
        String password = "shailesh@96";
        Connection connection;
        connection = DriverManager.getConnection(jdbcURL, userName, password);
        System.out.println("THE CONNECTION IS SUCCESFULL" + connection);
        return connection;
    }
}
