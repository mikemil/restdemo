package com.mm.restdemo.service;

import javax.transaction.Transactional;

import com.mm.restdemo.ApplicationConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import com.mm.restdemo.model.Person;
import com.mm.restdemo.repository.PersonRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    private ApplicationConfiguration config;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
    }

    @Override
    public ApplicationConfiguration getConfig() {
        return config;
    }

    @Override
    public Iterable<Person> list() {
        return personRepository.findAll();
    }

    @Override
    public Optional<Person> read(long id) {
        return personRepository.findById(id);
    }

    @Override
    @Transactional
    @Retryable(value = { IllegalArgumentException.class }, maxAttempts = 3, backoff = @Backoff(delay = 1000))
    public Person create(Person person) {
        BigDecimal oneMillion = new BigDecimal(1000000);
        if (person.getSalary().compareTo(oneMillion) > 0) {
            throw new IllegalArgumentException("salary too high");
        }

        return personRepository.save(person);
    }

    @Override
    public void delete(long id) {
        personRepository.delete(personRepository.findById(id).get());
    }


    @Recover
    public Person fallBack(IllegalArgumentException e, Person p) {
        System.out.println("***** Falling back due to too many failures *****");
        System.out.println("***** Exception: "+e);
        return p;
    }

    @Override
    public Optional<Person> update(long id,Person update) {
        Optional<Person> person = personRepository.findById(id);
        //TODO: do this in a better Java8 way!!!
        if( update.getFirstName() != null ) {
            person.get().setFirstName(update.getFirstName());
        }
        //TODO: double check if this is best way for Java8!!!
        return  Optional.of( personRepository.save(person.get()) );
    }

}
