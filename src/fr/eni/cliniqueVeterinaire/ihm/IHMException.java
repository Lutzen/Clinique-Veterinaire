package fr.eni.cliniqueVeterinaire.ihm;

public class IHMException extends Exception {
	//Constructeurs
	public IHMException() {
		super();
	}
	
	public IHMException(String message) {
		super(message);
	}
	
	public IHMException(String message, Throwable exception) {
		super(message, exception);
	}

	//Méthodes
	@Override
	public String getMessage() {
		StringBuffer sb = new StringBuffer("Couche IHM - ");
		sb.append(super.getMessage());
		
		return sb.toString() ;
	}
	
	
}
