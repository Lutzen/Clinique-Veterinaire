package fr.eni.cliniqueVeterinaire.dal;


public class DAOFactory {

	public static RdvDAO getRdvDAO() {
		return new RdvDAOJdbcImpl();
	}
	public static AnimalDAO getAnimalDAO() {
		return new AnimalDAOJdbcImpl();
	}
	public static ClientDAO getClientDAO() {
		return new ClientDAOJdbcImpl();
	}
	public static PersonnelDAO getPersonnelDAO() {
		return new PersonnelDAOJdbcImpl();
	}
	public static RaceDAO getRaceDAO() {
		return new RaceDAOJdbcImpl();
	}
}
