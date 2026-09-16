package com.aarohi.subsense.repository;

import com.aarohi.subsense.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


//epository = provides the operations to work with that entity/table.
public interface UserRepository extends JpaRepository<User,Long> {//This repository operates on the User entity, whose primary key is a Long
    //User tells Spring:
    //
    //"This repository is going to work with the User entity."
    //Long represents the datatype of the primary key of the user table

    //JpaRepository<WHAT, ID_TYPE>

    Optional<User> findByEmail(String email);
}
