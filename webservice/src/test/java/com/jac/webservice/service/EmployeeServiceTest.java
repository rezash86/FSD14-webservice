package com.jac.webservice.service;

import com.jac.webservice.exception.EmployeeNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @InjectMocks
    EmployeeService underTest;

    @Test
    void test_getById_exists(){
        underTest.initEmployee();
        String testeeId = "1";
        var actual = underTest.getById(testeeId);
        assertNotNull(actual);
    }

    @Test
    void test_getById_Not_exist(){
        underTest.initEmployee();
        String testeeId = "10000";

        EmployeeNotFoundException thrown = assertThrows(
                EmployeeNotFoundException.class,
                () -> underTest.getById(testeeId),
                String.format("No employee found with that employee Id -> " + testeeId)
        );

        assertTrue(thrown.getMessage().contains(testeeId));
    }
}
