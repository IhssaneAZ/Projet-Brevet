package com.ismo.brevets.models;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Table
@Entity
public class Brevet {

	@Id
	@GeneratedValue
	@Column(name="NUM_BREVET")
	private int num;
	
	@Column
	private String description;
	
	@Column(name="DATE_DEPOT")
	private LocalDate dateDepot;
	
	@Column(name="DATE_VALIDATION")
	private LocalDate dateValidation;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NUM_INVENTION")
	private Invention invention;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NUM_INVENTEUR")
	private Inventeur inventeur;
	
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public LocalDate getDateDepot() {
		return dateDepot;
	}
	public void setDateDepot(LocalDate dateDepot) {
		this.dateDepot = dateDepot;
	}
	public LocalDate getDateValidation() {
		return dateValidation;
	}
	public void setDateValidation(LocalDate dateValidation) {
		this.dateValidation = dateValidation;
	}
	public Invention getInvention() {
		return invention;
	}
	public void setInvention(Invention invention) {
		this.invention = invention;
	}
	public Inventeur getInventeur() {
		return inventeur;
	}
	public void setInventeur(Inventeur inventeur) {
		this.inventeur = inventeur;
	}
	
	
	
}
