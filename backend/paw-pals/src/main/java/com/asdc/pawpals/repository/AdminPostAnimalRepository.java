package com.asdc.pawpals.repository;

import com.asdc.pawpals.model.Animal;
import com.asdc.pawpals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminPostAnimalRepository
  extends JpaRepository<Animal, Long> {}
