package com.velocity.service;

import com.velocity.dto.EmployeeDto;
import com.velocity.response.EmployeeResponse;

public interface EmployeeService {
    EmployeeResponse addEmployee(EmployeeDto employeeDto);

    EmployeeResponse getEmployee();
    EmployeeResponse getEmployeeById(Integer employeeId);
    EmployeeResponse updateEmployee(final int id, final EmployeeDto employeeDto) ;
    EmployeeResponse deleteEmployee(int id);

}
