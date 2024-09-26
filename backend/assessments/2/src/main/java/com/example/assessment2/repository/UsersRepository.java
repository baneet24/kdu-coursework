package com.example.assessment2.repository;

import com.example.assessment2.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.UUID;

@Repository
public interface UsersRepository  extends JpaRepository<Users, UUID> {

}