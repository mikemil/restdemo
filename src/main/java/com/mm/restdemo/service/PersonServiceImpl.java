package com.mm.restdemo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mm.restdemo.model.Person;
import com.mm.restdemo.repository.PersonRepository;

import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    private PersonRepository personRepository;

    @Autowired
    public PersonServiceImpl(PersonRepository personRepository){
        this.personRepository = personRepository;
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
    public Person create(Person person) {
        return personRepository.save(person);
        //System.out.println(person);
        //return person;
    }

    @Override
    public void delete(long id) {
        personRepository.delete(personRepository.findById(id).get());
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
