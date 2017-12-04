package fr.eni.cliniqueVeterinaire.dal;

import java.util.List;

import cliniqueVeterinaire.bo.Animal;
import cliniqueVeterinaire.bo.Client;
import cliniqueVeterinaire.bo.Personnel;
import cliniqueVeterinaire.bo.Rdv;

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
