package com.jac.webservice.controller;

import com.jac.webservice.dto.Address;
import com.jac.webservice.dto.Employee;
import com.jac.webservice.exception.EmployeeNotFoundException;
import com.jac.webservice.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toCollection;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.springframework.http.HttpStatus.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    @InjectMocks
    private EmployeeController underTest;

    @Mock
    private EmployeeService service;

    ArrayList<Employee> employeeList = Stream.of(
            new Employee("1", "A",
                                 new Address("Montreal", "AAAAA")),
            new Employee("2", "B", new Address("Mon", "BBBBB")),
            Employee.builder().employeeId("3")
                        .name("C").address(Address.builder()
                                .city("Tor").postalCode("GGGGGG").build()).build()
        ).collect(toCollection(ArrayList::new));

    @Test
    void test_getAll_returnsEmployess(){

        //expectation
        when(service.getAll()).thenReturn(employeeList);
        var actual = underTest.getAll();

        //assert
        assertNotNull(actual.getBody());
        assertEquals(employeeList, actual.getBody());
        assertEquals(OK, actual.getStatusCode());
    }

    @Test
    void test_getById_givesOneEmployee(){
        String testeeId= "emp1";
        Employee expectedEmployee = new Employee("emp1", "A",
                new Address("Montreal", "AAAAA"));
        //expectation
        when(service.getById(testeeId)).thenReturn(expectedEmployee);

        var actual = underTest.getById(testeeId);

        //assert
        assertEquals(OK, actual.getStatusCode());
        assertEquals(expectedEmployee, actual.getBody());
    }

    @Test
    void test_getById_not_found(){
        String testeeId= "non_existent";
        String expectedBody= String.format("No employee found with that employee Id -> " + testeeId);
        //expectation
        when(service.getById(testeeId)).thenThrow(new EmployeeNotFoundException(expectedBody));

        var actual = underTest.getById(testeeId);

        //assert
        assertEquals(NOT_FOUND, actual.getStatusCode());
        assertEquals(expectedBody, actual.getBody());
    }

    @Test
    void test_getById_otherexception_internalerrorhappens(){
        String testeeId= "non_existent";
        String expectedBody= "Call to adminstrator";
        //expectation
        when(service.getById(testeeId)).thenThrow(new IllegalArgumentException());

        var actual = underTest.getById(testeeId);

        //assert
        assertEquals(INTERNAL_SERVER_ERROR, actual.getStatusCode());
        assertEquals(expectedBody, actual.getBody());
    }

}
