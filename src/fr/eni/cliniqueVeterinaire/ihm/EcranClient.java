package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class EcranClient {

	private JFrame frmClients;
	private JButton btnRechercher;
	private JButton btnAjouter;
	private JButton btnSupprimer;
	private JButton btnValider;
	private JButton btnAnnuler;
	private JTextField txtCode;
	private JTextField txtNom;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblCode;
	private JLabel lblNom;
	private JLabel txtPrenom;
	private JLabel txtAdresse;
	private JLabel txtCodePostal;
	private JLabel txtVille;
	private JTextArea textArea;
	private JButton btnEditer;
	private JButton btnSupprimer_1;
	private JButton btnAjouter_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranClient window = new EcranClient();
					window.frmClients.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EcranClient() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmClients = new JFrame();
		frmClients.setTitle("Clients");
		frmClients.setBounds(100, 100, 622, 377);
		frmClients.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmClients.getContentPane().setLayout(null);
		frmClients.getContentPane().add(getBtnRechercher());
		frmClients.getContentPane().add(getBtnAjouter());
		frmClients.getContentPane().add(getBtnSupprimer());
		frmClients.getContentPane().add(getBtnValider());
		frmClients.getContentPane().add(getBtnAnnuler());
		frmClients.getContentPane().add(getTxtCode());
		frmClients.getContentPane().add(getTxtNom());
		frmClients.getContentPane().add(getTextField_2());
		frmClients.getContentPane().add(getTextField_3());
		frmClients.getContentPane().add(getTextField_4());
		frmClients.getContentPane().add(getTextField_5());
		frmClients.getContentPane().add(getTextField_6());
		frmClients.getContentPane().add(getLblCode());
		frmClients.getContentPane().add(getLblNom());
		frmClients.getContentPane().add(getTxtPrenom());
		frmClients.getContentPane().add(getTxtAdresse());
		frmClients.getContentPane().add(getTxtCodePostal());
		frmClients.getContentPane().add(getTxtVille());
		frmClients.getContentPane().add(getTextArea());
		frmClients.getContentPane().add(getBtnEditer());
		frmClients.getContentPane().add(getBtnSupprimer_1());
		frmClients.getContentPane().add(getBtnAjouter_1());
	}

	private JButton getBtnRechercher() {
		if (btnRechercher == null) {
			btnRechercher = new JButton("Rechercher");
			btnRechercher.setBounds(10, 11, 89, 23);
		}
		return btnRechercher;
	}
	private JButton getBtnAjouter() {
		if (btnAjouter == null) {
			btnAjouter = new JButton("Ajouter");
			btnAjouter.setBounds(184, 11, 89, 23);
		}
		return btnAjouter;
	}
	private JButton getBtnSupprimer() {
		if (btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer");
			btnSupprimer.setBounds(283, 11, 89, 23);
		}
		return btnSupprimer;
	}
	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					do_btnValider_actionPerformed(e);
				}
			});
			btnValider.setBounds(408, 11, 89, 23);
		}
		return btnValider;
	}
	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.setBounds(507, 11, 89, 23);
		}
		return btnAnnuler;
	}
	protected static void do_btnValider_actionPerformed(ActionEvent e) {
	}
	private JTextField getTxtCode() {
		if (txtCode == null) {
			txtCode = new JTextField();
			txtCode.setBounds(86, 66, 170, 20);
			txtCode.setColumns(10);
		}
		return txtCode;
	}
	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
			txtNom.setColumns(10);
			txtNom.setBounds(86, 97, 170, 20);
		}
		return txtNom;
	}
	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setColumns(10);
			textField_2.setBounds(86, 128, 170, 20);
		}
		return textField_2;
	}
	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(86, 159, 170, 20);
		}
		return textField_3;
	}
	private JTextField getTextField_4() {
		if (textField_4 == null) {
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(86, 190, 170, 20);
		}
		return textField_4;
	}
	private JTextField getTextField_5() {
		if (textField_5 == null) {
			textField_5 = new JTextField();
			textField_5.setColumns(10);
			textField_5.setBounds(86, 221, 170, 20);
		}
		return textField_5;
	}
	private JTextField getTextField_6() {
		if (textField_6 == null) {
			textField_6 = new JTextField();
			textField_6.setColumns(10);
			textField_6.setBounds(86, 252, 170, 20);
		}
		return textField_6;
	}
	private JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("Code");
			lblCode.setBounds(10, 69, 46, 14);
		}
		return lblCode;
	}
	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
			lblNom.setBounds(10, 100, 46, 14);
		}
		return lblNom;
	}
	private JLabel getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JLabel("Pr\u00E9nom");
			txtPrenom.setBounds(10, 131, 46, 14);
		}
		return txtPrenom;
	}
	private JLabel getTxtAdresse() {
		if (txtAdresse == null) {
			txtAdresse = new JLabel("Adresse");
			txtAdresse.setBounds(10, 162, 46, 14);
		}
		return txtAdresse;
	}
	private JLabel getTxtCodePostal() {
		if (txtCodePostal == null) {
			txtCodePostal = new JLabel("Code postal");
			txtCodePostal.setBounds(10, 224, 66, 14);
		}
		return txtCodePostal;
	}
	private JLabel getTxtVille() {
		if (txtVille == null) {
			txtVille = new JLabel("Ville");
			txtVille.setBounds(10, 255, 46, 14);
		}
		return txtVille;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setBounds(283, 64, 313, 229);
		}
		return textArea;
	}
	private JButton getBtnEditer() {
		if (btnEditer == null) {
			btnEditer = new JButton("Editer");
			btnEditer.setBounds(507, 304, 89, 23);
		}
		return btnEditer;
	}
	private JButton getBtnSupprimer_1() {
		if (btnSupprimer_1 == null) {
			btnSupprimer_1 = new JButton("Supprimer");
			btnSupprimer_1.setBounds(408, 304, 89, 23);
		}
		return btnSupprimer_1;
	}
	private JButton getBtnAjouter_1() {
		if (btnAjouter_1 == null) {
			btnAjouter_1 = new JButton("Ajouter");
			btnAjouter_1.setBounds(309, 304, 89, 23);
		}
		return btnAjouter_1;
	}
}
