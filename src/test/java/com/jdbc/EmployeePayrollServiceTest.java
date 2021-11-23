package com.jdbc;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class EmployeePayrollServiceTest {
    @Test
    public void GivenEmployeepayrollInDB_whenRetrive_ShouldMatchEmployeeCount(){
        EmployeePayrollService employeePayrollService = new EmployeePayrollService();
            List<EmployeePayrollData> readEmployeePayroll = employeePayrollService.readEmployeePayroll();
            Assert.assertEquals(4,readEmployeePayroll.size());


    }
}
