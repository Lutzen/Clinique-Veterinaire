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

public class EcranRdv {

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
	private JFormattedTextField formattedTextField;
	private JLabel lblHeure;
	private JComboBox cBHeures;
	private JLabel lblH;
	private JComboBox cBMinutes;
	private JButton btnValider;
	private JButton btnSupprimer;
	private JTable table;
	private RdvManager rdvManager = RdvManager.getInstance();
	private PersonnelManager personnelManager = PersonnelManager.getInstance();


	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranRdv window = new EcranRdv();
					window.frmPriseDeRendezvous.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EcranRdv() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmPriseDeRendezvous = new JFrame();
		frmPriseDeRendezvous.setTitle("Prise de rendez-vous");
		frmPriseDeRendezvous.setBounds(100, 100, 597, 534);
		frmPriseDeRendezvous.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPriseDeRendezvous.getContentPane().setLayout(null);
		frmPriseDeRendezvous.getContentPane().add(getLblPour());
		frmPriseDeRendezvous.getContentPane().add(getLblClient());
		frmPriseDeRendezvous.getContentPane().add(getCBClients());
		frmPriseDeRendezvous.getContentPane().add(getLblAnimal());
		frmPriseDeRendezvous.getContentPane().add(getCBAnimaux());
		frmPriseDeRendezvous.getContentPane().add(getBtnNewButton());
		frmPriseDeRendezvous.getContentPane().add(getButton());
		frmPriseDeRendezvous.getContentPane().add(getLblPar());
		frmPriseDeRendezvous.getContentPane().add(getLblVtrinaire());
		frmPriseDeRendezvous.getContentPane().add(getCBVetos());
		frmPriseDeRendezvous.getContentPane().add(getLblQuand());
		frmPriseDeRendezvous.getContentPane().add(getLblDate());
		frmPriseDeRendezvous.getContentPane().add(getFormattedTextField());
		frmPriseDeRendezvous.getContentPane().add(getLblHeure());
		frmPriseDeRendezvous.getContentPane().add(getCBHeures());
		frmPriseDeRendezvous.getContentPane().add(getLblH());
		frmPriseDeRendezvous.getContentPane().add(getCBMinutes());
		frmPriseDeRendezvous.getContentPane().add(getBtnValider());
		frmPriseDeRendezvous.getContentPane().add(getBtnSupprimer());
		frmPriseDeRendezvous.getContentPane().add(getTable());
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
				List<Personnel> personnel =personnelManager.getPersonnelByRole("vet");
				cBVetos = new JComboBox(new String[] {});;
				for (int i = 0; i < personnel.size(); i++) {
					cBVetos.addItem(personnel.get(i).getNom());

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

	private JFormattedTextField getFormattedTextField() {
		if (formattedTextField == null) {
			formattedTextField = new JFormattedTextField();
			formattedTextField.setText("  /  /    ");
			formattedTextField.setBounds(428, 51, 92, 20);
		}
		return formattedTextField;
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
				Personnel personnel =personnelManager.getPersonnelByNom((String) cBVetos.getSelectedItem());
				table = new JTable(new TableRdv(rdvManager.getAgenda(personnel.getCodePers())));
				table.setBounds(10, 133, 561, 318);
			} catch (Exception e) {
			}

		}
		return table;

	}

}
