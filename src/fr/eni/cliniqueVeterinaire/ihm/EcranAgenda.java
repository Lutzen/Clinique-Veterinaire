package fr.eni.cliniqueVeterinaire.ihm;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Personnel;
import javax.swing.SwingUtilities;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import java.awt.Font;

public class EcranAgenda extends JFrame {

	private JLabel lblVeto;
	private JLabel lblDate;
	private JButton btnDossierMedical;
	private JComboBox comboBoxVeto;
	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	private ClientManager clientManager = ClientManager.getInstance();
	private JTable table;
	private Personnel personnel;
	private ModeleRdv modeleRdv;
	private JDateChooser dateChooser;
	private JScrollPane scrollPane;

	public EcranAgenda() {

		setResizable(false);

		setTitle("Agenda");
		setBounds(500, 200, 668, 465);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblVeto());
		getContentPane().add(getLblDate());
		getContentPane().add(getBtnDossierMedical());
		getContentPane().add(getComboBoxVeto());
		getContentPane().add(getDateChooser());
		getContentPane().add(getTable());
		getContentPane().add(getScrollPane());

	}

	private JComboBox getComboBoxVeto() {
		if (comboBoxVeto == null) {
			try {
				List<Personnel> personnel = personnelManager.getPersonnelByRole("vet");
				comboBoxVeto = new JComboBox(new String[] {});
				for (int i = 0; i < personnel.size(); i++) {
					comboBoxVeto.addItem(personnel.get(i).getNom());
					comboBoxVeto.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							mettreAJour();

						}
					});

				}
				comboBoxVeto.setBounds(79, 8, 136, 28);

			} catch (BLLException e) {

				e.printStackTrace();
			}

		}
		return comboBoxVeto;
	}

	private JLabel getLblVeto() {
		if (lblVeto == null) {
			lblVeto = new JLabel("Vétérinaire");
			lblVeto.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblVeto.setBounds(10, 15, 69, 14);
		}
		return lblVeto;
	}

	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("Date");
			lblDate.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblDate.setBounds(275, 15, 49, 14);
		}
		return lblDate;
	}

	private JButton getBtnDossierMedical() {
		if (btnDossierMedical == null) {
			btnDossierMedical = new JButton("Dossier m\u00E9dical");
			btnDossierMedical.setFont(new Font("SansSerif", Font.BOLD, 12));
			btnDossierMedical.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {

					int row = table.getSelectedRow();
					if (row == -1) {
						JOptionPane.showMessageDialog(null, "Selectionnez un rendez-vous");
					} else {
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								try {
									Client client;
									String nom = (String) table.getValueAt(row, 1);
									client = clientManager.getClientByName(nom);
									String nomAnimal = (String) table.getValueAt(row, 2);
									EcranDossierMedical frame = new EcranDossierMedical(client, nomAnimal);
									frame.setVisible(true);
								} catch (BLLException e) {
									e.printStackTrace();
								}

							}
						});
					}

				}
			});

			btnDossierMedical.setIcon(new ImageIcon(
					"resources\\vet\\medicalHistory32.png"));
			btnDossierMedical.setBounds(476, 385, 164, 40);
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
			modeleRdv.setData(personnel.getCodePers(), dateToString(dateChooser.getDate()));

		} catch (BLLException e) {

			e.printStackTrace();
		}
	}

	private ModeleRdv getModeleRdv() {
		if (modeleRdv == null) {
			try {
				personnel = personnelManager.getPersonnelByNom((String) comboBoxVeto.getSelectedItem());

				System.out.println(personnel);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String startDateString = dateFormat.format(new Date());
				modeleRdv = new ModeleRdv(personnel.getCodePers(), startDateString);
			} catch (BLLException e) {
				e.printStackTrace();
			}

		}
		return modeleRdv;
	}

	private String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startDateString = dateFormat.format(date);
		return startDateString;

	}

	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser(new Date());
			
			JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
			editor.setEditable(false);
			
			dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					mettreAJour();
				}
			});

			dateChooser.setBounds(324, 8, 145, 28);
		}
		return dateChooser;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 51, 640, 330);
		}
		return scrollPane;
	}
}
