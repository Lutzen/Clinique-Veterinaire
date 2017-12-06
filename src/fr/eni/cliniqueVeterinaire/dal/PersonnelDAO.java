package fr.eni.cliniqueVeterinaire.dal;



import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Personnel;


public interface PersonnelDAO {
	
	Personnel selectByNom(String nom) throws DALException;
	
	void delete(Personnel personne) throws DALException;
	
	void update(Personnel personne,String pass)throws DALException;
	
	List<Personnel> selectAll()throws DALException;
	
	void insert(Personnel personne)throws DALException;
	
	List<Personnel> selectbyRole(String role)throws DALException;
}
