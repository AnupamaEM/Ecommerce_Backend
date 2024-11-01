package com.productproject.demo.entity;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

@Table(name="Rating_Table")
@Entity
@Data
public class Ratings {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rating_id")
    private int rid;

    @Min(value = 0, message = "Rating must be at least 0")
    @Max(value = 5, message = "Rating must be at most 5")
    @Column(name="rating_value")
    private float ratingValue;

    @Column(name="creation Date")
    private LocalDateTime createdAt;

    @Column(name="updation Date")
    private LocalDateTime UpdatedAt;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User_Id",referencedColumnName = "User_Id")
    private Users users;
    
    @ManyToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name="product_Id" ,referencedColumnName = "product_Id")
    private Products products;

}






