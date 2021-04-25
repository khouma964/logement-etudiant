package com.ut.sn.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ut.sn.Modeles.Chambre;
import com.ut.sn.Modeles.Etudiant;
import com.ut.sn.Modeles.Periode;
import com.ut.sn.Modeles.UserModel;
import com.ut.sn.Repository.ChambreRepository;
import com.ut.sn.Repository.EtudiantRepository;
import com.ut.sn.Repository.PeriodeRepository;
import com.ut.sn.Repository.UserRepository;
import com.ut.sn.exception.ResourceNotFoundException;

import net.bytebuddy.asm.Advice.Exit;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EtudiantController {

	@Autowired
	EtudiantRepository etudiantRepository;
	@Autowired
	ChambreRepository chambreRepository;

	@Autowired
	PeriodeRepository periodeRepository;

	@Autowired
	UserRepository userRepo;

	@GetMapping("/etudiants")
	public List<Etudiant> getEtudiants() {
		return etudiantRepository.findAll();
	}

	@GetMapping("/etudiants/{id}")
	public ResponseEntity<Etudiant> getEtudiantById(@PathVariable(value = "id") Integer EtudiantId)
			throws ResourceNotFoundException {
		Etudiant etudiant = etudiantRepository.findById(EtudiantId)
				.orElseThrow(() -> new ResourceNotFoundException("Etudiant not found for this id :: " + EtudiantId));
		return ResponseEntity.ok().body(etudiant);
	}

	@PostMapping("/etudiants")
	public Etudiant createEtudiant(@Validated @RequestBody Etudiant Etudiant) {
		return etudiantRepository.save(Etudiant);
	}

	@DeleteMapping("/etudiants/{id}")
	public HashMap<String, Boolean> deleteEtudiant(@PathVariable(value = "id") Integer EtudiantId)
			throws ResourceNotFoundException {
		Etudiant etudiant = etudiantRepository.findById(EtudiantId)
				.orElseThrow(() -> new ResourceNotFoundException("Etudiant not present for the id :: " + EtudiantId));

		etudiantRepository.delete(etudiant);
		HashMap<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@PutMapping("/etudiants/{id}")
	public ResponseEntity<Etudiant> updateEtudiant(@PathVariable(value = "id") Integer EtudiantId,
			@Validated @RequestBody Etudiant EtudiantDetails) throws ResourceNotFoundException {
		Etudiant etudiant = etudiantRepository.findById(EtudiantId)
				.orElseThrow(() -> new ResourceNotFoundException("Etudiant not found for this id :: " + EtudiantId));

		etudiant.setIne(EtudiantDetails.getIne());
		etudiant.setNumDossier(EtudiantDetails.getNumDossier());
		etudiant.setFiliere(EtudiantDetails.getFiliere());
		etudiant.setAnnee(EtudiantDetails.getAnnee());
		etudiant.setNiveau(EtudiantDetails.getNiveau());
		etudiant.setCodifiable(EtudiantDetails.getCodifiable());

		final Etudiant updatedEtudiant = etudiantRepository.save(etudiant);
		return ResponseEntity.ok(updatedEtudiant);
	}

	@GetMapping("/etudiants/user/{email}")
	public ResponseEntity<Etudiant> getEtudiantByUser(@PathVariable(value = "email") String email)
			throws ResourceNotFoundException {
		System.out.println("user " + email);
		Optional<UserModel> user = userRepo.findByEmail(email);

		Etudiant medecin = etudiantRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("medecin non trouvé  :: " + user.toString()));
		return ResponseEntity.ok().body(medecin);
	}

	@PutMapping("/etudiants/codification/{id}")
	public HashMap<String, Boolean> addCodifcation(@PathVariable(value = "id") Integer id,
			@Validated @RequestBody Integer numero) {
		System.out.println("chambre " + numero);
		System.out.println("etudiant " + id);
		Etudiant etudiant = new Etudiant();
		try {
			etudiant = etudiantRepository.findById(id)
					.orElseThrow(() -> new ResourceNotFoundException("Etudiant not found for this id : " + id));
		} catch (ResourceNotFoundException e) {
			e.printStackTrace();
		}
		Chambre c = chambreRepository.findByNumero(numero);

		Collection<Periode> LP = periodeRepository.findAll();
		Periode periode = new Periode();
		for (Periode per : LP) {
			periode = per;
		}

		etudiantRepository.save(etudiant);

		HashMap<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("codification Réussi", Boolean.TRUE);
		return response;
	}

	@GetMapping("/etudiants/codification")
	public HashMap<String, Boolean> lancerCodification() {
		System.out.println("codification ");
		List<Etudiant> LE = etudiantRepository.findAll();
		List<Chambre> LC = chambreRepository.findAll();

		int i = 0, j = 0;
		while (i != LE.size() && j != LC.size()) {
			int cn = 0;
			while (cn != 2 ) {
				Etudiant e = etudiantRepository.findByNumDossier(LE.get(i).getNumDossier());
				System.out.println("etu "+ e.toString());
				Chambre c = chambreRepository.findByNumero(LC.get(j).getNumero());
				System.out.println("ch "+  c.toString());	
				e.AddChambre(c);
				c.AddEtudiant(e);
				etudiantRepository.save(e);
				chambreRepository.save(c);
				cn++;
				i++;
			}
			j++;
		}
		HashMap<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("codification Réussi", Boolean.TRUE);
		return response;
	}

	
	@GetMapping("/etudiants/chambre/{email}")
	public ResponseEntity<Chambre> getChambre(@PathVariable(value = "email") String email)
			throws ResourceNotFoundException {
		System.out.println("user " + email);
		Optional<UserModel> user = userRepo.findByEmail(email);

		Etudiant etudiant = etudiantRepository.findByUser(user)
				.orElseThrow(() -> new ResourceNotFoundException("medecin non trouvé  :: " + user.toString()));
		
		Chambre chambre = chambreRepository.findByEtudiant(etudiant);
		
		return ResponseEntity.ok().body(chambre);
	}

}
