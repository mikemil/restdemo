package com.mm.restdemo.service;

import com.mm.restdemo.model.Person;

import java.util.Optional;

/**
 * Sample interface with the CRUD methods, plus an additional
 * read for a specific id.
 *
 */
public interface PersonService {

    Iterable<Person> list();

    Person create(Person person);

    Optional<Person> read(long id);

    Optional<Person> update(long id, Person person);

    void delete(long id);
}

