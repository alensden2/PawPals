package com.asdc.pawpals.repository;

import com.asdc.pawpals.model.Animal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminReadRepository extends JpaRepository<Animal, Long> {}
