package sn.faty.employeeService.Controlller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sn.faty.employeeService.dto.APIResponseDTO;
import sn.faty.employeeService.dto.EmployeeDTO;
import sn.faty.employeeService.exceptions.EmployeeNotFoundException;
import sn.faty.employeeService.services.IEmployee;

import java.util.List;

@RequestMapping("employee")
@RestController
public class EmployeeController {

    private IEmployee iEmployee ;

    @Autowired
    public EmployeeController(IEmployee iEmployee) {
        this.iEmployee = iEmployee;
    }

    @PostMapping("add/")
    public ResponseEntity <EmployeeDTO> addEmployee(@RequestBody EmployeeDTO employeeDTO){

        return  ResponseEntity.ok().body(iEmployee.addEmployee(employeeDTO));

    }

    @GetMapping("getAll/")
    public ResponseEntity <List<EmployeeDTO>> getAllEmployee(){

        return ResponseEntity.ok().body(iEmployee.getAllEmployees());

    }

    @GetMapping("getEmployee/{id}")
    public ResponseEntity <APIResponseDTO> getEmployeeById(@PathVariable ("id") Long id) throws EmployeeNotFoundException {

        return ResponseEntity.ok().body(iEmployee.getEmployeeById(id));

    }

}
