package com.example.demo.messages

import org.axonframework.modelling.command.TargetAggregateIdentifier
import java.util.*


data class CreateShoolCommand (
        @TargetAggregateIdentifier
        val id: UUID,
        val name: String
)

data class CreatePresidentCommand (
        @TargetAggregateIdentifier
        val schoolId: UUID,
        val schoolName: String,
        val presidentId: UUID,
        val presidentName: String
)

data class CreateUserCommand (
        @TargetAggregateIdentifier
        val userId: UUID,
        val userName: String
)

data class SelectSchoolForPresidentCommand (
        @TargetAggregateIdentifier
        val schoolId: UUID,
        val presidentId: UUID
)

data class SchoolSelectedForPresidentEvent (
        val schoolId: UUID,
        val presidentId: UUID
)

data class UserCreatedEvent (
        val userId: UUID,
        val userName: String
)

data class PresidentCreatedEvent (
        val id: UUID,
        val name: String
)

data class SchoolCreatedEvent (
        val id: UUID,
        val name: String
)

