package com.secretsanta.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.secretsanta.model.Person;

public interface PersonRepository extends MongoRepository<Person, String> {
	
	Optional<Person> findById(String id);
	
	Optional<Person> findByName(String name);
	
}
