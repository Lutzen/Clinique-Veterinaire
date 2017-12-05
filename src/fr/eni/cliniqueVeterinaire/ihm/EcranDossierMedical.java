package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class EcranDossierMedical {

	private JFrame frmDossierMedical;
	private JButton btnValider;
	private JButton btnAnnuler;
	private JLabel lblClient;
	private JTextField textField;
	private JLabel lblAnimal;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JLabel lblAntcdentsconsultations;
	private JTextPane textPane;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranDossierMedical window = new EcranDossierMedical();
					window.frmDossierMedical.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EcranDossierMedical() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmDossierMedical = new JFrame();
		frmDossierMedical.setTitle("Dossier Medical");
		frmDossierMedical.setBounds(100, 100, 577, 360);
		frmDossierMedical.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmDossierMedical.getContentPane().setLayout(null);
		frmDossierMedical.getContentPane().add(getBtnValider());
		frmDossierMedical.getContentPane().add(getBtnAnnuler());
		frmDossierMedical.getContentPane().add(getLblClient());
		frmDossierMedical.getContentPane().add(getTextField());
		frmDossierMedical.getContentPane().add(getLblAnimal());
		frmDossierMedical.getContentPane().add(getTextField_1());
		frmDossierMedical.getContentPane().add(getTextField_2());
		frmDossierMedical.getContentPane().add(getTextField_3());
		frmDossierMedical.getContentPane().add(getTextField_4());
		frmDossierMedical.getContentPane().add(getTextField_5());
		frmDossierMedical.getContentPane().add(getTextField_6());
		frmDossierMedical.getContentPane().add(getLblAntcdentsconsultations());
		frmDossierMedical.getContentPane().add(getTextPane());
	}

	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.setBounds(363, 11, 89, 23);
		}
		return btnValider;
	}
	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					do_btnAnnuler_actionPerformed(arg0);
				}
			});
			btnAnnuler.setBounds(462, 11, 89, 23);
		}
		return btnAnnuler;
	}
	private JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Client :");
			lblClient.setBounds(10, 54, 34, 14);
		}
		return lblClient;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(54, 51, 169, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	protected static void do_btnAnnuler_actionPerformed(ActionEvent arg0) {
	}
	private JLabel getLblAnimal() {
		if (lblAnimal == null) {
			lblAnimal = new JLabel("Animal :");
			lblAnimal.setBounds(10, 93, 46, 14);
		}
		return lblAnimal;
	}
	private JTextField getTextField_1() {
		if (textField_1 == null) {
			textField_1 = new JTextField();
			textField_1.setBounds(54, 90, 133, 20);
			textField_1.setColumns(10);
		}
		return textField_1;
	}
	private JTextField getTextField_2() {
		if (textField_2 == null) {
			textField_2 = new JTextField();
			textField_2.setBounds(54, 118, 133, 20);
			textField_2.setColumns(10);
		}
		return textField_2;
	}
	private JTextField getTextField_3() {
		if (textField_3 == null) {
			textField_3 = new JTextField();
			textField_3.setBounds(54, 149, 133, 20);
			textField_3.setColumns(10);
		}
		return textField_3;
	}
	private JTextField getTextField_4() {
		if (textField_4 == null) {
			textField_4 = new JTextField();
			textField_4.setBounds(54, 180, 133, 20);
			textField_4.setColumns(10);
		}
		return textField_4;
	}
	private JTextField getTextField_5() {
		if (textField_5 == null) {
			textField_5 = new JTextField();
			textField_5.setBounds(54, 211, 133, 20);
			textField_5.setColumns(10);
		}
		return textField_5;
	}
	private JTextField getTextField_6() {
		if (textField_6 == null) {
			textField_6 = new JTextField();
			textField_6.setBounds(54, 242, 133, 20);
			textField_6.setColumns(10);
		}
		return textField_6;
	}
	private JLabel getLblAntcdentsconsultations() {
		if (lblAntcdentsconsultations == null) {
			lblAntcdentsconsultations = new JLabel("Ant\u00E9c\u00E9dents/consultations");
			lblAntcdentsconsultations.setBounds(247, 54, 133, 14);
		}
		return lblAntcdentsconsultations;
	}
	private JTextPane getTextPane() {
		if (textPane == null) {
			textPane = new JTextPane();
			textPane.setBounds(217, 82, 334, 227);
		}
		return textPane;
	}
}
