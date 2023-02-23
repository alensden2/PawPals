package com.asdc.pawpals.repository;

import com.asdc.pawpals.model.PetOwner;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;



    public interface AdminReadAllPetOwnersRepository extends JpaRepository<PetOwner, Long>{}

    

