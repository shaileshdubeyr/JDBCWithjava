package com.jdbc;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class EmployeePayrollServiceTest {
    EmployeePayrollService employeePayrollService;
    @Before
     public void object(){
        employeePayrollService=  new EmployeePayrollService();
    }

    @Test
    public void GivenEmployeepayrollInDB_whenRetrive_ShouldMatchEmployeeCount(){
            List<EmployeePayrollData> readEmployeePayroll = employeePayrollService.readEmployeePayRoll();
            Assert.assertEquals(4,readEmployeePayroll.size());
    }

   @Test
    public void givenNewsalaryForEmployee_shaouldWhenUpdated_ShouldSincWithDB() throws SQLException {
       EmployeePayrollService employeePayRollService = new EmployeePayrollService();
       List<EmployeePayrollData> payRollDataList = employeePayRollService.readEmployeePayRoll();
       employeePayRollService.updateEmployeeSalary("terisa",3000000.00);
       boolean result = employeePayRollService.checkEmployeePayrollInSyncWithDB("terisa");
       Assert.assertTrue(result);
    }
}
