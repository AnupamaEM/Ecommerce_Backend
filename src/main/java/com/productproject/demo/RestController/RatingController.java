package com.productproject.demo.RestController;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productproject.demo.Service.DemoService;
import com.productproject.demo.Service.RatingService;
import com.productproject.demo.dto.Ratingdto;
import com.productproject.demo.entity.Ratings;
import com.productproject.demo.repository.ProductsRepository;
import com.productproject.demo.repository.RatingsRepository;
import com.productproject.demo.repository.UsersRepository;


@RestController
@RequestMapping("/store")
public class RatingController {

    @Autowired
    public ProductsRepository productsRepository;

    @Autowired
    public UsersRepository usersRepository;
    
    @Autowired
    public RatingsRepository ratingsRepository;

    @Autowired
    public RatingService ratingService;

    @Autowired
    private DemoService demoService;

 
    @PostMapping("/user/addrating")
    public String addRating(@RequestBody Ratingdto ratingdto) {
        try{
        ratingService.addRating(ratingdto);
        return "Rating added ";}
        catch(Exception e){
            System.out.println("Rating added cannt be done");
        }
        return null;
    }

    
    @PutMapping("/user/editrating/{uid}/{pid}")
    public String updateRating(@PathVariable int pid, @PathVariable int uid, @RequestBody Ratingdto ratingdto) {
        try{
        ratingService.updateRating(pid, uid, ratingdto);
        return "Rating updated ";}
        catch(Exception e){
            System.out.println("Rating cannot be updated");
        }
        return null;
    }

 
    @DeleteMapping("/user/deleterating/{uid}/{pid}")
    public String deleterating(@PathVariable int uid, @PathVariable int pid){
        try{
        ratingService.deleterating( uid,pid);
        return "deleted";}
        catch(Exception e){
            System.out.println("cannot be deleted");
        } return null;
    }

    

}

 



