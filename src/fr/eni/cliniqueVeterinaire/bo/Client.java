package fr.eni.cliniqueVeterinaire.bo;

public class Client {

private String codeClient;
private String nomClient;
private String prenomClient;
private String adresse1;
private String adresse2;
private String codePostal;
private String ville;
private String numTel;
private String assurance;
private String Email;
private String remarque;



public String getNomClient() {
	return nomClient;
}
public void setNomClient(String nomClient) {
	this.nomClient = nomClient;
}
public String getPrenomClient() {
	return prenomClient;
}
public void setPrenomClient(String prenomClient) {
	this.prenomClient = prenomClient;
}
public String getAdresse1() {
	return adresse1;
}
public void setAdresse1(String adresse1) {
	this.adresse1 = adresse1;
}
public String getAdresse2() {
	return adresse2;
}
public void setAdresse2(String adresse2) {
	this.adresse2 = adresse2;
}
public String getCodePostal() {
	return codePostal;
}
public void setCodePostal(String codePostal) {
	this.codePostal = codePostal;
}
public String getVille() {
	return ville;
}
public void setVille(String ville) {
	this.ville = ville;
}
public String getNumTel() {
	return numTel;
}
public void setNumTel(String numTel) {
	this.numTel = numTel;
}
public String getAssurance() {
	return assurance;
}
public void setAssurance(String assurance) {
	this.assurance = assurance;
}
public String getEmail() {
	return Email;
}
public void setEmail(String eMail) {
	this.Email = eMail;
}
public String getRemarque() {
	return remarque;
}
public void setRemarque(String remarque) {
	this.remarque = remarque;
}

public Client() {
	
}
public String getCodeClient() {
	return codeClient;
}
public void setCodeClient(String codeClient) {
	this.codeClient = codeClient;
}
public Client(String codeClient, String nomClient, String prenomClient, String adresse1, String adresse2,
		String codePostal, String ville, String numTel, String assurance, String email, String remarque) {
	super();
	this.codeClient = codeClient;
	this.nomClient = nomClient;
	this.prenomClient = prenomClient;
	this.adresse1 = adresse1;
	this.adresse2 = adresse2;
	this.codePostal = codePostal;
	this.ville = ville;
	this.numTel = numTel;
	this.assurance = assurance;
	Email = email;
	this.remarque = remarque;
}






}
