package com.ut.sn.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.ut.sn.Modeles.Etudiant;
import com.ut.sn.Modeles.UserModel;

public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {

	Optional<Etudiant> findByUser(Optional<UserModel> user);

	Etudiant findByNumDossier(String numDossier);
}
