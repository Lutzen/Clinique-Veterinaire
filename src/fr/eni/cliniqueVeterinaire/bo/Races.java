package fr.eni.cliniqueVeterinaire.bo;

public class Races {
	private String espece;
	private String race;

	public String getEspece() {
		return espece;
	}

	public void setEspece(String espece) {
		this.espece = espece;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public Races(String espece, String race) {
		super();
		this.race = race;
		this.espece = espece;

	}

	public Races() {

	}

	@Override
	public String toString() {
		return "Races [espece=" + espece + ", race=" + race + "]";
	}

}
