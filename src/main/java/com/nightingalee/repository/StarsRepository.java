package com.nightingalee.repository;

import com.nightingalee.model.Stars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StarsRepository extends JpaRepository<Stars, Long> {
    Stars findByNameContaining(String name);
}
