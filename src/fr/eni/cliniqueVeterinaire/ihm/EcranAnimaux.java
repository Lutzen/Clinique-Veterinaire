package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import fr.eni.cliniqueVeterinaire.bll.AnimalManager;
import fr.eni.cliniqueVeterinaire.bll.BLLException;

import javax.swing.JComboBox;

public class EcranAnimaux extends JFrame{

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
	private AnimalManager animalManager = AnimalManager.getInstance();

	
	public EcranAnimaux() {
		setTitle("Animaux");
		setBounds(100, 100, 496, 271);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnAnnuler());
		getContentPane().add(getBtnValider());
		getContentPane().add(getLblClient());
		getContentPane().add(getTxtClient());
		getContentPane().add(getLblCode());
		getContentPane().add(getTxtCode());
		getContentPane().add(getTxtNom());
		getContentPane().add(getTxtCouleur());
		getContentPane().add(getLblNom());
		getContentPane().add(getLblCouleur());
		getContentPane().add(getCBSexe());
		getContentPane().add(getLblEspce());
		getContentPane().add(getCBEspece());
		getContentPane().add(getLblRace());
		getContentPane().add(getCBRace());
		getContentPane().add(getTxtTatouage());
		getContentPane().add(getLblTatouage());
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
