package fr.eni.cliniqueVeterinaire.ihm;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cliniqueVeterinaire.bo.Personnel;

public class ModeleStatique extends AbstractTableModel {
	/**
		 * 
		 */
	private static final long serialVersionUID = 1L;

	private final List<Personnel> donnees;
	
	private final String[] entetes = { "Nom", "Role" };

	public ModeleStatique(List<Personnel> donnees) {
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
		Personnel p = donnees.get(rowIndex);
		Object result = null;
		
		switch(columnIndex) {
		case 0:
			result= p.getNom();
			break;
		case 1:
			result = p.getRole();
			break;
		}
			
		return result;
	}
}
