package com.example.demo.service;

import com.example.demo.messages.SchoolCreatedEvent;
import com.example.demo.messages.SchoolSelectedForPresidentEvent;
import com.example.demo.domain.SchoolView;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class SchoolProjector {
    private final JpaSchoolsRepository repository;

    @Autowired
    public SchoolProjector(JpaSchoolsRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(SchoolCreatedEvent evt) {
        SchoolView schoolView = new SchoolView();
        schoolView.setId(evt.getId());
        schoolView.setName(evt.getName());

        repository.saveAndFlush(schoolView);
    }

    @EventHandler
    public void on(SchoolSelectedForPresidentEvent evt) {
        UUID schoolId = evt.getSchoolId();
        SchoolView school = repository.findById(schoolId).get();
        school.setPresidentId(evt.getPresidentId());
        repository.save(school);
        log.info("jap saved president with id: " + evt.getPresidentId() + " to school with id: " + schoolId);
    }
}
