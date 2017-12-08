package fr.eni.cliniqueVeterinaire.bo;

import java.util.Date;

public class Rdv {
	private int codeVeto;
	private Date dateRdv;
	private int codeAnimal;

	public int getCodeVeto() {
		return codeVeto;
	}

	public void setCodeVeto(int codeVeto) {
		this.codeVeto = codeVeto;
	}

	public Date getDateRdv() {
		return dateRdv;
	}

	public void setDateRdv(Date dateRdv) {
		this.dateRdv = dateRdv;
	}

	public int getCodeAnimal() {
		return codeAnimal;
	}

	public void setCodeAnimal(int codeAnimal) {
		this.codeAnimal = codeAnimal;
	}

	public Rdv() {

	}

	public Rdv(int codeVeto, Date dateRdv, int codeAnimal) {
		super();
		this.codeVeto = codeVeto;
		this.dateRdv = dateRdv;
		this.codeAnimal = codeAnimal;
	}

	@Override
	public String toString() {
		return "Rdv [codeVeto=" + codeVeto + ", dateRdv=" + dateRdv + ", codeAnimal=" + codeAnimal + "]";
	}

}
