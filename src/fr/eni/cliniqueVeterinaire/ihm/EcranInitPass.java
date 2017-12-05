package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

public class EcranInitPass {

	private JFrame frame;
	private JLabel lblNouveauMotDe;
	private JTextField textField;
	private JButton btnNewButton;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranInitPass window = new EcranInitPass();
					window.frame.setVisible(true);
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
		frame.setBounds(100, 100, 186, 126);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getLblNouveauMotDe());
		frame.getContentPane().add(getTextField());
		frame.getContentPane().add(getBtnNewButton());
	}
	private JLabel getLblNouveauMotDe() {
		if (lblNouveauMotDe == null) {
			lblNouveauMotDe = new JLabel("Nouveau mot de passe");
			lblNouveauMotDe.setBounds(10, 11, 144, 14);
		}
		return lblNouveauMotDe;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(10, 25, 144, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Valider");
			btnNewButton.setBounds(65, 53, 89, 23);
		}
		return btnNewButton;
	}
}
