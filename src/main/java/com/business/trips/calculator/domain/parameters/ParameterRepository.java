package com.business.trips.calculator.domain.parameters;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ParameterRepository extends CrudRepository<Parameter, Long> {

    @Override
    List<Parameter> findAll();

    @Override
    Parameter save(Parameter parameter);

    Optional<Parameter> findById(Long id);
}
