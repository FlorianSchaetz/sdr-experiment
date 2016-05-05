package de.irian.learning.sdr.data.person.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import de.irian.learning.sdr.data.person.entity.Person;

public interface PersonRepository extends PagingAndSortingRepository<Person, Integer> {

}
