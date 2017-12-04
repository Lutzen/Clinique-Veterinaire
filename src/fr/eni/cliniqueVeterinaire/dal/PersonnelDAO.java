package fr.eni.cliniqueVeterinaire.dal;

import java.util.List;


import fr.eni.cliniqueVeterinaire.bo.Personnel;


public interface PersonnelDAO {
	
	List<Personnel> selectByNom(String nom) throws DALException;
	
	void deletePersonnel(Personnel personne);
	
	void updatePass(Personnel personne);
}
