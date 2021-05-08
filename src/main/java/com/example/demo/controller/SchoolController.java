package com.example.demo.controller;

import com.example.demo.messages.CreatePresidentCommand;
import com.example.demo.messages.CreateShoolCommand;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/school")
public class SchoolController {
    private final CommandGateway commandGateway;

    @Autowired
    public SchoolController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @GetMapping("/create")
    public void createSchool(@RequestParam String schoolName) {
        UUID id = UUID.randomUUID();
        commandGateway.send(new CreateShoolCommand(id, schoolName));
    }

    @AllArgsConstructor
    static class DTO_createPresident {
       String presidentName;
       String schoolName;
    }

    @PostMapping("/createPresident")
    public void createPresident(@RequestBody DTO_createPresident dto) {
        UUID schoolId = UUID.randomUUID();
        UUID presidentId = UUID.randomUUID();

        commandGateway.send(new CreatePresidentCommand(schoolId, dto.schoolName, presidentId, dto.presidentName));
    }
}
