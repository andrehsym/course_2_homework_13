package pro.sky.java.homeworks.course2.homework_13.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pro.sky.java.homeworks.course2.homework_13.data.Employee;
import pro.sky.java.homeworks.course2.homework_13.exceptions.EmployeeAlreadyExistsException;
import pro.sky.java.homeworks.course2.homework_13.exceptions.EmployeeNotFoundException;
import pro.sky.java.homeworks.course2.homework_13.service.impl.EmployeeServiceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class EmployeeServiceTest {

    private EmployeeServiceImpl out = new EmployeeServiceImpl();

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;

    @BeforeEach
    public void start() {
        employee1 = new Employee("Ann", 1, 100_000);
        employee2 = new Employee("Fedor", 2, 200_000);
        employee3 = new Employee("Ivan", 3, 300_000);
    }

    @Test
    void add() {
        out.add("Ann", 1, 100_000);
        out.add("Fedor", 2, 200_000);
        out.add("Ivan", 3, 300_000);
        Set<Employee> actual = new HashSet<>();
        actual.add(employee1);
        actual.add(employee2);
        actual.add(employee3);

        assertEquals(out.getSetOfEmployees(), actual);
    }

    @Test
    void shouldThrowExceptionWhenAddExistingEmployee() {
        out.add("Ann", 1, 100_000);
        out.add("Fedor", 2, 200_000);
        out.add("Ivan", 3, 300_000);

        assertThrows(EmployeeAlreadyExistsException.class, () -> out.add("Ivan", 3, 300_000));
    }

    @Test
    void remove() {
        out.add("Ann", 1, 100_000);
        out.add("Fedor", 2, 200_000);
        out.add("Ivan", 3, 300_000);

        assertEquals(out.remove("Ann", 1, 100_000), employee1);
    }

    @Test
    void ShouldThrowExceptionWhenRemoveNotFoundEmployee() {
        out.add("Ann", 1, 100_000);
        out.add("Fedor", 2, 200_000);
        out.add("Ivan", 3, 300_000);

        assertThrows(EmployeeNotFoundException.class, () -> out.remove("Vasya", 4, 10));
    }

    @Test
    void find() {
        out.add("Ann", 1, 100_000);
        out.add("Fedor", 2, 200_000);
        out.add("Ivan", 3, 300_000);

        assertEquals(out.find("Ann", 1,100_000), employee1);
        assertEquals(out.find("Fedor", 2, 200_000), employee2);
        assertEquals(out.find("Ivan", 3, 300_000), employee3);
    }

    @Test
    void shouldThrowExceptionWhenFindNonExistentEmployee() {
        out.add("Ann", 1, 100_000);
        out.add("Fedor", 2, 200_000);
        out.add("Ivan", 3, 300_000);

        assertThrows(EmployeeNotFoundException.class, () -> out.find("Vasya", 4, 10));
    }
}