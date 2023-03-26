package com.asdc.pawpals.repository;

import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.PetOwner;
import com.asdc.pawpals.model.Vet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VetRepository extends JpaRepository<Vet, Long> {

    Optional<Vet> findByUser_UserId(String userId) throws UserNameNotFound;
}
