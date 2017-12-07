package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bll.RdvManager;
import fr.eni.cliniqueVeterinaire.bo.Personnel;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;

public class EcranAgenda extends JFrame {

	private JLabel lblVeto;
	private JLabel lblDate;
	private JFormattedTextField formattedTextField;
	private JButton btnDossierMedical;
	private JComboBox comboBoxVeto;
	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	private RdvManager rdvManager = RdvManager.getInstance();
	private JTable table;
	private Personnel personnel;
	private ModeleRdv modeleRdv;
	private JTable table_1;

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
		getContentPane().add(getFormattedTextField());
		getContentPane().add(getBtnDossierMedical());
		getContentPane().add(getComboBoxVeto());
		getContentPane().add(getTable());
		
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
				comboBoxVeto.setBounds(79, 8, 97, 20);
				
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
			lblDate.setBounds(196, 11, 32, 14);
		}
		return lblDate;
	}
	private JFormattedTextField getFormattedTextField() {
		if (formattedTextField == null) {
			formattedTextField = new JFormattedTextField();
			formattedTextField.setToolTipText("JJ/MM/AAAA");
			formattedTextField.setText("  /  /    ");
			formattedTextField.setBounds(238, 8, 46, 20);
		}
		return formattedTextField;
	}
	private JButton getBtnDossierMedical() {
		if (btnDossierMedical == null) {
			btnDossierMedical = new JButton("Dossier Medical");
			btnDossierMedical.addActionListener(new ActionListener(){	@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						EcranDossierMedical frame = new EcranDossierMedical();
						frame.setVisible(true);
					}
				});
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

}
