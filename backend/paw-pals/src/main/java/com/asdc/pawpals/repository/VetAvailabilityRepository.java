package com.asdc.pawpals.repository;


import com.asdc.pawpals.model.VetAvailability;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface VetAvailabilityRepository extends JpaRepository<VetAvailability, Integer> {
    public List<VetAvailability> findByVet_User_UserId(String userId);

    public List<VetAvailability> findByVetId(Long id);
}
