package fr.eni.cliniqueVeterinaire.ihm;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cliniqueVeterinaire.bll.AnimalManager;
import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bo.Animal;

public class ModeleAnimaux extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private AnimalManager animalManager = AnimalManager.getInstance();

	private List<Animal> donnees;

	private final String[] entetes = { "Numero", "Nom", "Sexe", "Couleur", "Espece", "Race", "Tatouage" };

	public ModeleAnimaux(int codeVeto) throws BLLException {
		super();
		setData(codeVeto);
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
		Animal animal = donnees.get(rowIndex);
		Object result = null;

		switch (columnIndex) {
		case 0:
			result = animal.getCodeAnimal();
			break;
		case 1:
			result = animal.getNomAnimal();
			break;

		case 2:
			result = animal.getSexe();
			break;
		case 3:
			result = animal.getCouleur();
			break;

		case 4:
			result = animal.getEspece();
			break;
		case 5:
			result = animal.getRace();
			break;

		case 6:
			result = animal.getTatouage();
			break;

		}

		return result;
	}

	public void setData(int codeClient) {
		try {
				this.donnees = animalManager.getAnimalList(codeClient);
		} catch (BLLException e) {
			e.printStackTrace();
		}
		super.fireTableDataChanged();

	}

}
