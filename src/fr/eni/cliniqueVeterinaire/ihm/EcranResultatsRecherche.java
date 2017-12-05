package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JList;

public class EcranResultatsRecherche {

	private JFrame frmResultatsDeLa;
	private JTextField txtRechercher;
	private JButton btnRechercher;
	private JList listResultats;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranResultatsRecherche window = new EcranResultatsRecherche();
					window.frmResultatsDeLa.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EcranResultatsRecherche() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmResultatsDeLa = new JFrame();
		frmResultatsDeLa.setTitle("Resultats de la recherche");
		frmResultatsDeLa.setBounds(100, 100, 450, 300);
		frmResultatsDeLa.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmResultatsDeLa.getContentPane().setLayout(null);
		frmResultatsDeLa.getContentPane().add(getTxtRechercher());
		frmResultatsDeLa.getContentPane().add(getBtnRechercher());
		frmResultatsDeLa.getContentPane().add(getListResultats());
	}

	private JTextField getTxtRechercher() {
		if (txtRechercher == null) {
			txtRechercher = new JTextField();
			txtRechercher.setBounds(10, 11, 252, 20);
			txtRechercher.setColumns(10);
		}
		return txtRechercher;
	}
	private JButton getBtnRechercher() {
		if (btnRechercher == null) {
			btnRechercher = new JButton("Rechercher");
			btnRechercher.setBounds(272, 10, 89, 23);
		}
		return btnRechercher;
	}
	private JList getListResultats() {
		if (listResultats == null) {
			listResultats = new JList();
			listResultats.setBounds(10, 42, 414, 208);
		}
		return listResultats;
	}
}
