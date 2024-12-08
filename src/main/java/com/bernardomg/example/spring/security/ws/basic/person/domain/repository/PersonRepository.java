
package com.bernardomg.example.spring.security.ws.basic.person.domain.repository;

import java.util.Collection;

import com.bernardomg.example.spring.security.ws.basic.person.domain.model.Person;

public interface PersonRepository {

    public Collection<Person> findAll();

}
