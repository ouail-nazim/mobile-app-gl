package com.example.gsa;
public class Absence {
String module ;
String seance ;
String enseignant;
String date ;

public Absence(String module, String seance, String enseignant, String date) {
	super();
	this.module = module; 
	this.seance = seance;
	this.enseignant = enseignant; 
	this.date = date;
}
public String getModule() {
	return module;
}
public void setModule(String module) {
	this.module = module;
}
public String getSeance() {
	return seance;
}
public void setSeance(String seance) {
	this.seance = seance;
}
public String getEnseignant() {
	return enseignant;
}
public void setEnseignant(String enseignant) {
	this.enseignant = enseignant;
}
public String getDate() {
	return date;
}
public void setDate(String date) {
	this.date = date;
}

}
