package com.asdc.pawpals.repository;

import com.asdc.pawpals.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminReadAllUserRepository extends JpaRepository<User, String> {}
