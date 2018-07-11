package com.mm.restdemo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mm.restdemo.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {


}
