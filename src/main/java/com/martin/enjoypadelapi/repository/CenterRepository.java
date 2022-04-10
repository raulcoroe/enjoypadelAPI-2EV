package com.martin.enjoypadelapi.repository;

import com.martin.enjoypadelapi.domain.Center;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends CrudRepository<Center, Long> {
    List<Center> findAll();
}
