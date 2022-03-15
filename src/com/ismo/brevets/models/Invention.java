package com.ismo.brevets.models;

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
public class Invention {

	@Id
	@GeneratedValue
	@Column(name="NUM_INVENTION")
	private int num;
	
	@Column
	private String descriptif;
	
	@Column
	private String resume;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="NUM_DOMAINE")
	private Domaine domaine;
	
	@OneToMany(mappedBy="invention", fetch=FetchType.LAZY)
	private List<Brevet> brevets;
	
	public Invention() {
		
	}
	
	public Invention(int num, String descriptif, String resume) {
		super();
		this.num = num;
		this.descriptif = descriptif;
		this.resume = resume;
	}
	
	public Invention(int num, String descriptif, String resume, Domaine domaine) {
		super();
		this.num = num;
		this.descriptif = descriptif;
		this.resume = resume;
		this.domaine = domaine;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getDescriptif() {
		return descriptif;
	}
	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}
	public String getResume() {
		return resume;
	}
	public void setResume(String resume) {
		this.resume = resume;
	}
	public Domaine getDomaine() {
		return domaine;
	}
	public void setDomaine(Domaine domaine) {
		this.domaine = domaine;
	}

	public List<Brevet> getBrevets() {
		return brevets;
	}

	public void setBrevets(List<Brevet> brevets) {
		this.brevets = brevets;
	}
}
