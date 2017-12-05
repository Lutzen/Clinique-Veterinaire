package fr.eni.cliniqueVeterinaire.bll;

import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Personnel;
import fr.eni.cliniqueVeterinaire.bo.Rdv;
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

		public List<Personnel> getAnimalList(int codeClient) throws BLLException {
			try {
				return AnimalDAO.selectByClient(codeClient);
			} catch (Exception e) {
				throw new BLLException("getAnimalList", e);
			}

		}

		public int addAnimal(Animal animal) throws BLLException {
			try {
				return AnimalDAO.insert(animal);
			} catch (Exception e) {
				throw new BLLException("addAnimal", e);
			}

		}

		public void updatePersonnel(Animal animal) throws BLLException {
			try {
				AnimalDAO.update(animal);

			} catch (Exception e) { 
				throw new BLLException("updateAnimal", e);
			}

		}

		public void deleteAnimal(Animal animal) throws BLLException {
			try {
				AnimalDAO.delete(animal);
			} catch (Exception e) {
				throw new BLLException("deleteAnimal", e);
			}

		}

//		public void validerArticle(Article a) throws BLLException {
//			try {
//			
//				if (a instanceof Stylo) {
//					if (empty(((Stylo) a).getCouleur()))
//						throw new BLLException("Couleur manquante");
//				}
//				if (a instanceof Ramette) {
//					if (((Ramette) a).getGrammage() <= 0)
//						throw new BLLException("Grammage manquant");
//				}
//				if(empty(a.getMarque())) 
//					throw new BLLException("Marque manquante");
//				if(empty(a.getDesignation())) 
//					throw new BLLException("Designation manquante");
//				if(empty(a.getReference())) 
//					throw new BLLException("Reference manquante");
//				if(a.getPrixUnitaire() <= 0) 
//					throw new BLLException("Prix unitaire manquant");
//				if(a.getQteStock() <= 0) 
//					throw new BLLException("Quantite stock manquante");
//				
//				
//			} catch (Exception e) {
//				throw new BLLException("validerArticle", e);
	//
//			}
	//
//		}


		
		public static boolean empty( String s ) {
			  // Null-safe, short-circuit evaluation.
			  return s == null || s.trim().isEmpty();
			}

	}



