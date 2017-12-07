package fr.eni.cliniqueVeterinaire.ihm;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bo.Personnel;

public class ModelePersonnel extends AbstractTableModel {
	/**
		 * 
		 */
	
	private static final long serialVersionUID = 1L;

	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	
	private  List<Personnel> donnees;
	
	private final String[] entetes = { "Nom", "Role" };

	public ModelePersonnel() throws BLLException {
		super();
		setData();
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
	
	public void setData() {
		try {
			this.donnees = personnelManager.getPersonnelList();
		} catch (BLLException e) {
			e.printStackTrace();
		}
		super.fireTableDataChanged();
		
		}
		
}
