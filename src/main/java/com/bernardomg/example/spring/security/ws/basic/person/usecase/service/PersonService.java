
package com.bernardomg.example.spring.security.ws.basic.person.usecase.service;

import java.util.Collection;

import com.bernardomg.example.spring.security.ws.basic.person.domain.model.Person;

public interface PersonService {

    public Collection<Person> getAll();

}
