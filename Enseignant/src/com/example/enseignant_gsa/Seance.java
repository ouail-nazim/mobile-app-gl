package com.example.enseignant_gsa;

public class Seance {

	String groupe ;
	String specialite;
	String module ;
	String date ;
	String heure ;
	int id ;
	String seance;
	String annee_specialite;
	String section;
	public Seance() {
		// TODO Auto-generated constructor stub
	}
	public Seance(String groupe, String specialite, String module, String date,
			String heure, int id, String seance, String annee_specialite,
			String section) {
		super();
		this.groupe = groupe;
		this.specialite = specialite;
		this.module = module;
		this.date = date;
		this.heure = heure;
		this.id = id;
		this.seance = seance;
		this.annee_specialite = annee_specialite;
		this.section = section;
	} 
	public String getGroupe() {
		return groupe;
	}
	public void setGroupe(String groupe) {
		this.groupe = groupe;
	}
	public String getSpecialite() {
		return specialite;
	}
	public void setSpecialite(String specialite) {
		this.specialite = specialite;
	}
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSeance() {
		return seance;
	}
	public void setSeance(String seance) {
		this.seance = seance;
	}
	public String getAnnee_specialite() {
		return annee_specialite;
	}
	public void setAnnee_specialite(String annee_specialite) {
		this.annee_specialite = annee_specialite;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
}
