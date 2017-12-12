package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bo.Personnel;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;
import java.awt.Font;

public class EcranConnexion {

	private JFrame frmConnexion;
	private JTextField txtNom;
	private JPasswordField txtMotDePasse;
	private JLabel lblNom;
	private JLabel lblMotDePasse;
	private JButton btnValider;

	private PersonnelManager personnelManager = PersonnelManager.getInstance();


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

	
	public EcranConnexion() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frmConnexion = new JFrame();
		frmConnexion.setResizable(false);
		frmConnexion.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		frmConnexion.setTitle("Connexion");
		frmConnexion.setBounds(100, 100, 292, 153);
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
			txtNom.setBounds(92, 11, 174, 28);
			txtNom.setColumns(10);
		}
		return txtNom;
	}

	private JPasswordField getTxtMotDePasse() {
		if (txtMotDePasse == null) {
			txtMotDePasse = new JPasswordField();
			txtMotDePasse.setBounds(92, 42, 174, 28);
			txtMotDePasse.setColumns(10);
		}
		return txtMotDePasse;
	}

	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
			lblNom.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblNom.setBounds(10, 18, 46, 14);
		}
		return lblNom;
	}

	private JLabel getLblMotDePasse() {
		if (lblMotDePasse == null) {
			lblMotDePasse = new JLabel("Mot de passe");
			lblMotDePasse.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblMotDePasse.setBounds(10, 49, 79, 14);
		}
		return lblMotDePasse;
	}

	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode() == KeyEvent.VK_ENTER) {
						btnValider.doClick();
					}
				}
			});
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					try {
						Personnel personnel;

						personnel = personnelManager.getPersonnelByNom(getTxtNom().getText());
						String pass = String.valueOf(getTxtMotDePasse().getPassword());

						if (personnel == null)
							JOptionPane.showMessageDialog(null, "Utilisateur inconnu");
						else {
							if (pass.equals(personnel.getPass())) {
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
						}
					} catch (BLLException e1) {
						e1.printStackTrace();
					}

				}
			});
			btnValider.setBounds(175, 73, 89, 28);
		}
		return btnValider;
	}

}
