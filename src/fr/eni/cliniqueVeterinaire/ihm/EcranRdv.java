package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bll.RdvManager;
import fr.eni.cliniqueVeterinaire.bo.Personnel;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;

public class EcranRdv extends JFrame {

	private JFrame frmPriseDeRendezvous;
	private JLabel lblPour;
	private JLabel lblClient;
	private JComboBox cBClients;
	private JLabel lblAnimal;
	private JComboBox cBAnimaux;
	private JButton btnNewButton;
	private JButton button;
	private JLabel lblPar;
	private JLabel lblVtrinaire;
	private JComboBox cBVetos;
	private JLabel lblQuand;
	private JLabel lblDate;
	private JFormattedTextField txtDate;
	private JLabel lblHeure;
	private JComboBox cBHeures;
	private JLabel lblH;
	private JComboBox cBMinutes;
	private JButton btnValider;
	private JButton btnSupprimer;
	private JTable table;
	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	private ModeleRdv modeleRdv;
	private Personnel personnel;

	/**
	 * Create the application.
	 */
	public EcranRdv() {

		setTitle("Prise de rendez-vous");
		setBounds(100, 100, 597, 534);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblPour());
		getContentPane().add(getLblClient());
		getContentPane().add(getCBClients());
		getContentPane().add(getLblAnimal());
		getContentPane().add(getCBAnimaux());
		getContentPane().add(getBtnNewButton());
		getContentPane().add(getButton());
		getContentPane().add(getLblPar());
		getContentPane().add(getLblVtrinaire());
		getContentPane().add(getCBVetos());
		getContentPane().add(getLblQuand());
		getContentPane().add(getLblDate());
		getContentPane().add(getTxtDate());
		getContentPane().add(getLblHeure());
		getContentPane().add(getCBHeures());
		getContentPane().add(getLblH());
		getContentPane().add(getCBMinutes());
		getContentPane().add(getBtnValider());
		getContentPane().add(getBtnSupprimer());
		getContentPane().add(getTable());
	}

	private JLabel getLblPour() {
		if (lblPour == null) {
			lblPour = new JLabel("Pour");
			lblPour.setBounds(10, 11, 46, 14);
		}
		return lblPour;
	}

	private JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Client");
			lblClient.setBounds(30, 36, 46, 14);
		}
		return lblClient;
	}

	private JComboBox getCBClients() {
		if (cBClients == null) {
			cBClients = new JComboBox();
			cBClients.setBounds(30, 51, 131, 20);
		}
		return cBClients;
	}

	private JLabel getLblAnimal() {
		if (lblAnimal == null) {
			lblAnimal = new JLabel("Animal");
			lblAnimal.setBounds(30, 82, 46, 14);
		}
		return lblAnimal;
	}

	private JComboBox getCBAnimaux() {
		if (cBAnimaux == null) {
			cBAnimaux = new JComboBox();
			cBAnimaux.setBounds(30, 100, 131, 20);
		}
		return cBAnimaux;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("");
			btnNewButton.setIcon(new ImageIcon(EcranRdv.class
					.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Indent-Black-rtl.png")));
			btnNewButton.setBounds(171, 50, 36, 23);
		}
		return btnNewButton;
	}

	private JButton getButton() {
		if (button == null) {
			button = new JButton("");
			button.setIcon(new ImageIcon(EcranRdv.class
					.getResource("/com/sun/javafx/scene/control/skin/modena/HTMLEditor-Indent-Black-rtl.png")));
			button.setBounds(171, 99, 36, 23);
		}
		return button;
	}

	private JLabel getLblPar() {
		if (lblPar == null) {
			lblPar = new JLabel("Par");
			lblPar.setBounds(236, 11, 46, 14);
		}
		return lblPar;
	}

	private JLabel getLblVtrinaire() {
		if (lblVtrinaire == null) {
			lblVtrinaire = new JLabel("V\u00E9t\u00E9rinaire");
			lblVtrinaire.setBounds(256, 36, 82, 14);
		}
		return lblVtrinaire;
	}

	private JComboBox getCBVetos() {
		if (cBVetos == null) {
			try {
				List<Personnel> personnel = personnelManager.getPersonnelByRole("vet");
				cBVetos = new JComboBox(new String[] {});
				for (int i = 0; i < personnel.size(); i++) {
					cBVetos.addItem(personnel.get(i).getNom());
					cBVetos.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							System.out.println("iciiii");
							mettreAJour();

						}
					});

				}
				cBVetos.setBounds(256, 51, 131, 20);

			} catch (BLLException e) {

				e.printStackTrace();
			}

		}
		return cBVetos;
	}

	private JLabel getLblQuand() {
		if (lblQuand == null) {
			lblQuand = new JLabel("Quand");
			lblQuand.setBounds(408, 11, 46, 14);
		}
		return lblQuand;
	}

	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("Date");
			lblDate.setBounds(428, 36, 46, 14);
		}
		return lblDate;
	}

	private JFormattedTextField getTxtDate() {
		if (txtDate == null) {
			txtDate = new JFormattedTextField();
			txtDate.setText("  /  /    ");
			txtDate.setBounds(428, 51, 92, 20);
		}
		return txtDate;
	}

	private JLabel getLblHeure() {
		if (lblHeure == null) {
			lblHeure = new JLabel("Heure");
			lblHeure.setBounds(428, 82, 46, 14);
		}
		return lblHeure;
	}

	private JComboBox getCBHeures() {
		if (cBHeures == null) {
			cBHeures = new JComboBox();
			cBHeures.setBounds(428, 100, 46, 20);
		}
		return cBHeures;
	}

	private JLabel getLblH() {
		if (lblH == null) {
			lblH = new JLabel("h");
			lblH.setBounds(484, 103, 6, 14);
		}
		return lblH;
	}

	private JComboBox getCBMinutes() {
		if (cBMinutes == null) {
			cBMinutes = new JComboBox();
			cBMinutes.setBounds(500, 100, 46, 20);
		}
		return cBMinutes;
	}

	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.setBounds(385, 462, 89, 23);
		}
		return btnValider;
	}

	private JButton getBtnSupprimer() {
		if (btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer");
			btnSupprimer.setBounds(482, 462, 89, 23);
		}
		return btnSupprimer;
	}

	private JTable getTable() {
		if (table == null) {

			try {
				table = new JTable(getModeleRdv());
				cBVetos.setSelectedIndex(0);
				table.setBounds(10, 133, 561, 318);
			} catch (Exception e) {
			}

		}
		return table;

	}

	public void mettreAJour() {

		try {
			personnel = personnelManager.getPersonnelByNom((String) cBVetos.getSelectedItem());
			modeleRdv.setData(personnel.getCodePers());

		} catch (BLLException e) {

			e.printStackTrace();
		}
	}

	private ModeleRdv getModeleRdv() {
		if (modeleRdv == null) {
			try {
				personnel = personnelManager.getPersonnelByNom((String) cBVetos.getSelectedItem());
				
				System.out.println(personnel);
				modeleRdv = new ModeleRdv(personnel.getCodePers());
			} catch (BLLException e) {
				e.printStackTrace();
			}

		}
		return modeleRdv;
	}

}
