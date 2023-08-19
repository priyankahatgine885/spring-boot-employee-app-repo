package com.velocity.model;

import com.velocity.dto.EmployeeDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "employee")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "pan_number")
    private String panNumber;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender")
    private String gender;


    public Employee(final EmployeeDto employeeDto) {
        this.id = employeeDto.getId();
        this.firstName = employeeDto.getFirstName();
        this.lastName = employeeDto.getLastName();
        this.email = employeeDto.getEmail();
        this.panNumber = employeeDto.getPanNumber();
        this.phoneNumber = employeeDto.getPhoneNumber();
        this.gender = employeeDto.getGender();
    }

}
