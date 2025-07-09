package com.sushil.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sushil.main.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer>{

    User findByEmail(String email);

}
