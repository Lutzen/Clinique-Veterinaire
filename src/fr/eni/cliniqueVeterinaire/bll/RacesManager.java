package fr.eni.cliniqueVeterinaire.bll;

import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Races;
import fr.eni.cliniqueVeterinaire.dal.DAOFactory;
import fr.eni.cliniqueVeterinaire.dal.RaceDAO;

public class RacesManager {
	private static RacesManager raceManager = null;
	public RaceDAO raceDAO = DAOFactory.getRaceDAO();

	private RacesManager() {

	}

	public static RacesManager getInstance() {

		if (raceManager == null)
			raceManager = new RacesManager();

		return raceManager;
	}

	public List<Races> getRacesList() throws BLLException {
		try {
			return raceDAO.selectAll();
		} catch (Exception e) {
			throw new BLLException("getClientList", e);
		}

	}
	
	public List<Races> getRacesByEspece(String string) throws BLLException {
		try {
			return raceDAO.selectByEspece(string);
		} catch (Exception e) {
			throw new BLLException("getClientList", e);
		}

	}
	
	
}
