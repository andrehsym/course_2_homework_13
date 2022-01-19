package pro.sky.java.homeworks.course2.homework_13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.homeworks.course2.homework_13.data.Employee;
import pro.sky.java.homeworks.course2.homework_13.service.EmployeeService;


import java.util.Set;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")
    public Employee add(@RequestParam("fullname") String fullName, @RequestParam ("departmentId") int department,
                        @RequestParam ("salary") int salary) {
        Employee newbie = employeeService.add(fullName, department, salary);
        return newbie;
    }

    @GetMapping("/remove")
    public Employee remove(@RequestParam("fullname") String fullName, @RequestParam ("departmentId") int department,
                           @RequestParam ("salary") int salary) {
        Employee removed = employeeService.remove(fullName, department, salary);
        return removed;
    }

    @GetMapping("/find")
    public Employee find(@RequestParam("fullname") String fullName, @RequestParam ("departmentId") int department,
                       @RequestParam ("salary") int salary) {
        Employee find = employeeService.find(fullName, department, salary);
        return find;
    }

    @GetMapping("/printlist")
    public Set<Employee> print() {
        return employeeService.printEmployeeList();
    }
}
