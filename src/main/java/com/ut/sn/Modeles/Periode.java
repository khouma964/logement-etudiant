package com.ut.sn.Modeles;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "periode")
public class Periode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private Integer jour;

	private Integer mois;

	private Integer annee;


	public Periode() {
	}

	public Periode(Integer jour, Integer mois, Integer annee) {
		this.jour = jour;
		this.mois = mois;
		this.annee = annee;
	}

	public Integer getJour() {
		return jour;
	}

	public Integer getMois() {
		return mois;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setJour(Integer jour) {
		this.jour = jour;
	}

	public void setMois(Integer mois) {
		this.mois = mois;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Periode [id=" + id + ", jour=" + jour + ", mois=" + mois + ", annee=" + annee + "]";
	}

//	public Set<Etudiant> getEtudiant() {
//		return etudiant;
//	}
//	public void setEtudiant(Set<Etudiant> etudiant) {
//		this.etudiant = etudiant;
//	}
}
