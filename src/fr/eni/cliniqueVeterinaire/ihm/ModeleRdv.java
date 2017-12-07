package fr.eni.cliniqueVeterinaire.ihm;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cliniqueVeterinaire.bll.AnimalManager;
import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Rdv;

public class ModeleRdv extends AbstractTableModel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AnimalManager animalManager = AnimalManager.getInstance();
	private ClientManager clientManager = ClientManager.getInstance();
	private final List<Rdv> donnees;
	
	private final String[] entetes = { "Heure", "Client","Animal","Espece" };

	public ModeleRdv(List<Rdv> donnees) {
		super();
		this.donnees = donnees;
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
		Rdv rdv = donnees.get(rowIndex);
		try {
			Animal animal = animalManager.getAnimalByCode( rdv.getCodeAnimal());
			Client client = clientManager.getClientByCode(animal.getCodeClient());
			
			
			Object result = null;
			
			switch(columnIndex) {
			case 0:
				result= rdv.getDateRdv();
				break;
			case 1:
				result= client.getNomClient();
				break;
			case 2:
				result= animal.getNomAnimal();
				break;
			case 3:
				result= animal.getEspece();
				break;
				
			}
			return result;

		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		return null;
	
		
	}

}
