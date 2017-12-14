package fr.eni.cliniqueVeterinaire.bll;

import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Personnel;
import fr.eni.cliniqueVeterinaire.bo.Rdv;
import fr.eni.cliniqueVeterinaire.dal.AnimalDAO;
import fr.eni.cliniqueVeterinaire.dal.DAOFactory;
import fr.eni.cliniqueVeterinaire.dal.RdvDAO;

public class AnimalManager {

		private static AnimalManager animalManager = null;
		public AnimalDAO animalDAO = DAOFactory.getAnimalDAO();

		private AnimalManager() {

		}

		public static AnimalManager getInstance() {
		
				if (animalManager == null)
					animalManager = new AnimalManager();

				return animalManager;
		

		}
		public Animal getAnimalByCode(int code) throws BLLException {
			try {
				return animalDAO.selectByCode(code);
			} catch (Exception e) {
				throw new BLLException("getAnimalByCode", e);
			}

		}

		public List<Animal> getAnimalList(int codeClient) throws BLLException {
			try {
				return animalDAO.selectByClient(codeClient);
			} catch (Exception e) {
				throw new BLLException("getAnimalList", e);
			}

		}

		public void addAnimal(Animal animal) throws BLLException {
			try {
				animalDAO.insert(animal);
			} catch (Exception e) {
				throw new BLLException("addAnimal", e);
			}

		}

		public void updateAnimal(Animal animal) throws BLLException {
			try {
				animalDAO.update(animal);

			} catch (Exception e) { 
				throw new BLLException("updateAnimal", e);
			}

		}

		public void deleteAnimal(int codeAnimal) throws BLLException {
			try {
				animalDAO.delete(codeAnimal);
			} catch (Exception e) {
				throw new BLLException("deleteAnimal", e);
			}

		}


	}



