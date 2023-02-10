package com.asdc.pawpals.repository;

import com.asdc.pawpals.model.PetOwner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetOwnerRepository extends JpaRepository<Long, PetOwner> {
}
