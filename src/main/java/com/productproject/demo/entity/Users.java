package com.productproject.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Table(name="users_table")
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="User_Id")
    private int uid;

    @NotBlank(message="fname cannot be blank")
    @Size(min=2,message="minimum 2 characters required")
    @Column(name="first_name")
    private String fname;

    @NotBlank(message="lname cannot be blank")
    @Size(min=2,message="minimum 2 characters required")
    @Column(name="last_name")
    private String lname;

    @NotBlank(message="email cannot be blank")
    @Column(name="email")
    @Email(message="enter a valid email")
    private String email;

    @Column(name="Firebase_id")
    private String fid;

    @Column(name="phone_number")
    @NotNull(message = "Phone number is required")
    @Size(min=12,max=14,message="minimum 12 characters required")
    @Pattern(regexp = "^\\+?[0-9]{1,3}?[-.\\s]?\\(?[0-9]{1,4}?\\)?[-.\\s]?[0-9]{1,4}[-.\\s]?[0-9]{1,9}$",message = "Phone number must be valid, include country code")
    private String phoneNum;

    @NotBlank(message="email cannot be blank")
    private String position;

}
