package com.productproject.demo.Service;

import java.util.*;
import org.springframework.data.domain.Page;
// import org.hibernate.query.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.productproject.demo.entity.Cart;
import com.productproject.demo.entity.Products;
import com.productproject.demo.entity.Users;
import com.productproject.demo.repository.CartRepository;
import com.productproject.demo.repository.ProductsRepository;
import com.productproject.demo.repository.RatingsRepository;
import com.productproject.demo.repository.UsersRepository;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Pageable;

@Service
public class DemoService {

    @Autowired
    public UsersRepository usersRepository;

    @Autowired
    public ProductsRepository productsRepository;

    @Autowired
    public CartRepository cartRepository;

    @Autowired
    public RatingsRepository ratingsRepository;

//     // Get all products
    public List<Products> getAllproducts() {
        try {
            return productsRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null; 
        }
    }

    // Get all users
    public List<Users> getAllUsers() {
        try {
            return usersRepository.findAll();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // Save product
    public ResponseEntity<Object> save(Products products) {
        try {
            productsRepository.save(products);
            return ResponseEntity.ok("done");
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Save user
    public Users save(Users users) {
        try {
            return usersRepository.save(users);
        } catch (Exception e) {
            System.out.println( e.getMessage());
            return null;
        }
    }

    // get prdt by id
    public Products getproductbyId(int pid) {
        try {
           Products product =productsRepository.findById(pid).get();
            return product;
            
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    // get usr by id
    public Users getuserbyId(int uid) {
        try {
            Users user=usersRepository.findById(uid).get();
            return user;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

// Delete product by ID
    @Transactional
    public int deleteproduct(int pid) {
        try {
            productsRepository.deleteById(pid);
            ratingsRepository.deleteBypid(pid);
            return pid;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return pid;  
        }
    }

// Delete user by ID
    public int deleteuser(int uid) {
        try {
            usersRepository.deleteById(uid);
            return uid;
        } catch (Exception e) {
            System.out.println( e.getMessage());
            return uid;
        }
    }

//save cart item
    public Cart save(Cart cart) {
       return cartRepository.save(cart);
    }

// find cart by id
public Cart  getCartbyId(int cid) {
    try {
        Cart cart =cartRepository.findById(cid).get();
        return cart;
    } catch (Exception e) {
        System.out.println(e.getMessage());
        return null;
    }
}

// delete cart
    public int deleteCart(int cid) {
        try {
            cartRepository.deleteById(cid);
            return cid;
        } catch (Exception e) {
            System.out.println( e.getMessage());
            return cid;
        }
    }

//  get product and qty by uid
    public List<Cart> getproductqtywithuid(int uid) {
        try{
            return cartRepository.findByUsersUid(uid);}
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

// total price
    public int gettotalprice(int uid) {
    try{
        return cartRepository.findTotalPriceByUsersUid(uid);}
    catch(Exception e){
        System.out.println(e.getMessage());
        return 0;
    }
    }

// getting product count by uid
    public int productCount(int uid) {
        try{
            return cartRepository.findTheCount(uid);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return 0;

    }

// pagination cart
    public Page<Cart> getAllCarts(int page, int size) {
        try{
        Pageable pageable = PageRequest.of(page, size);
        return cartRepository.findAll(pageable);}
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
// get the list of products by uid
    public List<Products> getproducts(int uid) {
        try{
            return cartRepository.findProductsByUid(uid);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

// get users who buy a particular product
    public List<Users> getUsersByProduct(int pid) {
        try{
        return cartRepository.findUsersByPid(pid);}
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }

// get qty of prdt for production
    public int getqtyforproduction(int pid) {
        try{
            return cartRepository.findQtyByPid(pid);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return 0;
    }

    //search for products
    public List<Products> searchProducts(String pname) {
        String[] parts = pname.split(" ", 2);  
        String firstName = parts[0];  
        String secondName = (parts.length > 1) ? parts[1] : "";  

        return productsRepository.findProductsBypname(firstName,secondName );
        
    }
 //search for products-apply filter
    public List<Products> filterProducts(Double minPrice, Double maxPrice, Double minRating, Double maxRating,String pname) {
        return productsRepository.findProductsByFilters(minPrice,maxPrice,minRating,maxRating,pname);
    }
    
    }
       


        
  















