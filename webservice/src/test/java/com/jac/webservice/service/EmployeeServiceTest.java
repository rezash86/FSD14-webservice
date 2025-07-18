package com.jac.webservice.service;

import com.jac.webservice.exception.EmployeeNotFoundException;
import com.jac.webservice.model.Employee;
import com.jac.webservice.repository.employee.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService underTest;

    @Mock
    EmployeeRepository repository;

    @Test
    void test_getById_exists(){
        String testeeId = "1";
        when(repository.getById(testeeId)).thenReturn(Employee.builder().build());
        var actual = underTest.getById(testeeId);
        assertNotNull(actual);
    }

    @Test
    void test_getById_Not_exist(){
        String testeeId = "10000";

        EmployeeNotFoundException thrown = assertThrows(
                EmployeeNotFoundException.class,
                () -> underTest.getById(testeeId),
                String.format("No employee found with that employee Id -> " + testeeId)
        );

        assertTrue(thrown.getMessage().contains(testeeId));
    }
}
