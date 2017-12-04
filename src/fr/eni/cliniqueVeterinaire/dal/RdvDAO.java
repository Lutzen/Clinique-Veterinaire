package fr.eni.cliniqueVeterinaire.dal;

import java.sql.Date;
import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Rdv;

//import cliniqueVeterinaire.bo.Article;

public interface RdvDAO {


	List<Rdv> selectById(int codeVeto) throws DALException;

	void insert(Client client,Animal animal) throws DALException;

	void update(int codeVeto,Rdv dateRdv,Animal animal,Date newDate) throws DALException;

	void delete(int codeVeto,Date dateRdv,Animal animal) throws DALException;

	
	
	
	
}
