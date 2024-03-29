package com.example.ppm.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.ppm.domain.ProjectTask;

@Repository
public interface ProjectTaskRepository extends CrudRepository<ProjectTask, Long>{

	List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);	

	ProjectTask findByProjectSequence(String sequence);
}
