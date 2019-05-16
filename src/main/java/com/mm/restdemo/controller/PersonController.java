package com.mm.restdemo.controller;

import com.mm.restdemo.ApplicationConfiguration;
import com.mm.restdemo.model.Person;
import com.mm.restdemo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/person")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private PersonService personService;

    @Autowired
    private ApplicationConfiguration config;

    @Value("${wireGroupList}")
    private String wireGroupList;

    @Autowired
    public PersonController(PersonService personService){
        this.personService = personService;
    }

    @RequestMapping( value = "/", method = RequestMethod.GET )
    public ResponseEntity<Iterable<Person>> list(){
        logger.info("wireGroupList: "+wireGroupList);
        logger.info("ApplicationConfig: groupList -  "+config.getGroupList());
        logger.info("ApplicationConfig: group1Limit: "+config.getGroup1Limit());
        logger.info("ApplicationConfig: group2Limit: "+config.getGroup2Limit());
        logger.info("ApplicationConfig: group3Limit: "+config.getGroup3Limit());
        logger.info("get list of persons");
        logger.debug("this is a DEBUG message");
        Iterable<Person> personList = personService.list();

        logger.info("persons:"+personList);
        return new ResponseEntity<>(personList, HttpStatus.OK);
    }

    @RequestMapping( value = "/{id}", method = RequestMethod.GET )
    public ResponseEntity<Person> read(@PathVariable(value="id") long id){
        logger.info("get person for id="+id);
        logger.debug("DEBUG the read method....");
        Optional<Person> person = personService.read(id);
        if ( person.isPresent() ) {
            logger.info("person found:"+person.get());
            return new ResponseEntity<>( person.get(), null, HttpStatus.OK);
        } else {
            logger.info("Person not found for id="+id);
            return new ResponseEntity<>(null, null, HttpStatus.NOT_FOUND);
        }
    }


//    @RequestMapping( value = "/{id}", method = RequestMethod.PUT )
//    public ResponseEntity<Person> update(@PathVariable(value="id") long id, @RequestBody Person person){
//        return personService.update(id,person);
//    }

    //@ExceptionHandler({ Exception.class })
    @RequestMapping( value = "/", method = RequestMethod.POST )
    public ResponseEntity<Person> create(@Valid @RequestBody Person person){
        return new ResponseEntity<>(personService.create(person), null, HttpStatus.OK);
    }


//    @RequestMapping( value = "/{id}", method = RequestMethod.DELETE )
//    public void delete(@PathVariable(value="id") int id){
//        personService.delete(id);
//    }


}
