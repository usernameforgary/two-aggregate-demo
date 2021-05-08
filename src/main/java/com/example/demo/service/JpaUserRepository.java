package com.example.demo.service;

import com.example.demo.domain.UserView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaUserRepository extends JpaRepository<UserView, UUID> {
}
