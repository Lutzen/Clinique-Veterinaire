package fr.eni.cliniqueVeterinaire.dal;

import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Client;


public interface ClientDAO {
	
	List<Client> selectAll() throws DALException;
	
	Client selectByNom(String nom) throws DALException;
	
	void delete(int codeClient) throws DALException;
	
	void insert(Client client)  throws DALException;
	
	void update(Client client)throws DALException;
	
	Client selectByCode(int codeClient) throws DALException;
	
	List<Client> selectByMotCle(String nom) throws DALException;
	


}
