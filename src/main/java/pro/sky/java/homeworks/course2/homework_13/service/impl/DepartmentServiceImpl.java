package pro.sky.java.homeworks.course2.homework_13.service.impl;

import org.springframework.stereotype.Service;
import pro.sky.java.homeworks.course2.homework_13.data.Employee;
import pro.sky.java.homeworks.course2.homework_13.service.DepartmentService;
import pro.sky.java.homeworks.course2.homework_13.service.EmployeeService;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getEmployeeMinSalary(int department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
        .min(Comparator.comparingInt(Employee::getSalary))
                .get();
    }

    @Override
    public Employee getEmployeeMaxSalary(int department) {
        return employeeService.getEmployees().stream()
                .filter(e -> e.getDepartment() == department)
                .max(Comparator.comparingInt(Employee::getSalary))
                .get();
    }

    @Override
    public Collection<Employee> getEmployeeFor(int department) {
        return employeeService.getEmployees().stream()
                .filter(employee -> employee.getDepartment() == department)
                .collect(Collectors.toSet());
    }

    @Override
    public Map<Integer, List<Employee>> getSortedEmployeesByDepartment() {
        return employeeService.getEmployees().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

}
