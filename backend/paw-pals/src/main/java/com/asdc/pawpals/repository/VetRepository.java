package com.asdc.pawpals.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asdc.pawpals.model.Vet;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long>{
}
