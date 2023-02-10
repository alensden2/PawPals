package com.asdc.pawpals.repository;

import com.asdc.pawpals.model.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Long, Animal> {
}
