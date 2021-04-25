package com.ut.sn.Modeles;

import java.time.LocalDateTime;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "user_model")
@XmlRootElement
@NamedQueries({ @NamedQuery(name = "User.findAll", query = "SELECT u FROM UserModel u"),
		@NamedQuery(name = "User.findById", query = "SELECT u FROM UserModel u WHERE u.id = :id"),
		@NamedQuery(name = "User.findByEmail", query = "SELECT u FROM UserModel u WHERE u.email = :email"),
		@NamedQuery(name = "User.findByPassword", query = "SELECT u FROM UserModel u WHERE u.password = :password") })
public class UserModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "id")
	private Integer id;
	@Basic(optional = false)
	@Column(name = "email", unique = true)
	private String email;
	@Basic(optional = false)
	@Column(name = "password")
	private String password;

	@CreationTimestamp
	@Column(name = "created_at", nullable = false, updatable = false)
	private LocalDateTime creatAt;

	private String prenom;
	private String nom;
	private String role;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
	private Etudiant etudiant;

	public UserModel() {
		super();
	}

	public UserModel(Integer id, String email, String password, LocalDateTime creatAt, String prenom, String nom,
			String role) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.creatAt = creatAt;
		this.prenom = prenom;
		this.nom = nom;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreatAt() {
		return creatAt;
	}

	public void setCreatAt(LocalDateTime creatAt) {
		this.creatAt = creatAt;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (id != null ? id.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof UserModel)) {
			return false;
		}
		UserModel other = (UserModel) object;
		if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
			return false;
		}
		return true;
	}

}
