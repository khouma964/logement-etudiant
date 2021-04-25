package com.ut.sn.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ut.sn.Modeles.Chambre;
import com.ut.sn.Modeles.Etudiant;

public interface ChambreRepository extends JpaRepository<Chambre, Integer> {

	Chambre findByNumero(Integer numero);

	Chambre findByEtudiant(Etudiant etudiant);
}
