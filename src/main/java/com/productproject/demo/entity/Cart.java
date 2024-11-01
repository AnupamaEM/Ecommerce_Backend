package com.productproject.demo.entity;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name="cart_table")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="Cart_Id")
    private int cid;

    @Column(name="quantity")
   private long qty;

    @Column(name="favourite")
    private boolean favourite;

    @ManyToOne(cascade = CascadeType.ALL) 
    @JoinColumn(name="product_Id" ,referencedColumnName = "product_Id")  
    private Products products ;

    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "User_Id",referencedColumnName = "User_Id")
    private Users users;

}
    

    /* targetEntity=Products.class,cascade=
     * name=cp_fk,referencedColumnName="id"
      */
      


    




