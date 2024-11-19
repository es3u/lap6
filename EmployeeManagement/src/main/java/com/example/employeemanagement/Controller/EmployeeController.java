package com.example.employeemanagement.Controller;

import com.example.employeemanagement.Model.EmployeeModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/lap6")
public class EmployeeController {

ArrayList <EmployeeModel> Employee= new ArrayList<>();
@GetMapping("/display")
public ResponseEntity display(){
    return ResponseEntity.ok(Employee);
}
@PostMapping("/newAdd")
public ResponseEntity newEmployee(@RequestBody @Valid EmployeeModel employee , Errors errors){
    if(errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(message);
    }
    Employee.add(employee);
    return ResponseEntity.ok("added successfully");
}

@PutMapping("/update/{id}")
public ResponseEntity updateEmployee(@PathVariable int id ,  @RequestBody @Valid EmployeeModel employee , Errors errors){
    if(errors.hasErrors()){
        String message = errors.getFieldError().getDefaultMessage();
        return ResponseEntity.badRequest().body(message);
    }
    for (EmployeeModel employeeModel : Employee) {
        if (employeeModel.getId().equals(id)) {
           int a = Employee.indexOf(employeeModel);
           Employee.set(a,employee);
        }else{
            return ResponseEntity.status(400).body("id not match");
        }
    }
    return ResponseEntity.status(200).body("updated successfully");
}
@DeleteMapping("/delete/{id}")
public ResponseEntity deleteEmployee(@PathVariable int id){
    if(Employee.get(id)==null){
        String message = "Employee not found";
        return ResponseEntity.badRequest().body(message);
    }else
    Employee.remove(id);
    return ResponseEntity.ok("deleted successfully");
}

@GetMapping("/getPosition/{position}")
public ResponseEntity Search_by_Position(@PathVariable String position){
    ArrayList<EmployeeModel> positionArr = new ArrayList<>();
    for (EmployeeModel employeeModel : Employee) {
        if (employeeModel.getPosition().equals(position)) {
            positionArr.add(employeeModel);
        }
    }
    return ResponseEntity.ok(positionArr);

}
@GetMapping("/Range/{minAge}/{maxAge}")
public ResponseEntity max_Min_AgeArr(@PathVariable int minAge,@PathVariable int maxAge){
    ArrayList<EmployeeModel> AgeArr = new ArrayList<>();

    for (EmployeeModel emp :Employee) {
        if (emp.getAge() >= minAge && emp.getAge() <= maxAge) {
            AgeArr.add(emp);
        }
    }

    return ResponseEntity.ok(AgeArr);
}

@PutMapping("/annualLeave/{id}")
public ResponseEntity annualLeave(@PathVariable String id ){

    for (EmployeeModel employeeModel : Employee) {
        if (employeeModel.getId().equals(id)) {
            if(employeeModel.isOnleave() == false){
                if (employeeModel.getAnnualLeave()>=1){
                    employeeModel.setAnnualLeave(employeeModel.getAnnualLeave()-1);
                    employeeModel.setOnleave(true);
                }else
                    return ResponseEntity.status(400).body("annualLeave not match");
            }else
                return ResponseEntity.status(400).body("annualLeave not match");
        }else
            return ResponseEntity.status(400).body("id not match");
    }
return ResponseEntity.status(400).body("He deserves a vacation");
}

@GetMapping("/getOn")
public ResponseEntity getOnleave(){
    ArrayList<EmployeeModel> onleaveArr = new ArrayList<>();
    for (EmployeeModel employeeModel : Employee) {
        if(employeeModel.isOnleave() == true){
            onleaveArr.add(employeeModel);
        }
    }
    return ResponseEntity.status(200).body(onleaveArr);
}
@PutMapping("/promote/{id}")
public ResponseEntity Promote_Employee(@PathVariable String id , @RequestBody EmployeeModel emp){
    for (EmployeeModel employeeModel : Employee) {
        if (employeeModel.getId().equals(id)) {
                if (employeeModel.isOnleave() == false) {
                    if (employeeModel.getPosition().equals("supervisor")) {
                        if (emp.getAge()>30){
                            if (emp.isOnleave() == false) {
                                emp.setPosition("supervisor");
                            }else
                                return ResponseEntity.status(400).body(" Employee is  onleave");

                        }else
                            return ResponseEntity.status(400).body("age < 30");

                    }else
                        return ResponseEntity.status(400).body("position not match");

                } else
                    return ResponseEntity.status(400).body("id not match");
        }else
            return ResponseEntity.status(400).body("id not match");
    }
    return ResponseEntity.status(200).body("He deserves to be a supervisor");
}












}
