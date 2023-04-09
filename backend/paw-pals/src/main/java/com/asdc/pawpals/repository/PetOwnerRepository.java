package com.asdc.pawpals.repository;

import com.asdc.pawpals.exception.UserNameNotFound;
import com.asdc.pawpals.model.PetOwner;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

@Transactional
public interface PetOwnerRepository extends JpaRepository<PetOwner, Long> {

    boolean existsByUser_UserId(String userId);

    Optional<PetOwner> findByUser_UserId(String userId) throws UserNameNotFound;

    @Modifying
    @Query("delete from PetOwner p where p.id = ?1")
    void delete(Long entityId);

}
