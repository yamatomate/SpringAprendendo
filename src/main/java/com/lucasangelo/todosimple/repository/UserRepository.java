package com.lucasangelo.todosimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lucasangelo.todosimple.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    
}
