package com.example.demo.service;

import com.example.demo.messages.UserCreatedEvent;
import com.example.demo.domain.UserView;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserProjector {
    private final JpaUserRepository jpaUserRepository;

    @Autowired
    public UserProjector(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @EventHandler
    public void on(UserCreatedEvent evt) {
        UserView user = new UserView();
        user.setUserId(evt.getUserId());
        user.setUserName(evt.getUserName());

        jpaUserRepository.save(user);
    }
}
