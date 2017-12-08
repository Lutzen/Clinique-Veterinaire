package fr.eni.cliniqueVeterinaire.bo;

public class Animal {
private int codeAnimal;	
private String nomAnimal;
private String sexe;
private String couleur;
private String race;
private String espece;
private int codeClient;
private String tatouage;
private String antecedents;



public String getNomAnimal() {
	return nomAnimal;
}
public void setNomAnimal(String nomAnimal) {
	this.nomAnimal = nomAnimal;
}
public String getSexe() {
	return sexe;
}
public void setSexe(String sexe) {
	this.sexe = sexe;
}
public String getCouleur() {
	return couleur;
}
public void setCouleur(String couleur) {
	this.couleur = couleur;
}
public String getRace() {
	return race;
}
public void setRace(String race) {
	this.race = race;
}
public String getEspece() {
	return espece;
}
public void setEspece(String espece) {
	this.espece = espece;
}
public int getCodeClient() {
	return codeClient;
}
public void setCodeClient(int codeClient) {
	this.codeClient = codeClient;
}
public String getTatouage() {
	return tatouage;
}
public void setTatouage(String tatouage) {
	this.tatouage = tatouage;
}
public String getAntecedents() {
	return antecedents;
}
public void setAntecedents(String antecedents) {
	this.antecedents = antecedents;
}

public Animal() {
	
}

public Animal(int codeAnimal, String nomAnimal, String sexe, String couleur, String race, String espece, int codeClient,
		String tatouage, String antecedents) {
	super();
	this.codeAnimal = codeAnimal;
	this.nomAnimal = nomAnimal;
	this.sexe = sexe;
	this.couleur = couleur;
	this.race = race;
	this.espece = espece;
	this.codeClient = codeClient;
	this.tatouage = tatouage;
	this.antecedents = antecedents;
}
public int getCodeAnimal() {
	return codeAnimal;
}
public void setCodeAnimal(int codeAnimal) {
	this.codeAnimal = codeAnimal;
}








}
