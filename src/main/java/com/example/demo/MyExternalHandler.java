package com.example.demo;

import com.example.demo.domain.School;
import com.example.demo.domain.User;
import com.example.demo.messages.CreatePresidentCommand;
import com.example.demo.messages.CreateShoolCommand;
import com.example.demo.messages.CreateUserCommand;
import com.example.demo.messages.SelectSchoolForPresidentCommand;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.modelling.command.Aggregate;
import org.axonframework.modelling.command.Repository;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

@Component
public class MyExternalHandler {
    @Resource
    private Repository<School> schoolRepository;

    @Resource
    private Repository<User> userRepository;

    @CommandHandler
    public void on(CreatePresidentCommand cmd) throws Exception {
        UUID schoolId = cmd.getSchoolId();
        UUID presidentId = cmd.getPresidentId();

        //create new School
        schoolRepository.newInstance(() -> new School(
                new CreateShoolCommand(cmd.getSchoolId(), cmd.getSchoolName())
        ));
        //end of create new school

        //create new president
        userRepository.newInstance(() -> new User(
                new CreateUserCommand(presidentId, cmd.getPresidentName())
        ));
        //end of create new president

        //get created school aggregate
        Aggregate<School> schoolAggregate = schoolRepository.load(schoolId.toString());
        //assign president to school
        schoolAggregate.execute(r -> r.on(new SelectSchoolForPresidentCommand(schoolId, presidentId)));
    }
}
