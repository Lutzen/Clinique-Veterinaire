package fr.eni.cliniqueVeterinaire.dal;

import java.util.List;


import fr.eni.cliniqueVeterinaire.bo.Personnel;


public interface PersonnelDAO {
	
	List<Personnel> selectByNom(String nom) throws DALException;
	
	void delete(Personnel personne);
	
	void update(Personnel personne);
	
	void selectAll();
}
