package com.jdbc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class EmployeePayrollServiceTest {
    EmployeePayrollService employeePayrollService;

    @Before
    public void object() {
        employeePayrollService = new EmployeePayrollService();
    }

    @Test
    public void GivenEmployeepayrollInDB_whenRetrive_ShouldMatchEmployeeCount() {
        List<EmployeePayrollData> readEmployeePayroll = employeePayrollService.readEmployeePayRoll();
        Assert.assertEquals(4, readEmployeePayroll.size());
    }

    @Test
    public void givenNewsalaryForEmployee_shaouldWhenUpdated_ShouldSincWithDB() throws SQLException {
        EmployeePayrollService employeePayRollService = new EmployeePayrollService();
        List<EmployeePayrollData> payRollDataList = employeePayRollService.readEmployeePayRoll();
        employeePayRollService.updateEmployeeSalary("terisa", 3000000.00);
        boolean result = employeePayRollService.checkEmployeePayrollInSyncWithDB("terisa");
        Assert.assertTrue(result);
    }

    @Test
    public void givenDateRange_WhenRetrieved_ShouldMatchEmployeeCount() {
        EmployeePayrollService employeePayRollService = new EmployeePayrollService();
        EmloyeepayrollDBService employeePayRollDBService = new EmloyeepayrollDBService();
        employeePayRollService.readEmployeePayroll();
        LocalDate startDate = LocalDate.of(2019, 01, 01);
        LocalDate endDate = LocalDate.now();
        List<EmployeePayrollData> employeePayRollDBData = employeePayRollDBService.readEmployeePayRollForDateRange(startDate, endDate);
        for (EmployeePayrollData data : employeePayRollDBData)
            System.out.println(data);
        Assert.assertEquals(2, employeePayRollDBData.size());
    }
}
