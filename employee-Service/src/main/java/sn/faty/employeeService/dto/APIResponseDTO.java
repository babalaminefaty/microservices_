package sn.faty.employeeService.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class APIResponseDTO {

    private EmployeeDTO employeeDTO ;
    private  DepartementDTO departementDTO ;
}
