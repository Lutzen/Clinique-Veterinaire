package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bo.Personnel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EcranConnexion {

	private JFrame frmConnexion;
	private JTextField txtNom;
	private JTextField txtMotDePasse;
	private JLabel lblNom;
	private JLabel lblMotDePasse;
	private JButton btnValider;

	private PersonnelManager personnelManager = PersonnelManager.getInstance();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranConnexion window = new EcranConnexion();
					window.frmConnexion.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EcranConnexion() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmConnexion = new JFrame();
		frmConnexion.setTitle("Connexion");
		frmConnexion.setBounds(100, 100, 292, 146);
		frmConnexion.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmConnexion.getContentPane().setLayout(null);
		frmConnexion.getContentPane().add(getTxtNom());
		frmConnexion.getContentPane().add(getTxtMotDePasse());
		frmConnexion.getContentPane().add(getLblNom());
		frmConnexion.getContentPane().add(getLblMotDePasse());
		frmConnexion.getContentPane().add(getBtnValider());
		frmConnexion.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}

	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
			txtNom.setBounds(82, 11, 184, 20);
			txtNom.setColumns(10);
		}
		return txtNom;
	}

	private JTextField getTxtMotDePasse() {
		if (txtMotDePasse == null) {
			txtMotDePasse = new JTextField();
			txtMotDePasse.setBounds(82, 42, 184, 20);
			txtMotDePasse.setColumns(10);
		}
		return txtMotDePasse;
	}

	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
			lblNom.setBounds(10, 14, 46, 14);
		}
		return lblNom;
	}

	private JLabel getLblMotDePasse() {
		if (lblMotDePasse == null) {
			lblMotDePasse = new JLabel("Mot de passe");
			lblMotDePasse.setBounds(10, 45, 64, 14);
		}
		return lblMotDePasse;
	}

	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Personnel personnel;

						personnel = personnelManager.getPersonnelByNom(getTxtNom().getText());

						if (getTxtMotDePasse().getText().equals(personnel.getPass())) {
							if (personnel.getRole().equals("adm")) {
								SwingUtilities.invokeLater(new Runnable() {

									@Override
									public void run() {
										EcranGestionPersonnel frame = new EcranGestionPersonnel();
										frame.setVisible(true);
									}
								});
							}
							if (personnel.getRole().equals("sec")) {
								SwingUtilities.invokeLater(new Runnable() {

									@Override
									public void run() {
										EcranRdv frame = new EcranRdv();
										frame.setVisible(true);
									}
								});
							}

							if (personnel.getRole().equals("vet")) {
								SwingUtilities.invokeLater(new Runnable() {

									@Override
									public void run() {
										EcranAgenda frame = new EcranAgenda();
										frame.setVisible(true);
									}
								});
							}
						
						} else
							JOptionPane.showMessageDialog(null, "Mot de passe incorrect");
					} catch (BLLException e1) {
						e1.printStackTrace();
					}

				}
			});
			btnValider.setBounds(177, 73, 89, 23);
		}
		return btnValider;
	}

}
