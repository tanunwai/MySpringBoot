package com.tanunwai.springboot.exam.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tanunwai.springboot.exam.demo.entity.User;

public interface PageUserRepository extends JpaRepository<User, Long>{
}
