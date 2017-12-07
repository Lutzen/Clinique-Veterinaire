package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bo.Personnel;

import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class EcranAjoutPersonnel extends JFrame {

	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblPoste;
	private JLabel lblMotDePasse;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtMdp;
	private JButton btnValider;
	private JComboBox comboBox;
	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	
	private EcranGestionPersonnel ecranGestion;

	/**
	 * Create the application.
	 */
	public EcranAjoutPersonnel(EcranGestionPersonnel ecranGestion) {
		setTitle("Ajout d'un employ�");
		setBounds(100, 100, 261, 195);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNom());
		getContentPane().add(getLblPrenom());
		getContentPane().add(getLblPoste());
		getContentPane().add(getLblMotDePasse());
		getContentPane().add(getTxtNom());
		getContentPane().add(getTxtPrenom());
		getContentPane().add(getTxtMdp());
		getContentPane().add(getBtnValider());
		getContentPane().add(getComboBox());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		this.ecranGestion = ecranGestion;
	}

	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
			lblNom.setBounds(10, 11, 77, 14);
		}
		return lblNom;
	}
	private JLabel getLblPrenom() {
		if (lblPrenom == null) {
			lblPrenom = new JLabel("Prenom");
			lblPrenom.setBounds(10, 36, 77, 14);
		}
		return lblPrenom;
	}
	private JLabel getLblPoste() {
		if (lblPoste == null) {
			lblPoste = new JLabel("Poste");
			lblPoste.setBounds(10, 61, 77, 14);
		}
		return lblPoste;
	}
	private JLabel getLblMotDePasse() {
		if (lblMotDePasse == null) {
			lblMotDePasse = new JLabel("Mot de passe");
			lblMotDePasse.setBounds(10, 86, 77, 14);
		}
		return lblMotDePasse;
	}
	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
			txtNom.setBounds(97, 8, 120, 20);
			txtNom.setColumns(10);
		}
		return txtNom;
	}
	private JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField();
			txtPrenom.setBounds(97, 33, 120, 20);
			txtPrenom.setColumns(10);
		}
		return txtPrenom;
	}
	private JTextField getTxtMdp() {
		if (txtMdp == null) {
			txtMdp = new JTextField();
			txtMdp.setBounds(97, 83, 120, 20);
			txtMdp.setColumns(10);
		}
		return txtMdp;
	}
	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
					Personnel personnel = new Personnel();
					personnel.setNom(txtNom.getText()+ " " + txtPrenom.getText());
					personnel.setPass(txtMdp.getText());
					switch ((String)comboBox.getSelectedItem()) {
					case "V�t�rinaire":
						personnel.setRole("vet");
						break;
					case "Secr�taire":
						personnel.setRole("sec");
						break;
					case "Assistant":
						personnel.setRole("ast");
						break;
					case "Stagiaire":
						personnel.setRole("sta");
						break;
					default:
						break;
					}
					personnelManager.addPersonnel(personnel);
					JOptionPane.showMessageDialog(null, "Personnel ajout� avec succ�s");

					ecranGestion.mettreAJour();
					
					dispose();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnValider.setBounds(128, 122, 89, 23);
		}
		return btnValider;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox(new String[] { "V�t�rinaire", "Secr�taire", "Assistant", "Stagiaire"});
			comboBox.setBounds(97, 58, 120, 20);
		}
		return comboBox;
	}
	
}
