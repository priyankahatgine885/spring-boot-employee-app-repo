package com.velocity.service;

import com.velocity.exception.CustomExceptionEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.velocity.dto.EmployeeDto;
import com.velocity.model.Employee;
import com.velocity.repository.EmployeeRepository;
import com.velocity.response.EmployeeResponse;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	static Logger log = LogManager.getLogger(EmployeeServiceImpl.class.getName());
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public EmployeeResponse addEmployee(EmployeeDto employeeDto) {
		try {
			if (Objects.isNull(employeeDto)) {
				return new EmployeeResponse(CustomExceptionEnum.EMPLOYEE_NOT_FOUND_EXCEPTION.getResponse());
			}
			final Employee employee = employeeRepository.save(new Employee(employeeDto));

				final EmployeeResponse response = new EmployeeResponse(new EmployeeDto(employee));
				response.setMessage("Employee created successfully !");
				response.setSuccess(true);
				log.info("Completed add employee method..");
				return response;
		} catch (final Exception ex) {
			log.error(ex.getMessage());
			return new EmployeeResponse(ex.getMessage(), false);
		}
	}

	@Override
	public EmployeeResponse getEmployee() {
		log.info("Running get employees method..");
		try {
			final List<Employee> employeeList = employeeRepository.findAll();
			final EmployeeResponse response = new EmployeeResponse();
			response.setSuccess(true);
			response.setMessage("Get all employees successfully");
			response.setEmployeeList(employeeList.stream().map(EmployeeDto::new).collect(Collectors.toList()));
			log.info("Completed get employees method..");
			return response;
		} catch (final Exception ex) {
			log.error(ex.getMessage());
			return new EmployeeResponse(ex.getMessage(), false);
		}
	}
	@Override
	public EmployeeResponse getEmployeeById(final Integer employeeId) {
		log.info("Running get employee by id method..");
		try {
			if (employeeId <= 0) {
				return new EmployeeResponse(CustomExceptionEnum.EMPLOYEE_ID_NOT_NEGATIVE.getResponse());
			}
			final Employee employee = employeeRepository.getEmployeeById(employeeId);
			if (Objects.isNull(employee)) {
				return new EmployeeResponse(CustomExceptionEnum.EMPLOYEE_NOT_EXIST.getResponse());
			}
			final EmployeeResponse response = new EmployeeResponse(new EmployeeDto(employee));
			response.setSuccess(true);
			log.info("Completed get employee id method..");
			return response;
		} catch (final Exception ex) {
			log.error(ex.getMessage());
			return new EmployeeResponse(ex.getMessage(), false);
		}
	}
	@Override
	public EmployeeResponse updateEmployee(final int id, final EmployeeDto employeeDto) {
		log.info("Running update employee method..");
		try {
			final Employee employee = employeeRepository.getReferenceById(id);
			if (Objects.isNull(employee)) {
				return new EmployeeResponse(CustomExceptionEnum.EMPLOYEE_NOT_EXIST.getResponse());
			}
			employee.setFirstName(employeeDto.getFirstName());
			employee.setLastName(employeeDto.getLastName());
			employee.setEmail(employeeDto.getEmail());
			employee.setPanNumber(employeeDto.getPanNumber());
			employee.setPhoneNumber(employeeDto.getPhoneNumber());
			employeeRepository.save(employee);
			final EmployeeResponse response = new EmployeeResponse();
			response.setSuccess(true);
			response.setMessage("Employee updated successfully");
			response.setEmployeeDto(new EmployeeDto(employee));
			log.info("Completed update employee method..");
			return response;
		} catch (final Exception ex) {
			log.error(ex.getMessage());
			return new EmployeeResponse(ex.getMessage(), false);
		}
	}
	@Override
	public EmployeeResponse deleteEmployee(final int id) {
		log.info("Running delete employee method..");
		try {
			final Employee entity = employeeRepository.getOne(id);
			employeeRepository.delete(entity);
			final EmployeeResponse response = new EmployeeResponse();
			response.setSuccess(true);
			response.setMessage("Employee deleted successfully");
			log.info("Completed delete employee method..");
			return response;
		} catch (final Exception ex) {
			log.error(ex.getMessage());
			return new EmployeeResponse(ex.getMessage(), false);
		}
	}


}
