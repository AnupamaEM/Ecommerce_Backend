package com.productproject.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.google.common.base.Optional;
import com.productproject.demo.entity.Ratings;


@Repository
public interface RatingsRepository extends JpaRepository<Ratings,Integer>{

    // @Query("SELECT ratingValue FROM Ratings r WHERE r.users.uid=:uid AND r.products.pid=:pid")
    // Optional<Ratings> findRatingByUserAndProduct(int uid, int pid);

    List<Ratings> findAllByProductsPid(int pid);
    // Ratings findByProductPidAndUserUid(int pid, int uid);
    Ratings findByProductsPidAndUsersUid(int pid, int uid);


    @Modifying
    @Query("DELETE FROM Ratings r WHERE r.products.pid=:pid AND r.users.uid=:uid")
    void deleteByProductsAndUsers(int uid, int pid);

    @Modifying
    @Query("DELETE FROM Ratings r WHERE r.products.pid=:pid ")
    void deleteBypid(int pid);

}
