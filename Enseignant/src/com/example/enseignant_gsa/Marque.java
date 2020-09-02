package com.example.enseignant_gsa;

public class Marque {
 String id;
 String date ;
 int num;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getDate() {
	return date;
}
public Marque(String id, String date, int num) {
	super();
	this.id = id;
	this.date = date;
	this.num = num;
}
public void setDate(String date) {
	this.date = date;
}
public int getNum() {
	return num;
}
public void setNum(int num) {
	this.num = num;
}
}
