package com.jdbc;

import java.util.List;

public class EmployeePayrollService {

    private List<EmployeePayrollData> employeePayRollList;
    private EmloyeepayrollDBService employeePayrollDBService;

    public  EmployeePayrollService (){
        employeePayrollDBService = EmloyeepayrollDBService.getInstance();
    }

    public EmployeePayrollService(List<EmployeePayrollData> employeePayRollList){
        this();
        this.employeePayRollList = employeePayRollList;
    }

    public List<EmployeePayrollData> readEmployeePayRoll(){
        this.employeePayRollList = new EmloyeepayrollDBService().readData();
        this.employeePayRollList = EmloyeepayrollDBService.getInstance().readData();
        return employeePayRollList;
    }

    public void updateEmployeeSalary(String name, double salary) {
        int result = employeePayrollDBService.updateEmployeeData(name, salary);
        if(result == 0) return;
        EmployeePayrollData employeePayRollData = this.getEmployeePayRollData(name);
        if (employeePayRollData != null)
            employeePayRollData.salary = salary;
    }

    private EmployeePayrollData getEmployeePayRollData(String name) {
        EmployeePayrollData employeePayrollData;
        employeePayrollData = this.employeePayRollList.stream().filter(employeePayrollDataItem -> employeePayrollDataItem.name.equals(name)).findFirst().orElse(null);
        return employeePayrollData;
    }

    public boolean checkEmployeePayrollInSyncWithDB(String name) {
        List<EmployeePayrollData> employeePayrollDataList =employeePayrollDBService.getEmployeePayrollData(name);
        return employeePayrollDataList.get(0).equals(getEmployeePayRollData(name));
    }
}
