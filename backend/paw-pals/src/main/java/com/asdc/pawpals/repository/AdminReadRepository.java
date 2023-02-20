package com.asdc.pawpals.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asdc.pawpals.model.Animal;

@Repository
public interface AdminReadRepository extends JpaRepository<Animal, Long>{
}
