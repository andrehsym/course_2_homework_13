package pro.sky.java.homeworks.course2.homework_13.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.java.homeworks.course2.homework_13.data.Employee;
import pro.sky.java.homeworks.course2.homework_13.exceptions.EmployeeNotFoundException;
import pro.sky.java.homeworks.course2.homework_13.service.impl.DepartmentServiceImpl;
import pro.sky.java.homeworks.course2.homework_13.service.impl.EmployeeServiceImpl;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @InjectMocks
    private DepartmentServiceImpl out;

    private Employee employee1;
    private Employee employee2;
    private Employee employee3;
    private Employee employee4;
    private Employee employee5;
    private Employee employee6;
    private Set<Employee> actual;
    private Map<Integer, List<Employee>> actualMap;

    @Mock
    private final EmployeeService employeeServiceMock = mock(EmployeeService.class);

    @BeforeEach
    public void start() {
        employee1 = new Employee("Ann", 1, 100_000);
        employee2 = new Employee("Fedor", 2, 200_000);
        employee3 = new Employee("Ivan", 3, 300_000);
        employee4 = new Employee("Daria", 1, 150_000);
        employee5 = new Employee("Petr", 2, 250_000);
        employee6 = new Employee("Oleg", 3, 350_000);
        actual = new HashSet<>();
        actual.add(employee1);
        actual.add(employee2);
        actual.add(employee3);
        actual.add(employee4);
        actual.add(employee5);
        actual.add(employee6);
    }

    @Test
    void getEmployeeMinSalary() {
        when(employeeServiceMock.getEmployees()).thenReturn(actual);

        assertEquals(out.getEmployeeMinSalary(1), employee1);
        assertEquals(out.getEmployeeMinSalary(2), employee2);
        assertEquals(out.getEmployeeMinSalary(3), employee3);
    }
    @Test
    void shouldThrowExceptionWhenGetEmployeeMinSalaryWithNonExistDepartment() {
        when(employeeServiceMock.getEmployees()).thenReturn(actual);

        assertThrows(NoSuchElementException.class, () -> out.getEmployeeMinSalary(-4));
    }

    @Test
    void shouldThrowExceptionWhenGetEmployeeMinSalaryWithNullEmployeeList() {
        when(employeeServiceMock.getEmployees()).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> out.getEmployeeMinSalary(1));
    }

    @Test
    void getEmployeeMaxSalary() {
        when(employeeServiceMock.getEmployees()).thenReturn(actual);

        assertEquals(out.getEmployeeMaxSalary(1), employee4);
        assertEquals(out.getEmployeeMaxSalary(2), employee5);
        assertEquals(out.getEmployeeMaxSalary(3), employee6);
    }

    @Test
    void shouldThrowExceptionWhenGetEmployeeMaxSalaryWithNonExistDepartment() {
        when(employeeServiceMock.getEmployees()).thenReturn(actual);

        assertThrows(NoSuchElementException.class, () -> out.getEmployeeMaxSalary(Integer.MAX_VALUE));
    }

    @Test
    void shouldThrowExceptionWhenGetEmployeeMaxSalaryHasNoEmployeesInDeclaredDepartment() {
        actual.remove(employee1);
        actual.remove(employee4);
        when(employeeServiceMock.getEmployees()).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> out.getEmployeeMinSalary(1));
    }

    @Test
    void getEmployeeFor() {
        when(employeeServiceMock.getEmployees()).thenReturn(actual);
        Set<Employee> actualSetOf1Department = new HashSet<>();
        actualSetOf1Department.add(employee1);
        actualSetOf1Department.add(employee4);
        Set<Employee> actualSetOf2Department = new HashSet<>();
        actualSetOf2Department.add(employee2);
        actualSetOf2Department.add(employee5);
        Set<Employee> actualSetOf3Department = new HashSet<>();
        actualSetOf3Department.add(employee3);
        actualSetOf3Department.add(employee6);

        assertEquals(out.getEmployeeFor(1), actualSetOf1Department);
        assertEquals(out.getEmployeeFor(2), actualSetOf2Department);
        assertEquals(out.getEmployeeFor(3), actualSetOf3Department);
    }

    @Test
    void shouldDontThrowExceptionWhenGetEmployeeForHasNoEmployeesInDeclaredDepartment() {
        when(employeeServiceMock.getEmployees()).thenReturn(actual);
        actual.remove(employee1);
        actual.remove(employee1);
//проверка на то, что исключение не кидается, тк при отсутствии работников в передаваемом в метод отделе выдается по сути пустое множество
        assertDoesNotThrow(() -> out.getEmployeeFor(1));
    }

    @Test
    void getSortedEmployeesByDepartment() {
        when(employeeServiceMock.getEmployees()).thenReturn(actual);
        List<Employee> actualSetOf1Department = new ArrayList<>();
        actualSetOf1Department.add(employee4);
        actualSetOf1Department.add(employee1);
        List<Employee> actualSetOf2Department = new ArrayList<>();
        actualSetOf2Department.add(employee2);
        actualSetOf2Department.add(employee5);
        List<Employee> actualSetOf3Department = new ArrayList<>();
        actualSetOf3Department.add(employee3);
        actualSetOf3Department.add(employee6);
        actualMap = new HashMap<>();
        actualMap.put(1, actualSetOf1Department);
        actualMap.put(2, actualSetOf2Department);
        actualMap.put(3, actualSetOf3Department);

        assertEquals(out.getSortedEmployeesByDepartment(), actualMap);
    }

    @Test
    void shouldThrowExceptionWhenGetSortedEmployeesByDepartmentWithNullEmployeeList() {
        when(employeeServiceMock.getEmployees()).thenThrow(NullPointerException.class);

        assertThrows(NullPointerException.class, () -> out.getSortedEmployeesByDepartment());
    }
}