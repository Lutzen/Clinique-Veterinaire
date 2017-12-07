package fr.eni.cliniqueVeterinaire.bo;

public class Personnel {
@Override
	public String toString() {
		return "Personnel [nom=" + nom + ", pass=" + pass + ", role=" + role + ", codePers=" + codePers + "]";
	}
private String nom;
private String pass;
private String role;
private int codePers;





public int getCodePers() {
	return codePers;
}
public void setCodePers(int codePers) {
	this.codePers = codePers;
}
public String getNom() {
	return nom;
}
public void setNom(String nom) {
	this.nom = nom;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public String getRole() {
	return role;
}
public void setRole(String role) {
	this.role = role;
}

public Personnel() {
	
}

public Personnel(String nom, String pass, String role) {
	super();
	this.nom = nom;
	this.pass = pass;
	this.role = role;
}


}
