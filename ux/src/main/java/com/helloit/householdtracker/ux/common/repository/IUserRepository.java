package com.helloit.householdtracker.ux.common.repository;


import com.helloit.householdtracker.ux.common.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface IUserRepository extends JpaRepository<User, Integer> {

    List<User> findByusername(String test);
}