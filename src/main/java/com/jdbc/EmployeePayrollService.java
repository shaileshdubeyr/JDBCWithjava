package com.jdbc;

import java.util.List;

public class EmployeePayrollService {

    public List<EmployeePayrollData> readEmployeePayroll() {
        List<EmployeePayrollData> employeePayRollList;
        employeePayRollList = new EmloyeepayrollDBService().readData();
        return employeePayRollList;
    }
}
