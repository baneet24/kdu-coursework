package com.example.jpahw.repository;

import com.example.jpahw.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;
public interface UserRepository  extends JpaRepository<User, UUID> {
    @Query(value = "UPDATE users SET username = :username, logged_in = :loggedIn, time_zone = :timeZone WHERE id = :userId", nativeQuery = true)
    void updateUserDetails(@Param("userId") UUID userId, @Param("username") String username, @Param("loggedIn") int loggedIn, @Param("timeZone") String timeZone);

}
