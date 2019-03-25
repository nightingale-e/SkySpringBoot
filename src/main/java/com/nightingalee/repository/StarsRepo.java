package com.nightingalee.repository;

import com.nightingalee.model.Stars;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StarsRepo extends JpaRepository<Stars, Long> {
    Stars findByNazwaContaining(String nazwa);
}
