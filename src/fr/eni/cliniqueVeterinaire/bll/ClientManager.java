package fr.eni.cliniqueVeterinaire.bll;

import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.dal.ClientDAO;
import fr.eni.cliniqueVeterinaire.dal.DAOFactory;

public class ClientManager {

	
	private static ClientManager clientManager = null;
	public ClientDAO clientDAO = DAOFactory.getClientDAO();

	private ClientManager() {

	}

	public static ClientManager getInstance() {
	
			if (clientManager == null)
				clientManager = new ClientManager();

			return clientManager;
	

	}

	public List<Client> getClientList() throws BLLException {
		try {
			return clientDAO.selectAll();
		} catch (Exception e) {
			throw new BLLException("getClientList", e);
		}

	}
	
	public List<Client> getClientByMotCle(String string) throws BLLException {
		try {
			return clientDAO.selectByMotCle(string);
		} catch (Exception e) {
			throw new BLLException("getClientList", e);
		}

	}
	
	public Client getClientByCode(int code) throws BLLException {
		try {
			return clientDAO.selectByCode(code);
		} catch (Exception e) {
			throw new BLLException("getClientByCode", e);
		}

	}
	
	
	public Client getClientByName(String nom) throws BLLException {
		try {
			return clientDAO.selectByNom(nom);
		} catch (Exception e) {
			throw new BLLException("getClientList", e);
		}

	}

	public void addClient(Client client) throws BLLException {
		try {
			clientDAO.insert(client);
		} catch (Exception e) {
			throw new BLLException("addClient", e);
		}

	}

	public void updateClient(Client client) throws BLLException {
		try {
			clientDAO.update(client);

		} catch (Exception e) { 
			throw new BLLException("updateClient", e);
		}

	}

	public void deleteClient(int codeClient) throws BLLException {
		try {
			clientDAO.delete(codeClient);
		} catch (Exception e) {
			throw new BLLException("deleteClient", e);
		}

	}



}





