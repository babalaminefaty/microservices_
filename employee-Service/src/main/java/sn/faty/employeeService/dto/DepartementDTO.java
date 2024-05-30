package sn.faty.employeeService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DepartementDTO {
    private  int id  ;
    private  String deprtName ;
    private  String description ;
    private  String departementCode ;
}
