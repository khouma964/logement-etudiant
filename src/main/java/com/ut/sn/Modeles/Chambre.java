package com.ut.sn.Modeles;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "chambre")
public class Chambre implements Serializable {

	private static final long serialVersionUID = 1L;

	public Chambre(String pavillon, Integer numero) {
		this.pavillon = pavillon;
		this.numero = numero;
	}

	@Id
	@Column(name = "id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "pavillon", nullable = false)
	private String pavillon;
	
	@Column(name = "numero", nullable = false)
	private Integer numero;

	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinTable(name = "etudiant_chambre", joinColumns = { @JoinColumn(name = "chambre_id") }, inverseJoinColumns = {
			@JoinColumn(name = "etudiant_id") })
	public Set<Etudiant> etudiant = new HashSet<Etudiant>();

	public Chambre() {
	}
	public String getPavillon() {
		return pavillon;
	}
	public void setPavillon(String pavillon) {
		this.pavillon = pavillon;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getNumero() {
		return numero;
	}
	@Override
	public String toString() {
		return "Chambre [id=" + id + ", pavillon=" + pavillon + ", numero=" + numero + "]";
	}

	public Integer getId() {
		return id;
	}
	public void AddEtudiant(Etudiant et) {
		this.etudiant.add(et);
	}
}
