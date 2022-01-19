package pro.sky.java.homeworks.course2.homework_13.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pro.sky.java.homeworks.course2.homework_13.data.Employee;
import pro.sky.java.homeworks.course2.homework_13.service.DepartmentService;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;


    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/min-salary")
    public Employee getEmployeeMinSalary(@RequestParam("departmentId") int department) {
        return departmentService.getEmployeeMinSalary(department);
    }

    @GetMapping("/max-salary")
    public Employee getEmployeeMaxSalary(@RequestParam("departmentId") int department) {
        return departmentService.getEmployeeMaxSalary(department);
    }

    @GetMapping("/all")
    public Map<Integer, List<Employee>> getSortedEmployees() {
        return departmentService.getSortedEmployeesByDepartment();
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    Collection<Employee> getEmployeesFor(@RequestParam(name = "departmentId") int department) {
        return departmentService.getEmployeeFor(department);
    }

}
