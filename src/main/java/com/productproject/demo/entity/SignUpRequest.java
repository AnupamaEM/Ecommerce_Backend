package com.productproject.demo.entity;

import lombok.Data;

@Data
public class SignUpRequest {
    private String email;
    private String password;
    private String fname;
    private String lname;
    private String phoneNum;
    private String position;

}
