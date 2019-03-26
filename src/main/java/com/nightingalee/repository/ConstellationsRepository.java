package com.nightingalee.repository;

import com.nightingalee.model.Constellations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ConstellationsRepository extends JpaRepository<Constellations, String> {

}
