package sn.faty.employeeService.services;

import sn.faty.employeeService.dto.APIResponseDTO;
import sn.faty.employeeService.exceptions.EmployeeNotFoundException;
import sn.faty.employeeService.dto.EmployeeDTO;
import sn.faty.employeeService.entity.Employee;

import java.util.List;

public interface IEmployee{

    EmployeeDTO addEmployee(EmployeeDTO user) ;

    APIResponseDTO getEmployeeById(Long id) throws EmployeeNotFoundException;

    List<EmployeeDTO> getAllEmployees() ;

    EmployeeDTO updateEmployees(Employee user) ;

    void deleteEmployee(Long id) throws EmployeeNotFoundException;

}
