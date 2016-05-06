package de.irian.learning.sdr.data.tag.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.RepositoryDefinition;
import org.springframework.security.access.prepost.PreAuthorize;

import de.irian.learning.sdr.data.tag.entity.Tag;

@RepositoryDefinition(domainClass=Tag.class, idClass=Integer.class)
public interface TagRepository{
		
	//@Query("SELECT t FROM Tag t WHERE t.id = 2")
	List<Tag> findAll();
	
	@PreAuthorize("hasRole('user')")
	@Query("SELECT t FROM Tag t WHERE t.id = ?1 AND t.name = ?#{principal.name}")
	Tag findOne(Integer id);
	
}
