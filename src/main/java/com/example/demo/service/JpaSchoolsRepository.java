package com.example.demo.service;

import com.example.demo.domain.SchoolView;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaSchoolsRepository extends JpaRepository<SchoolView, UUID> {
}
