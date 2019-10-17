package com.javasampleapproach.springrest.mongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.javasampleapproach.springrest.mongodb.model.Person;

public interface PersonRepository extends MongoRepository<Person, String> {
	
	Optional<Person> findById(String id);
	
	Optional<Person> findByName(String name);
	
}
