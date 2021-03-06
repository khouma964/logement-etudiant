package com.ut.sn.Service;

public class JwtResponse {
	
	private String token;
	private Integer id;
	private String password;
	private String email;
	private String prenom;
	private String nom;
	private String role;
	
	public JwtResponse(String token, Integer id, String password, String email, String prenom, String nom
			,String role) {
		super();
		this.token = token;
		this.id = id;
		this.password = password;
		this.email = email;
		this.nom = nom;
		this.prenom = prenom;
		this.role = role;
	}
	public JwtResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	
	
	
}
