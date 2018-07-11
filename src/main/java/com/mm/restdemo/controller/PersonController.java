package com.mm.restdemo.controller;

import com.mm.restdemo.model.Person;
import com.mm.restdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public ResponseEntity<Iterable<Person>> list(){
        Iterable<Person> personList = personService.list();
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public ResponseEntity<Person> read(@PathVariable(value="id") long id){
        Optional<Person> person = personService.read(id);
        if ( person.isPresent() ) {
            return new ResponseEntity<>( person.get(), null, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
    }


//    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
//    public ResponseEntity<Person> update(@PathVariable(value="id") long id, @RequestBody Person person){
//        return personService.update(id,person);
//    }


    @RequestMapping( value = "/", method = RequestMethod.POST )
    public ResponseEntity<Person> create(@Valid @RequestBody Person person){
        return new ResponseEntity<>(personService.create(person), null, HttpStatus.OK);
    }


//    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
//    public void delete(@PathVariable(value="id") int id){
//        personService.delete(id);
//    }


}
