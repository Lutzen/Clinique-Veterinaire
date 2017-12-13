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
import java.awt.Font;
import java.awt.Rectangle;



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
		
		setResizable(false);
		setTitle("Ajout d'un employé");
		setBounds(600, 300, 263, 231);
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
			lblNom.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblNom.setBounds(10, 11, 77, 14);
		}
		return lblNom;
	}
	private JLabel getLblPrenom() {
		if (lblPrenom == null) {
			lblPrenom = new JLabel("Prenom");
			lblPrenom.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblPrenom.setBounds(10, 49, 77, 14);
		}
		return lblPrenom;
	}
	private JLabel getLblPoste() {
		if (lblPoste == null) {
			lblPoste = new JLabel("Poste");
			lblPoste.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblPoste.setBounds(10, 87, 77, 14);
		}
		return lblPoste;
	}
	private JLabel getLblMotDePasse() {
		if (lblMotDePasse == null) {
			lblMotDePasse = new JLabel("Mot de passe");
			lblMotDePasse.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblMotDePasse.setBounds(10, 126, 77, 14);
		}
		return lblMotDePasse;
	}
	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
			txtNom.setBounds(99, 5, 136, 26);
			txtNom.setColumns(10);
		}
		return txtNom;
	}
	private JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField();
			txtPrenom.setBounds(99, 43, 136, 26);
			txtPrenom.setColumns(10);
		}
		return txtPrenom;
	}
	private JTextField getTxtMdp() {
		if (txtMdp == null) {
			txtMdp = new JTextField();
			txtMdp.setBounds(99, 120, 136, 26);
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
						if(txtNom.getText().isEmpty() || txtMdp.getText().isEmpty())
							JOptionPane.showMessageDialog(null, "Remplissez tout les champs");
						else {
					Personnel personnel = new Personnel();
					personnel.setNom(txtNom.getText()+ " " + txtPrenom.getText());
					personnel.setPass(txtMdp.getText());
					switch ((String)comboBox.getSelectedItem()) {
					case "Vétérinaire":
						personnel.setRole("vet");
						break;
					case "Secrétaire":
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
					JOptionPane.showMessageDialog(null, "Personnel ajouté avec succès");

					ecranGestion.mettreAJour();
					
					dispose();}
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnValider.setBounds(146, 158, 89, 28);
		}
		return btnValider;
	}
	private JComboBox getComboBox() {
		if (comboBox == null) {
			comboBox = new JComboBox(new String[] { "Vétérinaire", "Secrétaire", "Assistant", "Stagiaire"});
			comboBox.setBounds(99, 81, 136, 26);
		}
		return comboBox;
	}
	
}
