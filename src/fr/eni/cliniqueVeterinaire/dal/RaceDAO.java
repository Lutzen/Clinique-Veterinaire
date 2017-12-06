package fr.eni.cliniqueVeterinaire.dal;

import java.util.List;


import fr.eni.cliniqueVeterinaire.bo.Races;

public interface RaceDAO {

	List<Races> selectByEspece(String espece) throws DALException;
	List<Races> selectAll() throws DALException;
}
