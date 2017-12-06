package fr.eni.cliniqueVeterinaire.dal;

import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Animal;


public interface AnimalDAO {
	

	List<Animal> selectByClient(Long codeClient) throws DALException;
	
	void delete(Animal animal) throws DALException;
	
	void insert(Animal animal)  throws DALException;
	
	void update(Animal animal)throws DALException;
	
	Animal selectByCode(Long codeAnimal) throws DALException;

}
