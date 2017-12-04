package fr.eni.cliniqueVeterinaire.dal;

import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Personnel;
import fr.eni.cliniqueVeterinaire.bo.Rdv;

//import cliniqueVeterinaire.bo.Article;

public interface RdvDAO {


	List<Rdv> selectById(int codeVeto) throws DALException;

	void insertRdv(Client client,Animal animal) throws DALException;

	void updateRdv(int codeVeto,Rdv rdv,Animal animal,Rdv newDate) throws DALException;

	void deleteRdv(int codeVeto,Rdv rdv,Animal animal) throws DALException;

	List<Personnel> selectByNom(String nom,String pass,String role) throws DALException;
	
	void deletePersonnel(String nom);
	
	void updatePersonnel(String nom, String newPass);
	
	
	
}
