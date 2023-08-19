package com.velocity.controller;

import com.velocity.dto.EmployeeDto;
import com.velocity.exception.CustomExceptionEnum;
import com.velocity.response.EmployeeResponse;
import com.velocity.service.EmployeeService;
import com.velocity.utils.ErrorUtil;
import jakarta.validation.Valid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {
    static Logger log = LogManager.getLogger(EmployeeController.class.getName());

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/addEmployee")
    public ResponseEntity<EmployeeResponse> addEmployee(@RequestBody final @Valid EmployeeDto employeeDto,
                                                        final BindingResult result) {
        try {
            if (result.hasErrors()) {
                final EmployeeResponse res = new EmployeeResponse(
                        CustomExceptionEnum.VALIDATION_FAILED.getResponse(ErrorUtil.getErrorList(result)));

                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            log.info("Creating Employee");
            final EmployeeResponse response = employeeService.addEmployee(employeeDto);
            final HttpStatus statusCode = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(response, statusCode);
        } catch (final Exception ex) {
            log.error(ex.getCause());
            return new ResponseEntity<>(new EmployeeResponse(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmployees")
    public ResponseEntity<EmployeeResponse> getEmployees() {
        try {
            log.info("Get All Employees");
            final EmployeeResponse response = employeeService.getEmployee();
            final HttpStatus statusCode = response.getSuccess() ? HttpStatus.OK : HttpStatus.NO_CONTENT;
            return new ResponseEntity<>(response, statusCode);
        } catch (final RuntimeException ex) {
            log.error(ex.getCause());
            return new ResponseEntity<>(new EmployeeResponse(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getEmployeeById/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable final Integer id) {
        try {
            log.info("Get Employee By ID");
            final EmployeeResponse response = employeeService.getEmployeeById(id);
            final HttpStatus statusCode = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(response, statusCode);
        } catch (final Exception ex) {
            log.error(ex.getCause());
            return new ResponseEntity<>(new EmployeeResponse(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateEmployee/{id}")
    public ResponseEntity<EmployeeResponse> updateEmployee(@PathVariable final Integer id, @RequestBody final @Valid EmployeeDto employeeDto,
                                                           final BindingResult result) {
        try {
            if (result.hasErrors()) {
                final EmployeeResponse res = new EmployeeResponse(CustomExceptionEnum.VALIDATION_FAILED
                        .getResponse(ErrorUtil.getErrorList(result)));
                return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
            }
            log.info("Updating employee");
            final EmployeeResponse response = employeeService.updateEmployee(id, employeeDto);
            final HttpStatus statusCode = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(response, statusCode);
        } catch (final Exception ex) {
            log.error(ex.getCause());
            return new ResponseEntity<>(new EmployeeResponse(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deleteEmployee/{id}")
    public ResponseEntity<EmployeeResponse> deleteEmployee(@PathVariable final Integer id) {
        try {
            log.info("Deleting employee");
            final EmployeeResponse response = employeeService.deleteEmployee(id);
            final HttpStatus statusCode = response.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST;
            return new ResponseEntity<>(response, statusCode);
        } catch (final Exception ex) {
            log.error(ex.getCause());
            return new ResponseEntity<>(new EmployeeResponse(ex.getMessage(), false), HttpStatus.BAD_REQUEST);
        }
    }
}
