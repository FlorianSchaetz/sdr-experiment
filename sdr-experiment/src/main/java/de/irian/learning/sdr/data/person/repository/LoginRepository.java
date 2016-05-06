package de.irian.learning.sdr.data.person.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RestResource;

import de.irian.learning.sdr.data.person.entity.Login;

@RestResource(exported=true)
public interface LoginRepository extends PagingAndSortingRepository<Login, Integer>{

	@RestResource(exported=true)
	Page<Login> findAll(Pageable pageable);
	
	@RestResource(exported=true)
	Login findOne(Integer id);
	
}
