package com.example.demo.repository;

import com.example.demo.model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<Authority, Long> {

    Authority findByName(String name);

}