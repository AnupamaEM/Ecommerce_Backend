package com.productproject.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="product_table")
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="product_Id")
    private int pid;
    
    @NotBlank(message="pname required")
    @Column(name="prod_name")
    private String pname;

    @Column(name="description")
    private String desc;

    @Min(value = 5,message="please enter a value above 50")
    @Max(value = 10000,message="please enter a value below 100000")
    @Column(name="price")
    private long price;

    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be at most 5")
    private float avgRating;

}
