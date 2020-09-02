package com.example.enseignant_gsa;

import android.R.drawable;
import android.graphics.drawable.Drawable;

public class Etudiant {

	String nom;
	String prenom;
	int num;
	Drawable logo;
	String date;
	String seance;


	public String getSeance() {
		return seance;
	}
	public void setSeance(String seance) {
		this.seance = seance;
	}
	boolean selected;
	
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	public String getIdseance() {
		return date;
	}
	public void setIdseance(String idseance) {
		this.date = idseance;
	}
	public Drawable getLogo() {
		return logo;
	}
	public void setLogo(Drawable logo) {
		this.logo = logo;
	}
	
	public Etudiant(String nom, String prenom, int num , Drawable logo,String date,String idseance,boolean selected) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.num = num;
		this.logo = logo;
		this.date=date;
		this.selected=selected;
		this.seance=idseance;
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
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
}
