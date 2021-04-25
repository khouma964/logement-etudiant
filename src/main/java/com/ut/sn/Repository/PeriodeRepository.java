package com.ut.sn.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ut.sn.Modeles.Periode;

public interface PeriodeRepository extends JpaRepository<Periode, Integer> {

	Optional<Periode> findById(Integer id);
}
