package com.ut.sn.Modeles;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "etudiant")
public class Etudiant {

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "ine", nullable = false)
	private String ine;

	@Column(name = "num_dossier", nullable = false, unique = true)
	
	private String numDossier;

	@Column(name = "filiere", nullable = false)
	private String filiere;

	@Column(name = "annee", nullable = false)
	private Integer annee;

	@Column(name = "niveau")
	private String niveau;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "etudiant")
	private Set<Chambre> chambre = new HashSet<Chambre>();


	@OneToOne
	private UserModel user;

	@Column(name = "codifiable", nullable = false)
	private String codifiable;
	@Column(name = "genre", nullable = false)
	private String genre;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIne() {
		return ine;
	}

	public void setIne(String ine) {
		this.ine = ine;
	}

	public String getNumDossier() {
		return numDossier;
	}

	public void setNumDossier(String numDossier) {
		this.numDossier = numDossier;
	}

	public String getFiliere() {
		return filiere;
	}

	public void setFiliere(String filiere) {
		this.filiere = filiere;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public String getNiveau() {
		return niveau;
	}

	public void setNiveau(String niveau) {
		this.niveau = niveau;
	}

	public UserModel getUser() {
		return user;
	}

	public void setUser(UserModel user) {
		this.user = user;
	}

	public String getCodifiable() {
		return codifiable;
	}

	public void setCodifiable(String codifiable) {
		this.codifiable = codifiable;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public Etudiant(String ine, String numDossier, String filiere, Integer annee, String niveau, String codifiable,
			String genre) {
		super();
		this.ine = ine;
		this.numDossier = numDossier;
		this.filiere = filiere;
		this.annee = annee;
		this.niveau = niveau;
		this.codifiable = codifiable;
		this.genre = genre;
	}

	public Etudiant() {
	}

	public void AddChambre(Chambre ch) {
		this.chambre.add(ch);

	}

}


