package com.example.employeemanagement.Model;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
public class EmployeeModel {

    @NotEmpty(message = "id can be not null")
    @Min(value = 2 , message = "enter your id at least 2 numbers")

    private final String id ;

    @NotEmpty(message = "name can not be empty")
    @Pattern(regexp = "^[a-zA-Z]{4,}$", message = "must be Letters only , no number ")
   // @Min(value = 4, message = "must be name more than 4 Letters")
    private String name ;

    @NotEmpty(message = "email can be not empty")
    @Email
    private String email ;

    @NotEmpty(message = "phone number can not be empty")
    //@Max(value = 10 ,message = "must be consists exactly 10 number")
    @Pattern(regexp = "^05\\d{8}$", message = "Must start with 05 and Must be consists of exactly 10 digits.. ")
    private String phoneNumber ;

    @NotNull(message = "can not be age null number")
    @Min(value = 25 , message = "Please enter Age over 25")
   //@Pattern(regexp = "^\\d+$", message = "Age must be a valid number")
    @Positive(message = "enter a valid number")
    private int age ;

    @NotEmpty(message = "position can not be empty")
    @Pattern(regexp = "^(supervisor|coordinator)$" , message = "Must be either \"supervisor\" or \"coordinator\" only.")
    private String position ;

    @NotNull(message = "on leave can not be null")

    private boolean onleave;

    @PastOrPresent
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate hireDate ;

    @NotNull(message= "Can not be annual leave no number")
    @PositiveOrZero(message = "must be annouale leave positive number")
    private int annualLeave ;

}
