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
import javax.swing.JDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class EcranConnexion extends JFrame {

	private JTextField txtNom;
	private JPasswordField txtMotDePasse;
	private JLabel lblNom;
	private JLabel lblMotDePasse;
	private JButton btnValider;
	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	private EcranPrincipal ecranPrincipal;

	public EcranConnexion(EcranPrincipal ecran) {
		setBounds(100, 100, 292, 153);
		setType(Type.UTILITY);
		setResizable(false);
		setAlwaysOnTop(true);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		ecranPrincipal = ecran;
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		setTitle("Connexion");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getTxtNom());
		getContentPane().add(getTxtMotDePasse());
		getContentPane().add(getLblNom());
		getContentPane().add(getLblMotDePasse());
		getContentPane().add(getBtnValider());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

						if (personnel == null || getTxtNom().getText().isEmpty()) {
							JOptionPane pane = new JOptionPane("Utilisateur inconnu");
							JDialog dialog = pane.createDialog("Erreur");
							dialog.setAlwaysOnTop(true);
							dialog.setVisible(true);
						} else {
							if (pass.equals(personnel.getPass())) {
								if (personnel.getRole().equals("adm")) {
									ecranPrincipal.RetourConnexion(0);
								}
								if (personnel.getRole().equals("sec")) {
									ecranPrincipal.RetourConnexion(1);
								}

								if (personnel.getRole().equals("vet")) {
									ecranPrincipal.RetourConnexion(2);
								}
								dispose();
							} else {
								JOptionPane pane = new JOptionPane("Mot de passe incorrect");
								JDialog dialog = pane.createDialog("Erreur");
								dialog.setAlwaysOnTop(true);
								dialog.setVisible(true);
							}

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
