package com.example.demo.domain;

import com.example.demo.messages.CreateUserCommand;
import com.example.demo.messages.UserCreatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.util.UUID;

@Aggregate
public class User {
    @AggregateIdentifier
    private UUID userId;
    private String userName;

    public User() {}

    @CommandHandler
    public User(CreateUserCommand cmd) {
        AggregateLifecycle.apply(new UserCreatedEvent(cmd.getUserId(), cmd.getUserName()));
    }

    @EventSourcingHandler
    public void on(UserCreatedEvent evt) {
        this.userId = evt.getUserId();
        this.userName = evt.getUserName();
    }
}
