package com.asdc.pawpals.repository;

import com.asdc.pawpals.model.MedicalHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalHistory, Long> {
}
