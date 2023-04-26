package com.spring.mvc.spring_jdbc;

import com.spring.mvc.jdbc.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PersonSpringRepositoryTest {
    @Autowired
    PersonSpringRepository repository;

    @Test
    void savePersonTest() {
        //given
        Person p = new Person();
        p.setPersonName("정스프링");
        p.setPersonAge(4);
        //when
        repository.savePerson(p);
    }
    // 삭제 기능
    @Test
    void removePerson() {
        //given
        long id = 2L;
        //when
        repository.removePerson(id);
    }


    @Test
    void modifyPersonTest() {
        // given
        Person p = new Person();
        p.setId(3L);
        p.setPersonName("만지호");
        p.setPersonAge(10000);
        // when
        boolean flag = repository.modify(p);
        // then
        assertTrue(flag);
    }

    @Test
    void findAllTest() {

        List<Person> people =repository.findAll();
        for(Person p : people){
            System.out.println("p = " + p);
        }
    }
    @Test
    void findOneTest() {
        Person p = repository.findOne(5L);
        System.out.println("p = " + p);

        assertEquals("춘식이", p.getPersonName());
    }
}