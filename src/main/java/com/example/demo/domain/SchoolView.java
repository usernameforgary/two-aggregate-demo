package com.example.demo.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class SchoolView {
    @Id
    private UUID id;
    private String name;
    private UUID presidentId;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getPresidentId() {
        return presidentId;
    }

    public void setPresidentId(UUID presidentId) {
        this.presidentId = presidentId;
    }
}
