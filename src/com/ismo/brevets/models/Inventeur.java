package com.ismo.brevets.models;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Table
@Entity
public class Inventeur {

	@Id
	@GeneratedValue
	@Column(name="NUM_INVENTEUR")
	private int num;
	
	@Column
	private String nom;
	
	@Column
	private String prenom;
	
	@Column
	private String adresse;
	
	@Column(name="date_naiss")
	private LocalDate date_nais;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NUM_ENTREPRISE")
	private Entreprise entreprise;
	
	@OneToMany(mappedBy="inventeur", fetch=FetchType.EAGER)
	private List<Brevet> brevets;
	
	public Inventeur() {
		super();
	}
	
	public Inventeur(String nom, String prenom, String adresse, LocalDate date_nais, Entreprise entreprise) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.date_nais = date_nais;
		this.entreprise = entreprise;
	}

	public Inventeur(String nom, String prenom, String adresse, LocalDate date_nais) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.adresse = adresse;
		this.date_nais = date_nais;
	}



	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public LocalDate getDate_nais() {
		return date_nais;
	}
	public void setDate_nais(LocalDate date_nais) {
		this.date_nais = date_nais;
	}
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public List<Brevet> getBrevets() {
		return brevets;
	}

	public void setBrevets(List<Brevet> brevets) {
		this.brevets = brevets;
	}
	
	
	
	
}
