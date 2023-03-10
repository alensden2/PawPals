package com.asdc.pawpals.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asdc.pawpals.model.VetAvailability;


public interface VetAvailabilityRepository extends JpaRepository<VetAvailability, Integer>  {
    public List<VetAvailability> findByVet_User_UserId(String userId);
    public List<VetAvailability> findByVetId(Long id);
}
