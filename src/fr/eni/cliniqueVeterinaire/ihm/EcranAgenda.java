package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bll.RdvManager;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Personnel;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

public class EcranAgenda extends JFrame {

	private JLabel lblVeto;
	private JLabel lblDate;
	private JButton btnDossierMedical;
	private JComboBox comboBoxVeto;
	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	private RdvManager rdvManager = RdvManager.getInstance();
	private ClientManager clientManager = ClientManager.getInstance();
	private JTable table;
	private Personnel personnel;
	private ModeleRdv modeleRdv;
	private JTable table_1;
	private JDateChooser dateChooser;
	private JScrollPane scrollPane;
	

	

	
	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public EcranAgenda() {

		setTitle("Agenda");
		setBounds(100, 100, 454, 390);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblVeto());
		getContentPane().add(getLblDate());
		getContentPane().add(getBtnDossierMedical());
		getContentPane().add(getComboBoxVeto());
		getContentPane().add(getTable());
		getContentPane().add(getDateChooser());
		getContentPane().add(getScrollPane());

		

		
	}

	
	private JComboBox getComboBoxVeto() {
		if (comboBoxVeto == null) {
			try {
				List<Personnel> personnel =personnelManager.getPersonnelByRole("vet");
				comboBoxVeto= new JComboBox(new String[]{});
				for (int i = 0; i < personnel.size(); i++) {
					comboBoxVeto.addItem(personnel.get(i).getNom());
					comboBoxVeto.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							mettreAJour();

						}});

				}
				comboBoxVeto.setBounds(79, 8, 136, 20);
				
			} catch (BLLException e) {
				
				e.printStackTrace();
			}
			
		}
		return comboBoxVeto;
	}

	private JLabel getLblVeto() {
		if (lblVeto == null) {
			lblVeto = new JLabel("Vétérinaire");
			lblVeto.setBounds(10, 11, 59, 14);
		}
		return lblVeto;
	}
	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("Date");
			lblDate.setBounds(235, 11, 32, 14);
		}
		return lblDate;
	}
	private JButton getBtnDossierMedical() {
		if (btnDossierMedical == null) {
			btnDossierMedical = new JButton("Dossier Medical");
			btnDossierMedical.addActionListener(new ActionListener(){	@Override
			public void actionPerformed(ActionEvent e) {
				
						int row = table.getSelectedRow();
						System.out.println("row:"+ row);
						
						
						if (row  == -1) {
							JOptionPane.showMessageDialog(null, "Selectionnez un client");
						}
						else {
							SwingUtilities.invokeLater(new Runnable() {
								
								@Override
								public void run() {
						 try {
							Client client ;
							String nom = (String) table.getValueAt(row, 1);
							client = clientManager.getClientByName(nom);
							 EcranDossierMedical frame = new EcranDossierMedical(client);
								frame.setVisible(true);
						} catch (BLLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
								}});
						}
						
				
			}
			});
			
			
			btnDossierMedical.setIcon(new ImageIcon(EcranAgenda.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
			btnDossierMedical.setBounds(277, 317, 151, 23);
		}
		return btnDossierMedical;
	}
	private JTable getTable() {
		if (table == null) {

			try {
				table = new JTable(getModeleRdv());
				comboBoxVeto.setSelectedIndex(0);
				table.setBounds(10, 36, 418, 273);
			} catch (Exception e) {
			}

		}
		return table;

	}

	public void mettreAJour() {

		try {
			personnel = personnelManager.getPersonnelByNom((String) comboBoxVeto.getSelectedItem());
			modeleRdv.setData(personnel.getCodePers());

		} catch (BLLException e) {

			e.printStackTrace();
		}
	}

	private ModeleRdv getModeleRdv() {
		if (modeleRdv == null) {
			try {
				personnel = personnelManager.getPersonnelByNom((String) comboBoxVeto.getSelectedItem());
				
				System.out.println(personnel);
				modeleRdv = new ModeleRdv(personnel.getCodePers());
			} catch (BLLException e) {
				e.printStackTrace();
			}

		}
		return modeleRdv;
	}
	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser(new Date());
			dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
			          @Override
			          public void propertyChange(PropertyChangeEvent evt) {
			              Date date = dateChooser.getDate();
			              System.out.println("date: "+date);
			          }
			      });
			      
			dateChooser.setBounds(277, 8, 124, 20);
		}
		return dateChooser;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 36, 418, 273);
		}
		return scrollPane;
	}
}
