package com.example.demo;

import com.example.demo.domain.School;
import com.example.demo.domain.SchoolView;
import com.example.demo.messages.CreateShoolCommand;
import com.example.demo.messages.SchoolCreatedEvent;
import com.example.demo.service.JpaSchoolsRepository;
import com.example.demo.service.SchoolProjector;
import org.axonframework.test.aggregate.AggregateTestFixture;
import org.axonframework.test.aggregate.FixtureConfiguration;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SchoolTest {
    private FixtureConfiguration<School> fixture;

    private final UUID schoolId = UUID.randomUUID();
    private final String schoolName = "mySchoolName";

    private final JpaSchoolsRepository repository = Mockito.mock(JpaSchoolsRepository.class);

    private SchoolProjector schoolProjector;

    @Before
    public void setUp() {
        fixture = new AggregateTestFixture<>(School.class);
        schoolProjector = new SchoolProjector(repository);
    }

    @Test
    public void testCreateSchoolCommand() {
        SchoolCreatedEvent schoolCreatedEvent = new SchoolCreatedEvent(schoolId, schoolName);

        fixture.givenNoPriorActivity()
                .when(new CreateShoolCommand(schoolId, schoolName))
                .expectSuccessfulHandlerExecution()
                .expectEvents(schoolCreatedEvent)
                .expectState(state -> {
                    School school = new School();
                    school.setId(schoolId);
                    school.setName("mySchoolName");

                    assertEquals(school, state);
                });

        //repository create new school
        SchoolView schoolView = new SchoolView();
        schoolView.setId(schoolId);
        schoolView.setName(schoolName);
        schoolView.setPresidentId(null);

        //mock repository return school view when query by id
//        Mockito.when(repository.findById(schoolId))
//                .thenReturn(Optional.of(schoolView));

        //send event
        schoolProjector.on(schoolCreatedEvent);

        ArgumentCaptor<SchoolView> captor = ArgumentCaptor.forClass(SchoolView.class);
        Mockito.verify(repository, Mockito.times(1)).saveAndFlush(captor.capture());
        SchoolView state = captor.getValue();

        assertEquals(schoolView, state);
    }
}
