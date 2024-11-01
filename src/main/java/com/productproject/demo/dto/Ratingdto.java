package com.productproject.demo.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Ratingdto {

    private int rid;
    private float ratingValue;
    private LocalDateTime createdAt;
    private LocalDateTime UpdatedAt;
    private int uid;
    private int pid;

}
