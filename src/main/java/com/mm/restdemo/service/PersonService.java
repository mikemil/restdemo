package com.mm.restdemo.service;

import com.mm.restdemo.ApplicationConfiguration;
import com.mm.restdemo.model.Person;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;

import java.util.Optional;

/**
 * Sample interface with the CRUD methods, plus an additional
 * read for a specific id.
 *
 */
public interface PersonService {

    ApplicationConfiguration getConfig();

    Iterable<Person> list();

    //@Retryable(value = { IllegalArgumentException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    Person create(Person person);

    Optional<Person> read(long id);

    Optional<Person> update(long id, Person person);

    void delete(long id);

    //@Recover
    Person fallBack(IllegalArgumentException e, Person person);
}

