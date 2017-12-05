package fr.eni.cliniqueVeterinaire.bll;

import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Personnel;
import fr.eni.cliniqueVeterinaire.dal.DAOFactory;

public class ClientManager {

	
	private static ClientManager clientManager = null;
	public clientDAO clientDAO = DAOFactory.getClientDAO();

	private ClientManager() {

	}

	public static ClientManager getInstance() {
	
			if (clientManager == null)
				clientManager = new ClientManager();

			return clientManager;
	

	}

	public List<Client> getClientList(Client client) throws BLLException {
		try {
			return clientDAO.selectByClient(client);
		} catch (Exception e) {
			throw new BLLException("getClientList", e);
		}

	}

	public int addClient(Client client) throws BLLException {
		try {
			return clientDAO.insert(client);
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

	public void deleteClient(Client client) throws BLLException {
		try {
			clientDAO.delete(client);
		} catch (Exception e) {
			throw new BLLException("deleteClient", e);
		}

	}

//	public void validerArticle(Article a) throws BLLException {
//		try {
//		
//			if (a instanceof Stylo) {
//				if (empty(((Stylo) a).getCouleur()))
//					throw new BLLException("Couleur manquante");
//			}
//			if (a instanceof Ramette) {
//				if (((Ramette) a).getGrammage() <= 0)
//					throw new BLLException("Grammage manquant");
//			}
//			if(empty(a.getMarque())) 
//				throw new BLLException("Marque manquante");
//			if(empty(a.getDesignation())) 
//				throw new BLLException("Designation manquante");
//			if(empty(a.getReference())) 
//				throw new BLLException("Reference manquante");
//			if(a.getPrixUnitaire() <= 0) 
//				throw new BLLException("Prix unitaire manquant");
//			if(a.getQteStock() <= 0) 
//				throw new BLLException("Quantite stock manquante");
//			
//			
//		} catch (Exception e) {
//			throw new BLLException("validerArticle", e);
//
//		}
//
//	}


	
	public static boolean empty( String s ) {
		  // Null-safe, short-circuit evaluation.
		  return s == null || s.trim().isEmpty();
		}

}





