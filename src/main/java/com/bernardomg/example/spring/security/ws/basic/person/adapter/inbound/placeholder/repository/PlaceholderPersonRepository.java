
package com.bernardomg.example.spring.security.ws.basic.person.adapter.inbound.placeholder.repository;

import java.util.Collection;
import java.util.Objects;

import org.springframework.stereotype.Repository;

import com.bernardomg.example.spring.security.ws.basic.person.domain.model.Person;
import com.bernardomg.example.spring.security.ws.basic.person.domain.repository.PersonRepository;
import com.bernardomg.example.spring.security.ws.basic.security.user.domain.model.User;
import com.bernardomg.example.spring.security.ws.basic.security.user.domain.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public final class PlaceholderPersonRepository implements PersonRepository {

    private final UserRepository userRepository;

    public PlaceholderPersonRepository(final UserRepository userRepo) {
        super();

        userRepository = Objects.requireNonNull(userRepo, "Received a null pointer as user repository");
    }

    @Override
    public final Collection<Person> findAll() {
        final Collection<Person> persons;

        log.debug("Finding all the persons");

        persons = userRepository.findAll()
            .stream()
            .map(this::toPerson)
            .toList();

        log.debug("Found all the persons: {}", persons);

        return persons;
    }

    private final Person toPerson(final User user) {
        return new Person(user.username(), user.name());
    }

}
