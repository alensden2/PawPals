package com.asdc.pawpals.repository;

import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.PetOwner;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {

    boolean existsByUser_UserId(String userId);

    Optional<PetOwner> findByUser_UserId(String userId) throws UserNameNotFound;

}
