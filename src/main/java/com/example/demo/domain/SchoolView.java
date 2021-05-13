package com.example.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Data
@Entity
public class SchoolView {
    @Id
    private UUID id;
    private String name;
    private UUID presidentId;
}
