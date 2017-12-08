package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

import fr.eni.cliniqueVeterinaire.bll.AnimalManager;
import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;

import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

public class EcranClient extends JFrame {

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
	private JButton btnEditer;
	private JButton btnSupprimer_1;
	private JButton btnAjouter_1;
	private JTable table;
	private AnimalManager animalManager = AnimalManager.getInstance();
	private ClientManager clientManager = ClientManager.getInstance();
	private Client client;
	private Animal animal;
	private JScrollPane scrollPane;
	private ModeleAnimaux modeleAnimaux;

	public EcranClient() throws IHMException {
		setTitle("Clients");
		setBounds(100, 100, 622, 377);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnRechercher());
		getContentPane().add(getBtnAjouter());
		getContentPane().add(getBtnSupprimer());
		getContentPane().add(getBtnValider());
		getContentPane().add(getBtnAnnuler());
		getContentPane().add(getTxtCode());
		getContentPane().add(getTxtNom());
		getContentPane().add(getTextField_2());
		getContentPane().add(getTextField_3());
		getContentPane().add(getTextField_4());
		getContentPane().add(getTextField_5());
		getContentPane().add(getTextField_6());
		getContentPane().add(getLblCode());
		getContentPane().add(getLblNom());
		getContentPane().add(getTxtPrenom());
		getContentPane().add(getTxtAdresse());
		getContentPane().add(getTxtCodePostal());
		getContentPane().add(getTxtVille());
		getContentPane().add(getBtnEditer());
		getContentPane().add(getBtnSupprimer_1());
		getContentPane().add(getBtnAjouter_1());
		getContentPane().add(getTable());
		getContentPane().add(getScrollPane());

	}

	private JButton getBtnRechercher() {
		if (btnRechercher == null) {
			btnRechercher = new JButton("Rechercher");
			btnRechercher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {
								EcranResultatsRecherche frame;
								try {
									frame = new EcranResultatsRecherche();
									frame.setVisible(true);

								} catch (IHMException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
							}
						});
					
				}
			});
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

	private JTable getTable() throws IHMException {
		if (table == null) {

			try {
				table = new JTable(getModeleAnimaux());
				table.setBounds(10, 133, 561, 318);

			} catch (Exception e) {
				throw new IHMException("Erreur à la recupération de la table", e);
			}

		}
		return table;

	}

	public void mettreAJour() throws IHMException {

		try {
			// client = clientManager.getClientByCode(Integer.parseInt(txtCode.getText()));
			client = clientManager.getClientByCode(1);
			modeleAnimaux.setData(client.getCodeClient());

		} catch (BLLException e) {
			e.printStackTrace();
			throw new IHMException("Erreur à la mise à jour de la table", e);

		}
	}

	private ModeleAnimaux getModeleAnimaux() throws IHMException {
		if (modeleAnimaux == null) {
			try {

				modeleAnimaux = new ModeleAnimaux(0);
			} catch (BLLException e) {

				e.printStackTrace();
				throw new IHMException("Erreur à l'appel getModeleAnimaux de la table", e);

			}

		}
		return modeleAnimaux;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(266, 45, 330, 248);
		}
		return scrollPane;
	}

}
