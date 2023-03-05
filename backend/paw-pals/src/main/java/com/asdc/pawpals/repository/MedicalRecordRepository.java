package com.asdc.pawpals.repository;

import com.asdc.pawpals.model.MedicalHistory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalHistory, Long> {
 
    public List<MedicalHistory> findByAnimalId(Long animalId);
}
