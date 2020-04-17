package com.boot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.boot.entities.*;

public interface UserRepository extends JpaRepository<User, Long>{

}
