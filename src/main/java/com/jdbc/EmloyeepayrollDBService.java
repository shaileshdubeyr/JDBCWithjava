package com.jdbc;

import java.sql.*;
import java.time.LocalDate;
import java.util.*;

public class EmloyeepayrollDBService {
    private PreparedStatement employeePayrollDataStatement;
    private static EmloyeepayrollDBService employeePayrollDBService;
    EmloyeepayrollDBService(){
    }

    public static EmloyeepayrollDBService getInstance(){
        if (employeePayrollDBService == null)
            employeePayrollDBService = new EmloyeepayrollDBService();
        return employeePayrollDBService;
    }

    public  List<EmployeePayrollData> readData() {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try{
            String sql = "SELECT * FROM emplyees;";
            Connection connection = this.getConnection();
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery(sql);
            employeePayrollList = this.getEmployeePayrollList(result);

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employeePayrollList;
    }
    public List<EmployeePayrollData> getEmployeePayrollData(String name) {
        List<EmployeePayrollData> employeePayrollList = null;
        if (this.employeePayrollDataStatement == null)
            this.prepareStatementForEmployeeData();
        try {
            employeePayrollDataStatement.setString(1, name);
            ResultSet resultSet = employeePayrollDataStatement.executeQuery();
            employeePayrollList = this.getEmployeePayrollData(resultSet);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employeePayrollList;
    }
    private List<EmployeePayrollData> getEmployeePayrollData(ResultSet resultSet) {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try {
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                Double salary = resultSet.getDouble("salary");
                LocalDate date = resultSet.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, name, salary, date));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return employeePayrollList;
    }

    private List<EmployeePayrollData> getEmployeePayrollList(ResultSet result)  {
        List<EmployeePayrollData> employeePayrollList = new ArrayList<>();
        try {
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                double salary = result.getDouble("salary");
                LocalDate start = result.getDate("start").toLocalDate();
                employeePayrollList.add(new EmployeePayrollData(id, name, salary, start));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return employeePayrollList;
    }


    public int updateEmployeeData(String name, double salary) {
        return this.updateEmployeeDataUsingStatement(name, salary);
    }

    private int updateEmployeeDataUsingStatement(String name, double salary) {
        String sql = String.format("update emplyees set salary = %.2f where name = '%s';",salary,name);
        try (Connection connection = this.getConnection()){
            Statement statement = connection.createStatement();
            return statement.executeUpdate(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }

    private void prepareStatementForEmployeeData() {
        try {
            Connection connection = this.getConnection();
            String sql = "SELECT * from emplyees WHERE name = ?";
            employeePayrollDataStatement = connection.prepareStatement(sql);
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public Connection getConnection() {

        String jdbcURL = "jdbc:mysql://localhost:3306/employee_payroll";
        String userName = "root";
        String password = "shailesh@96";
        Connection con = null;
        System.out.println("Connectiong to database" + jdbcURL);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded");
        }
        catch (ClassNotFoundException e){
            throw  new IllegalStateException("Cannot find the driver in the classpath",e);
        }

        try {
            con = DriverManager.getConnection(jdbcURL, userName, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Connection is successfull !!" +con);
        return con;
    }
}
