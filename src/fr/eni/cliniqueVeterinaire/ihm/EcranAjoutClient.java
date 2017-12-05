package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class EcranAjoutClient {

	private JFrame frmAjoutDunClient;
	private JButton btnAnnuler;
	private JButton btnValider;
	private JTextField txtCode;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtAdresse;
	private JTextField txtAdresse2;
	private JTextField txtCodePostal;
	private JTextField txtVille;
	private JLabel lblCode;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JLabel lblAdresse;
	private JLabel lblCodePostal;
	private JLabel lblVille;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranAjoutClient window = new EcranAjoutClient();
					window.frmAjoutDunClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EcranAjoutClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAjoutDunClient = new JFrame();
		frmAjoutDunClient.setTitle("Ajout d'un client");
		frmAjoutDunClient.setBounds(100, 100, 346, 305);
		frmAjoutDunClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAjoutDunClient.getContentPane().setLayout(null);
		frmAjoutDunClient.getContentPane().add(getBtnAnnuler());
		frmAjoutDunClient.getContentPane().add(getBtnValider());
		frmAjoutDunClient.getContentPane().add(getTxtCode());
		frmAjoutDunClient.getContentPane().add(getTxtNom());
		frmAjoutDunClient.getContentPane().add(getTxtPrenom());
		frmAjoutDunClient.getContentPane().add(getTxtAdresse());
		frmAjoutDunClient.getContentPane().add(getTxtAdresse2());
		frmAjoutDunClient.getContentPane().add(getTxtCodePostal());
		frmAjoutDunClient.getContentPane().add(getTxtVille());
		frmAjoutDunClient.getContentPane().add(getLblCode());
		frmAjoutDunClient.getContentPane().add(getLblNom());
		frmAjoutDunClient.getContentPane().add(getLblPrnom());
		frmAjoutDunClient.getContentPane().add(getLblAdresse());
		frmAjoutDunClient.getContentPane().add(getLblCodePostal());
		frmAjoutDunClient.getContentPane().add(getLblVille());
	}

	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.setBounds(231, 11, 89, 23);
		}
		return btnAnnuler;
	}
	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnValider_actionPerformed(e);
				}
			});
			btnValider.setBounds(136, 11, 89, 23);
		}
		return btnValider;
	}
	protected static void do_btnValider_actionPerformed(ActionEvent e) {
	}
	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setBounds(85, 45, 235, 20);
			txtCode.setColumns(10);
		}
		return txtCode;
	}
	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
			txtNom.setColumns(10);
			txtNom.setBounds(85, 76, 235, 20);
		}
		return txtNom;
	}
	private JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField();
			txtPrenom.setColumns(10);
			txtPrenom.setBounds(85, 107, 235, 20);
		}
		return txtPrenom;
	}
	private JTextField getTxtAdresse() {
		if (txtAdresse == null) {
			txtAdresse = new JTextField();
			txtAdresse.setColumns(10);
			txtAdresse.setBounds(85, 138, 235, 20);
		}
		return txtAdresse;
	}
	private JTextField getTxtAdresse2() {
		if (txtAdresse2 == null) {
			txtAdresse2 = new JTextField();
			txtAdresse2.setColumns(10);
			txtAdresse2.setBounds(85, 169, 235, 20);
		}
		return txtAdresse2;
	}
	private JTextField getTxtCodePostal() {
		if (txtCodePostal == null) {
			txtCodePostal = new JTextField();
			txtCodePostal.setColumns(10);
			txtCodePostal.setBounds(85, 200, 235, 20);
		}
		return txtCodePostal;
	}
	private JTextField getTxtVille() {
		if (txtVille == null) {
			txtVille = new JTextField();
			txtVille.setColumns(10);
			txtVille.setBounds(85, 231, 235, 20);
		}
		return txtVille;
	}
	private JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("Code");
			lblCode.setBounds(10, 48, 46, 14);
		}
		return lblCode;
	}
	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
			lblNom.setBounds(10, 79, 46, 14);
		}
		return lblNom;
	}
	private JLabel getLblPrnom() {
		if (lblPrnom == null) {
			lblPrnom = new JLabel("Pr\u00E9nom");
			lblPrnom.setBounds(10, 110, 46, 14);
		}
		return lblPrnom;
	}
	private JLabel getLblAdresse() {
		if (lblAdresse == null) {
			lblAdresse = new JLabel("Adresse");
			lblAdresse.setBounds(10, 141, 46, 14);
		}
		return lblAdresse;
	}
	private JLabel getLblCodePostal() {
		if (lblCodePostal == null) {
			lblCodePostal = new JLabel("Code postal");
			lblCodePostal.setBounds(10, 203, 65, 14);
		}
		return lblCodePostal;
	}
	private JLabel getLblVille() {
		if (lblVille == null) {
			lblVille = new JLabel("Ville");
			lblVille.setBounds(10, 234, 46, 14);
		}
		return lblVille;
	}
}
