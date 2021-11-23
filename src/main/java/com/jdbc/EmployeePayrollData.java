package com.jdbc;

import java.time.LocalDate;
import java.util.Objects;

public class EmployeePayrollData {
    public Integer id;
    public String name;
    public Double salary;
    public LocalDate date;

    public EmployeePayrollData(Integer id, String name, Double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    public EmployeePayrollData(Integer id, String name, Double salary, LocalDate date) {
        this(id, name, salary);
        this.date = date;
    }

    @Override
    public String toString() {
        return "EmployeePayrollData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EmployeePayrollData)) return false;
        EmployeePayrollData that = (EmployeePayrollData) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(salary, that.salary) && Objects.equals(date, that.date);
    }
}

