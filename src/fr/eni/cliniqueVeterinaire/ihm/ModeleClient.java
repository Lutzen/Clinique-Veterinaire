package fr.eni.cliniqueVeterinaire.ihm;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bo.Client;

public class ModeleClient extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private ClientManager clientManager = ClientManager.getInstance();

	private List<Client> donnees;

	private final String[] entetes = {  "Nom Prenom",  "Code Postal", "Ville" };

	public ModeleClient() throws BLLException {
		super();
		donnees=clientManager.getClientList();
	
	}

	public int getRowCount() {
		return donnees.size();
	}

	public int getColumnCount() {
		return entetes.length;
	}

	public String getColumnName(int columnIndex) {
		return entetes[columnIndex];
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		Client client = donnees.get(rowIndex);
		Object result = null;

		switch (columnIndex) {
		case 0:
			result = client.getNomClient();
			break;
		case 1:
			result = client.getCodePostal();
			break;
		case 2:
			result = client.getVille();
			break;

		}

		return result;
	}

	public List<Client> setData(String motCle) {
		try {
			System.out.println(donnees);
			this.donnees = clientManager.getClientByMotCle(motCle);
			super.fireTableDataChanged();
			
		} catch (BLLException e) {
			e.printStackTrace();
		}
		return donnees;
		

	}

}
