package com.asdc.pawpals.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asdc.pawpals.model.MedicalHistory;

public interface AdminReadAllMedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {}
    
