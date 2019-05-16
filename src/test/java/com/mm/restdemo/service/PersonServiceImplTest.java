package com.mm.restdemo.service;

import com.mm.restdemo.repository.PersonRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(classes = PersonServiceImplTest.class)
public class PersonServiceImplTest {

    @TestConfiguration
    static class PersonServiceImplTestContextConfiguration {

        @MockBean
        private PersonRepository personRepository;

        @Bean
        public PersonService personService() {
            return new PersonServiceImpl(personRepository);
        }
    }

    @Autowired
    PersonService personService;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void list() {
        assertNotNull("personService is null", personService);
        assertNotNull( personService.getConfig().getGroupList() );
        System.out.println(personService.getConfig().getGroupList());
    }
}