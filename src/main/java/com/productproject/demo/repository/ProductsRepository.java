package com.productproject.demo.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.productproject.demo.entity.*;

@Repository
public interface ProductsRepository extends JpaRepository<Products,Integer> {

    @Query("SELECT AVG(r.ratingValue) FROM Ratings r JOIN Products p ON r.products.pid=products.pid WHERE r.products.pid = :pid")
    Float findAvgRating(int pid);

    @Query("SELECT p FROM Products p WHERE pname = CONCAT(:firstName, ' ', :secondName) " + "UNION ALL " + "SELECT p FROM Products p WHERE pname LIKE CONCAT('%', :firstName, '%') OR pname LIKE CONCAT('%', :secondName, '%') " +
    "AND pname != CONCAT(:firstName, ' ', :secondName) " + "ORDER BY CASE WHEN pname = CONCAT(:firstName, ' ', :secondName) THEN 1 ELSE 2 END") 
    List<Products> findProductsBypname(String firstName,String secondName);

    @Query("SELECT p FROM Products p WHERE " +"(:pname IS NULL OR p.pname LIKE CONCAT('%', :pname, '%')) AND "+"(:minPrice IS NULL OR p.price >= :minPrice) AND " + " (:maxPrice IS NULL OR p.price <= :maxPrice) AND " + "(:minRating IS NULL OR p.avgRating >= :minRating) AND " + "(:maxRating IS NULL OR p.avgRating <= :maxRating)")
    List<Products> findProductsByFilters(Double minPrice, Double maxPrice, Double minRating, Double maxRating ,String pname);
}



