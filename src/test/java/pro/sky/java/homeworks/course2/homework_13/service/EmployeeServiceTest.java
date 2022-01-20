package pro.sky.java.homeworks.course2.homework_13.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pro.sky.java.homeworks.course2.homework_13.data.Employee;
import pro.sky.java.homeworks.course2.homework_13.exceptions.EmployeeAlreadyExistsException;
import pro.sky.java.homeworks.course2.homework_13.exceptions.EmployeeNotFoundException;
import pro.sky.java.homeworks.course2.homework_13.service.impl.EmployeeServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceTest {

    private EmployeeServiceImpl out;
    private Set<Employee> employees = new HashSet<>();
//    private Set<Employee> employees;
    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;


    public EmployeeServiceTest(EmployeeServiceImpl out) {
        this.out = out;
    }

    @BeforeEach
    public void start() {
        employee1 = new Employee("Ann", 1, 100_000);
        employee2 = new Employee("Fedor", 2, 200_000);
        employee3 = new Employee("Ivan", 3, 300_000);
    }

    @Test
    void addExistingEmployee() {
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        Assertions.assertThrows(EmployeeAlreadyExistsException.class, () -> out.add("Ivan", 3, 300_000));
//        Assertions.assertThrows(EmployeeAlreadyExistsException.class, () -> out.addFullEmployee(employee1));
    }

    @Test
    void add() {
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        Set<Employee> actual = new HashSet<>();

        actual.add(employee1);
        actual.add(employee2);
        actual.add(employee3);

        Assertions.assertEquals(employees, actual);
    }

    @Test
    void removeNotFoundEmployee() {
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.remove("Vasya", 4, 10));
    }

    @Test
    void remove() {
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        employees.remove(employee1);

        Set<Employee> actual = new HashSet<>();

        actual.add(employee2);
        actual.add(employee3);

        Assertions.assertEquals(employees, actual);
    }

    @Test
    void find() {
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        Set<Employee> actual = new HashSet<>();

        actual.add(employee1);
        actual.add(employee2);
        actual.add(employee3);

        Assertions.assertEquals(employees.contains(employee1), actual.contains(employee1));
        Assertions.assertEquals(employees.contains(employee2), actual.contains(employee2));
        Assertions.assertEquals(employees.contains(employee3), actual.contains(employee3));
    }

    @Test
    void findNonExistentEmployee() {
        employees.add(employee1);
        employees.add(employee2);
        employees.add(employee3);

        Assertions.assertThrows(EmployeeNotFoundException.class, () -> out.add("Ivan", 3, 300_000));
    }
}