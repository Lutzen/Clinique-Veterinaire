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

public class EcranInitPass {

	private JFrame frame;
	private JLabel lblNouveauMotDe;
	private JTextField txtNewPass;
	private JButton btnNewButton;
	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	private static Personnel personne;
	private JLabel lblNom;

	/**
	 * Launch the application.
	 */
	public static void main(Personnel personnel) {
		personne = personnel;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranInitPass window = new EcranInitPass();
					window.frame.setVisible(true);
					window.frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EcranInitPass() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 186, 162);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLblNouveauMotDe());
		frame.getContentPane().add(getTxtNewPass());
		frame.getContentPane().add(getBtnValider());
		frame.getContentPane().add(getLblNom());
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
						JOptionPane.showMessageDialog(null, "Mot de passe modifié");
						frame.dispose();
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
