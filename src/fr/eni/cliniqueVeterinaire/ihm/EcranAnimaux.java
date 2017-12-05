package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;

public class EcranAnimaux {

	private JFrame frmAnimaux;
	private JButton btnAnnuler;
	private JButton btnValider;
	private JLabel lblClient;
	private JTextField txtClient;
	private JLabel lblCode;
	private JTextField txtCode;
	private JTextField txtNom;
	private JTextField txtCouleur;
	private JLabel lblNom;
	private JLabel lblCouleur;
	private JComboBox cBSexe;
	private JLabel lblEspce;
	private JComboBox cBEspece;
	private JLabel lblRace;
	private JComboBox cBRace;
	private JTextField txtTatouage;
	private JLabel lblTatouage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranAnimaux window = new EcranAnimaux();
					window.frmAnimaux.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EcranAnimaux() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAnimaux = new JFrame();
		frmAnimaux.setTitle("Animaux");
		frmAnimaux.setBounds(100, 100, 496, 271);
		frmAnimaux.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAnimaux.getContentPane().setLayout(null);
		frmAnimaux.getContentPane().add(getBtnAnnuler());
		frmAnimaux.getContentPane().add(getBtnValider());
		frmAnimaux.getContentPane().add(getLblClient());
		frmAnimaux.getContentPane().add(getTxtClient());
		frmAnimaux.getContentPane().add(getLblCode());
		frmAnimaux.getContentPane().add(getTxtCode());
		frmAnimaux.getContentPane().add(getTxtNom());
		frmAnimaux.getContentPane().add(getTxtCouleur());
		frmAnimaux.getContentPane().add(getLblNom());
		frmAnimaux.getContentPane().add(getLblCouleur());
		frmAnimaux.getContentPane().add(getCBSexe());
		frmAnimaux.getContentPane().add(getLblEspce());
		frmAnimaux.getContentPane().add(getCBEspece());
		frmAnimaux.getContentPane().add(getLblRace());
		frmAnimaux.getContentPane().add(getCBRace());
		frmAnimaux.getContentPane().add(getTxtTatouage());
		frmAnimaux.getContentPane().add(getLblTatouage());
	}

	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.setBounds(379, 11, 89, 23);
		}
		return btnAnnuler;
	}
	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.setBounds(264, 11, 89, 23);
		}
		return btnValider;
	}
	private JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Client :");
			lblClient.setBounds(10, 15, 46, 14);
		}
		return lblClient;
	}
	private JTextField getTxtClient() {
		if (txtClient == null) {
			txtClient = new JTextField();
			txtClient.setBounds(10, 30, 244, 20);
			txtClient.setColumns(10);
		}
		return txtClient;
	}
	private JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("Code ");
			lblCode.setBounds(10, 75, 46, 14);
		}
		return lblCode;
	}
	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setBounds(87, 72, 167, 20);
			txtCode.setColumns(10);
		}
		return txtCode;
	}
	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
			txtNom.setBounds(87, 103, 167, 20);
			txtNom.setColumns(10);
		}
		return txtNom;
	}
	private JTextField getTxtCouleur() {
		if (txtCouleur == null) {
			txtCouleur = new JTextField();
			txtCouleur.setBounds(87, 134, 167, 20);
			txtCouleur.setColumns(10);
		}
		return txtCouleur;
	}
	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
			lblNom.setBounds(10, 106, 46, 14);
		}
		return lblNom;
	}
	private JLabel getLblCouleur() {
		if (lblCouleur == null) {
			lblCouleur = new JLabel("Couleur");
			lblCouleur.setBounds(10, 137, 46, 14);
		}
		return lblCouleur;
	}
	private JComboBox getCBSexe() {
		if (cBSexe == null) {
			cBSexe = new JComboBox();
			cBSexe.setBounds(264, 103, 71, 20);
		}
		return cBSexe;
	}
	private JLabel getLblEspce() {
		if (lblEspce == null) {
			lblEspce = new JLabel("Esp\u00E8ce");
			lblEspce.setBounds(10, 168, 46, 14);
		}
		return lblEspce;
	}
	private JComboBox getCBEspece() {
		if (cBEspece == null) {
			cBEspece = new JComboBox();
			cBEspece.setBounds(87, 165, 167, 20);
		}
		return cBEspece;
	}
	private JLabel getLblRace() {
		if (lblRace == null) {
			lblRace = new JLabel("Race");
			lblRace.setBounds(264, 168, 46, 14);
		}
		return lblRace;
	}
	private JComboBox getCBRace() {
		if (cBRace == null) {
			cBRace = new JComboBox();
			cBRace.setBounds(301, 165, 167, 20);
		}
		return cBRace;
	}
	private JTextField getTxtTatouage() {
		if (txtTatouage == null) {
			txtTatouage = new JTextField();
			txtTatouage.setColumns(10);
			txtTatouage.setBounds(87, 196, 167, 20);
		}
		return txtTatouage;
	}
	private JLabel getLblTatouage() {
		if (lblTatouage == null) {
			lblTatouage = new JLabel("Tatouage");
			lblTatouage.setBounds(10, 199, 46, 14);
		}
		return lblTatouage;
	}
}
