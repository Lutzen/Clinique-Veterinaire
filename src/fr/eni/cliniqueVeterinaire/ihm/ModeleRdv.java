package fr.eni.cliniqueVeterinaire.ihm;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.toedter.calendar.JDateChooser;

import fr.eni.cliniqueVeterinaire.bll.AnimalManager;
import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bll.RdvManager;
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
	private  List<Rdv> donnees;
	private RdvManager rdvManager = RdvManager.getInstance();
	private final String[] entetes = { "Heure", "Client","Animal","Espece" };

	public ModeleRdv(int codeVeto) {
		super();
		setData(codeVeto);
	
	}
	
	public ModeleRdv(int codeVeto,String date) {
		super();
		setData(codeVeto,date);
	
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
			Animal animal = animalManager.getAnimalByCode(rdv.getCodeAnimal());
			Client client = clientManager.getClientByCode(animal.getCodeClient());
			
			
			Object result = null;
			
			switch(columnIndex) {
			case 0:
				result= dateToString(rdv.getDateRdv());
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
	
	private String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
		String startDateString = dateFormat.format(date);
		return startDateString;
		
	}
	
	
	public void setData(int codeVeto) {
		try {
			this.donnees = rdvManager.getAgenda(codeVeto);
	
		} catch (BLLException e) {
			e.printStackTrace();
		}
		fireTableDataChanged();
		
	}
	
	public void setData(int codeVeto,String date) {
		try {
			this.donnees = rdvManager.getAgendaDate(codeVeto, date);
	
		} catch (BLLException e) {
			e.printStackTrace();
		}
		fireTableDataChanged();
		
	}

}
