package com.productproject.demo.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.productproject.demo.entity.Cart;
import com.productproject.demo.entity.Products;
import com.productproject.demo.entity.Users;

@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
// public interface CartRepository extends PagingAndSortingRepository<Cart,Integer> {
 
    List<Cart> findByUsersUid(int uid);
    
    @Query("SELECT SUM(c.qty*p.price)FROM Cart c JOIN Products p ON c.products.pid=p.pid WHERE c.users.uid=:uid")
    int findTotalPriceByUsersUid(int uid);



    // @Query("SELECT CASE " +

    //    "WHEN SUM(c.qty * p.price) > 10000 THEN SUM(c.qty * p.price) * 0.7 " +
      
    //    "WHEN SUM(c.qty * p.price) > 1000 THEN SUM(c.qty * p.price) * 0.8 " +
       
    //    "WHEN SUM(c.qty * p.price) > 100 THEN SUM(c.qty * p.price) * 0.9 " +
       
    //    "ELSE SUM(c.qty * p.price) " +
       
    //    "END " +

    //    "FROM Cart c JOIN Products p ON c.products.pid = p.pid WHERE c.users.uid = :uid")
    // int findTotalPriceByUsersUid(int uid);


    @Query("SELECT COUNT(c.products.pid) FROM Cart c WHERE c.users.uid =:uid")
    int findTheCount(int uid);

    @Query("SELECT p FROM Products p JOIN Cart c ON c.products.pid=p.pid WHERE c.users.uid=:uid")
    List<Products> findProductsByUid(int uid);

    @Query("SELECT u FROM Cart c JOIN Users u ON c.users.uid=u.uid WHERE c.products.pid=:pid")
    List<Users> findUsersByPid(int pid);

    @Query("SELECT COUNT(qty) FROM Cart c JOIN Products p ON c.products.pid= p.pid WHERE c.products.pid=:pid")
    int findQtyByPid(int pid);
 
}

/*

SELECT u.user_id,c.cart_id FROM cart_table c JOIN users_table u  ON c.user_id = u.user_id ; 
SELECT u.user_id,c.cart_id FROM cart_table c FULL JOIN users_table u  ON c.user_id = u.user_id ;

SELECT SUM(quantity*price)FROM cart_table c JOIN product_table p ON c.product_id=p.product_id WHERE c.user_id=4;

SELECT u.user_id,c.cart_id FROM cart_table c LEFT JOIN users_table u  ON c.user_id = u.user_id WHERE u.user_id=1;
SELECT * FROM product_table p JOIN cart_table c ON c.product_id=p.product_id WHERE c.user_id=1;
SELECT * FROM cart_table c JOIN users_table u ON c.user_id=u.user_id WHERE c.product_id=3;
SELECT COUNT(quantity) FROM cart_table c JOIN product_table p ON c.product_id= p.product_id WHERE c.product_id=2

*/ 