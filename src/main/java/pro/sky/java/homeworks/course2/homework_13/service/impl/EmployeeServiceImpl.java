package pro.sky.java.homeworks.course2.homework_13.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import pro.sky.java.homeworks.course2.homework_13.data.Employee;
import pro.sky.java.homeworks.course2.homework_13.exceptions.BadRequestException;
import pro.sky.java.homeworks.course2.homework_13.exceptions.EmployeeAlreadyExistsException;
import pro.sky.java.homeworks.course2.homework_13.exceptions.EmployeeNotFoundException;
import pro.sky.java.homeworks.course2.homework_13.service.EmployeeService;

import java.util.HashSet;
import java.util.Set;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    Set<Employee> employees = new HashSet<>();

    @Override
    public Employee add(String fullName, int department, int salary) {
        Employee newbie = new Employee(fullName, department, salary);
        if (!StringUtils.isAlpha(fullName)) {
            throw new BadRequestException();
        } else if (employees.contains(newbie)) {
            throw new EmployeeAlreadyExistsException();
        }
//        if (employees.contains(newbie)) {
//            throw new EmployeeAlreadyExistsException();
//        }
            employees.add(newbie);
            return newbie;
    }

//    @Override
//    public Employee addFullEmployee(Employee employee) {
//        return add(employee.getFullName(), employee.getDepartment(), employee.getSalary());
//    }


    @Override
    public Employee remove(String fullName, int department, int salary) {
        Employee removed = new Employee(fullName, department, salary);
        if (!StringUtils.isAlpha(fullName)) {
            throw new BadRequestException();
        } else if (employees.contains(removed)) {
            employees.remove(removed);
            return removed;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String fullName, int department, int salary) {
        Employee find = new Employee(fullName, department, salary);
        if (!StringUtils.isAlpha(fullName)) {
            throw new BadRequestException();
        } else if (employees.contains(find)) {
            return find;
        }
        throw new EmployeeNotFoundException();
    }

//    @Override
//    public Employee findByName(String fullName) {
//        Employee findByName = new Employee(fullName, 0, 0);
//        if (!StringUtils.isAlpha(fullName)) {
//            throw new BadRequestException();
//        } else if (employees.contains(fullName)) {
//
//            employees.remove(removed);
//            return removed;
//        }
//        throw new EmployeeNotFoundException();
//    }


    @Override
    public Set<Employee> printEmployeeList() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        return employees;
    }

    @Override
    public Set<Employee> getEmployees() {
        for (Employee employee : employees) {
            System.out.println(employee);
        }
        return employees;
    }

    @Override
    public Set<Employee> getSetOfEmployees() {
        return employees;
    }
}
