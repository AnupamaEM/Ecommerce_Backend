package com.productproject.demo.Service;

import java.time.LocalDateTime;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.productproject.demo.dto.Ratingdto;
import com.productproject.demo.entity.Products;
import com.productproject.demo.entity.Ratings;
import com.productproject.demo.entity.Users;
import com.productproject.demo.repository.ProductsRepository;
import com.productproject.demo.repository.RatingsRepository;
import com.productproject.demo.repository.UsersRepository;

import jakarta.transaction.Transactional;

@Service
public class RatingService {

     @Autowired
    public UsersRepository usersRepository;

    @Autowired
    public ProductsRepository productsRepository;

    @Autowired
    public RatingsRepository ratingsRepository; 


    // add new rating
    public void addRating(Ratingdto ratingdto) {
        Ratings rating = new Ratings();
        rating.setRatingValue(ratingdto.getRatingValue());
        rating.setCreatedAt(LocalDateTime.now());
        Products product = productsRepository.findById(ratingdto.getPid()).get();
        Users user = usersRepository.findById(ratingdto.getUid()).get();
        rating.setProducts(product);
        rating.setUsers(user);
        ratingsRepository.save(rating);
        avgRatings(product.getPid());
    }

    // update existing rating
    public void updateRating(int pid, int uid, Ratingdto ratingdto) {
       Ratings existingRating = ratingsRepository.findByProductsPidAndUsersUid(pid, uid);
        existingRating.setRatingValue(ratingdto.getRatingValue());
        existingRating.setUpdatedAt(LocalDateTime.now());
        ratingsRepository.save(existingRating);
        avgRatings(pid);
    }

    private void avgRatings(int pid) {
        Float avgRating = productsRepository.findAvgRating(pid);
        Products product = productsRepository.findById(pid).get();
        product.setAvgRating((float) avgRating);
        productsRepository.save(product);
    }

    @Transactional
    public String deleterating(int uid, int pid) {
        ratingsRepository.deleteByProductsAndUsers(uid,pid);
        return "deleted";
        
    }


}



    

 
    





