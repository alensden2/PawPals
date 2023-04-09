package com.asdc.pawpals.repository;

import com.asdc.pawpals.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MedicalRecordRepository extends JpaRepository<MedicalHistory, Long> {

    public List<MedicalHistory> findByAnimalId(Long animalId);
}
