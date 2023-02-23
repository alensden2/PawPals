package com.asdc.pawpals.repository;

import com.asdc.pawpals.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminReadAllVetsRepository extends JpaRepository<Vet, Long> {}
