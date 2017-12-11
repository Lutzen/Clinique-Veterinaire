package fr.eni.cliniqueVeterinaire.dal;

import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Animal;


public interface AnimalDAO {
	

	List<Animal> selectByClient(int codeClient) throws DALException;
	
	void delete(int codeAnimal) throws DALException;
	
	void insert(Animal animal)  throws DALException;
	
	void update(Animal animal)throws DALException;
	
	Animal selectByCode(int codeAnimal) throws DALException;

}
