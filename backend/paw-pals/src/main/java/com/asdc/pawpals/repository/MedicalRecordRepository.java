package com.asdc.pawpals.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asdc.pawpals.model.MedicalHistory;


public interface MedicalRecordRepository extends JpaRepository<MedicalHistory, Long> {
 
    public List<MedicalHistory> findByAnimalId(Long animalId);
}
