package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.sun.java.swing.plaf.windows.resources.windows;

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bo.Personnel;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EcranInitPass extends JFrame{

	private JLabel lblNouveauMotDe;
	private JTextField txtNewPass;
	private JButton btnNewButton;
	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	private static Personnel personne;
	private JLabel lblNom;
	


	public EcranInitPass(Personnel personnel) {
		personne = personnel;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 186, 162);
		getContentPane().setLayout(null);
		getContentPane().add(getLblNouveauMotDe());
		getContentPane().add(getTxtNewPass());
		getContentPane().add(getBtnValider());
		getContentPane().add(getLblNom());
		
	}

	private JLabel getLblNouveauMotDe() {
		if (lblNouveauMotDe == null) {
			lblNouveauMotDe = new JLabel("Nouveau mot de passe pour");
			lblNouveauMotDe.setBounds(10, 11, 144, 14);
		}
		return lblNouveauMotDe;
	}
	private JTextField getTxtNewPass() {
		if (txtNewPass == null) {
			txtNewPass = new JTextField();
			txtNewPass.setBounds(10, 58, 144, 20);
			txtNewPass.setColumns(10);
		}
		return txtNewPass;
	}
	private JButton getBtnValider() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Valider");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						personnelManager.updatePersonnel(personne, getTxtNewPass().getText());
						JOptionPane.showMessageDialog(null, "Mot de passe modifi�");
						dispose();
					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnNewButton.setBounds(71, 89, 89, 23);
			
		}
		return btnNewButton;
	}
	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel(personne.getNom());
			lblNom.setBounds(10, 33, 144, 14);
		}
		return lblNom;
	}
}
