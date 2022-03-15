package com.ismo.brevets.models;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Domaine {

	@Id
	@GeneratedValue
	@Column(name="NUM_DOMAINE")
	private int num;
	
	@Column(name="NOM_DOMAINE")
	private String nom;
	
	@OneToMany(mappedBy="domaine", fetch=FetchType.EAGER)
	private List<Invention> inventions;
	
	public Domaine() {
		
	}
	
	public Domaine(int num, String nom) {
		super();
		this.num = num;
		this.nom = nom;
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

	public List<Invention> getInventions() {
		return inventions;
	}

	public void setInventions(List<Invention> inventions) {
		this.inventions = inventions;
	}
}
