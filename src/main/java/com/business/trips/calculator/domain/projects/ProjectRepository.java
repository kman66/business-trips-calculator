package com.business.trips.calculator.domain.projects;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long> {

    @Override
    List<Project> findAll();

    @Override
    Project save(Project project);

    Optional<Project> findById(Long projectId);

    Optional<Project> findByName(String projectName);
}
