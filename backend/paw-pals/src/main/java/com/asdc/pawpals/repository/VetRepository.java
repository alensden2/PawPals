package com.asdc.pawpals.repository;

import com.asdc.pawpals.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {}
