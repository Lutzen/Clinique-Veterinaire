package fr.eni.cliniqueVeterinaire.bll;

import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Personnel;
import fr.eni.cliniqueVeterinaire.dal.DAOFactory;
import fr.eni.cliniqueVeterinaire.dal.PersonnelDAO;





public class PersonnelManager {
	private static PersonnelManager personnelManager = null;
	public PersonnelDAO personnelDAO = DAOFactory.getPersonnelDAO();

	private PersonnelManager() {

	}

	public static PersonnelManager getInstance() {
	
			if (personnelManager == null)
				personnelManager = new PersonnelManager();

			return personnelManager;
	

	}
	public Personnel getPersonnelByNom(String nom) throws BLLException {
		try {
			return personnelDAO.selectByNom(nom);
		} catch (Exception e) {
			throw new BLLException("getPersonnelByNom", e);
		}

	}
	
	public List<Personnel> getPersonnelByRole(String role) throws BLLException {
		try {
			return personnelDAO.selectByRole(role);
		} catch (Exception e) {
			throw new BLLException("getPersonnelByRole", e);
		}

	}
	


	public List<Personnel> getPersonnelList() throws BLLException {
		try {
			return personnelDAO.selectAll();
		} catch (Exception e) {
			throw new BLLException("getPersonnelList", e);
		}

	}

	public void addPersonnel(Personnel personnel) throws BLLException {
		try {
			personnelDAO.insert(personnel);
		} catch (Exception e) {
			throw new BLLException("addPersonnel", e);
		}

	}

	public void updatePersonnel(Personnel personnel, String mdp) throws BLLException {
		try {
			personnelDAO.update(personnel, mdp);

		} catch (Exception e) {
			throw new BLLException("updatePersonnel", e);
		}

	}

	public void deletePersonnel(Personnel personnel) throws BLLException {
		try {
			personnelDAO.delete(personnel);
		} catch (Exception e) {
			throw new BLLException("deletePersonnel", e);
		}

	}



}




