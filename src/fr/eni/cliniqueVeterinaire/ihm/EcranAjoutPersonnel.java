package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JButton;

public class EcranAjoutPersonnel {

	private JFrame frmAjoutEmploy;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblPoste;
	private JLabel lblMotDePasse;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtMdp;
	private JList listRole;
	private JButton btnValider;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranAjoutPersonnel window = new EcranAjoutPersonnel();
					window.frmAjoutEmploy.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EcranAjoutPersonnel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAjoutEmploy = new JFrame();
		frmAjoutEmploy.setTitle("Ajout d'un employ\u00E9");
		frmAjoutEmploy.setBounds(100, 100, 261, 195);
		frmAjoutEmploy.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAjoutEmploy.getContentPane().setLayout(null);
		frmAjoutEmploy.getContentPane().add(getLblNom());
		frmAjoutEmploy.getContentPane().add(getLblPrenom());
		frmAjoutEmploy.getContentPane().add(getLblPoste());
		frmAjoutEmploy.getContentPane().add(getLblMotDePasse());
		frmAjoutEmploy.getContentPane().add(getTxtNom());
		frmAjoutEmploy.getContentPane().add(getTxtPrenom());
		frmAjoutEmploy.getContentPane().add(getTxtMdp());
		frmAjoutEmploy.getContentPane().add(getListRole());
		frmAjoutEmploy.getContentPane().add(getBtnValider());
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
	private JList getListRole() {
		if (listRole == null) {
			listRole = new JList();
			listRole.setBounds(97, 60, 120, 15);
		}
		return listRole;
	}
	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.setBounds(128, 122, 89, 23);
		}
		return btnValider;
	}
}
