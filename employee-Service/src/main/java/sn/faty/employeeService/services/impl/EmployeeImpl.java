package sn.faty.employeeService.services.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import sn.faty.employeeService.dto.APIResponseDTO;
import sn.faty.employeeService.dto.DepartementDTO;
import sn.faty.employeeService.exceptions.EmployeeNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sn.faty.employeeService.dto.EmployeeDTO;
import sn.faty.employeeService.entity.Employee;
import sn.faty.employeeService.repository.EmployeeRepository;
import sn.faty.employeeService.services.IEmployee;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class EmployeeImpl implements IEmployee {

    private EmployeeRepository employeeRepository ;
    private ModelMapper modelMapper ;
    private RestTemplate restTemplate ;
    private  WebClient webClient ;

    @Autowired
    public EmployeeImpl(EmployeeRepository employeeRepository, ModelMapper modelMapper,
                        RestTemplate restTemplate, WebClient webClient) {
        this.employeeRepository = employeeRepository;
        this.modelMapper= modelMapper ;
        this.restTemplate=restTemplate ;
        this.webClient=webClient ;
    }

    @Override
    public EmployeeDTO addEmployee(EmployeeDTO departementDTO) {

        Employee departement= modelMapper.map(departementDTO,Employee.class) ;

        Employee deprtmentSaved= employeeRepository.save(departement);

        EmployeeDTO departmentDTO= modelMapper.map(deprtmentSaved, EmployeeDTO.class);

        return departmentDTO;
    }

    @Override
    public APIResponseDTO getEmployeeById(Long id) {

        Employee employee= employeeRepository.findById(id).get() ;

        //recuperation de lemployee qui a dailleurrs son code

        // on utilise le code du departement pour recuperer l'objet departement

//       ResponseEntity<DepartementDTO> responseEntity= restTemplate.getForEntity("http://localhost:8081/api/departement/getDepartementByCode/"+
//
//                 employee.getDepartementCode(),DepartementDTO.class);
//
//       DepartementDTO departementDTO= responseEntity.getBody() ;
        
         EmployeeDTO employeeDTO= new EmployeeDTO(
                 employee.getId(),
                 employee.getFirstName(),
                 employee.getLastName(),
                 employee.getEmail(),
                 employee.getDepartementCode()
         );

     DepartementDTO departementDTO= webClient.get().uri("http://localhost:8081/api/departement/getDepartementByCode/"+employee.getDepartementCode()).retrieve().bodyToMono(DepartementDTO.class).block() ;

        APIResponseDTO apiResponseDTO= new APIResponseDTO() ;

         apiResponseDTO.setDepartementDTO(departementDTO);

         apiResponseDTO.setEmployeeDTO(employeeDTO);

           return  apiResponseDTO ;

    }

    /**
     * @return
     */

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll().stream().
                map(departement -> modelMapper.map(departement, EmployeeDTO.class)).collect(Collectors.toList());
    }

    /**
     * @param user
     * @return
     */
    @Override
    public EmployeeDTO updateEmployees(Employee user) {
        return null;
    }

    /**
     * @param id
     */
    @Override
    public void deleteEmployee(Long id) throws EmployeeNotFoundException {

        employeeRepository.findById(id).orElseThrow( () -> new EmployeeNotFoundException()) ;

        employeeRepository.deleteById(id);
    }
}
