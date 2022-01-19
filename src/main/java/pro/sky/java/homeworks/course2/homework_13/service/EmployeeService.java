package pro.sky.java.homeworks.course2.homework_13.service;


import pro.sky.java.homeworks.course2.homework_13.data.Employee;

import java.util.Set;

public interface EmployeeService {

    Employee add(String fullName, int department, int salary);

    Employee remove(String fullName, int department, int salary);

    Employee find(String fullName, int department, int salary);

//    Employee findByName(String fullName);

    Set<Employee> printEmployeeList();

    Set<Employee> getEmployees();

}
