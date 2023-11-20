package com.example.repository;

import com.example.entity.YearEntity;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
@Repository
public interface YearRepository extends CrudRepository<YearEntity, Long> {

}
