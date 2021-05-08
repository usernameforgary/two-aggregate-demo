package com.example.demo.domain;

import com.example.demo.messages.CreateShoolCommand;
import com.example.demo.messages.SchoolCreatedEvent;
import com.example.demo.messages.SchoolSelectedForPresidentEvent;
import com.example.demo.messages.SelectSchoolForPresidentCommand;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Slf4j
@Aggregate
public class School {
    @AggregateIdentifier
    private UUID id;
    private String name;

    private UUID presidentId;

    public School() {}

    @CommandHandler
    public School(CreateShoolCommand command) {

        log.info(("create school command received, school id: " + command.getName()));
        AggregateLifecycle.apply(new SchoolCreatedEvent(command.getId(), command.getName()));
    }

    @CommandHandler
    public void on(SelectSchoolForPresidentCommand cmd) {
        AggregateLifecycle.apply(new SchoolSelectedForPresidentEvent(cmd.getSchoolId(), cmd.getPresidentId()));
    }

    @EventSourcingHandler
    public void on(SchoolSelectedForPresidentEvent evt) {
        this.presidentId = evt.getPresidentId();
    }

    @EventSourcingHandler
    public void on(SchoolCreatedEvent evt) {
        this.id = evt.getId();
        this.name = evt.getName();
    }
}
