package com.asdc.pawpals.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asdc.pawpals.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, Integer>{
    public List<Appointment> findByVet_User_UserId(String userId);
    public List<Appointment> findByVetId(Long id);
}
