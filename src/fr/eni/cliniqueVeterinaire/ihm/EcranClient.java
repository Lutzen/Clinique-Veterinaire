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
	private JTextField txtPrenom;
	private JTextField txtAdresse1;
	private JTextField txtAdresse02;
	private JTextField txtCodePostal;
	private JTextField txtVille;
	private JLabel lblCode;
	private JLabel lblNom;
	private JLabel lblPrenom;
	private JLabel lblAdresse;
	private JLabel lblCodePostal;
	private JLabel lblVille;
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
		setBounds(100, 100, 761, 377);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnRechercher());
		getContentPane().add(getBtnAjouter());
		getContentPane().add(getBtnSupprimer());
		getContentPane().add(getBtnValider());
		getContentPane().add(getBtnAnnuler());
		getContentPane().add(getTxtCode());
		getContentPane().add(getTxtNom());
		getContentPane().add(getTxtPrenom());
		getContentPane().add(getTxtAdresse1());
		getContentPane().add(getTxtAdresse02());
		getContentPane().add(getTxtCodePostal());
		getContentPane().add(getTxtVille());
		getContentPane().add(getLblCode());
		getContentPane().add(getLblNom());
		getContentPane().add(getLblPrenom());
		getContentPane().add(getLblAdresse());
		getContentPane().add(getLblCodePostal());
		getContentPane().add(getLblVille());
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
								frame = new EcranResultatsRecherche(EcranClient.this);
								frame.setVisible(true);

							} catch (IHMException e) {
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
	
	public void recupClient(Client client) throws IHMException {
		txtCode.setText(String.valueOf(client.getCodeClient()));
		txtNom.setText(client.getNomClient());
		txtPrenom.setText(client.getPrenomClient());
		txtAdresse1.setText(client.getAdresse1());
		txtAdresse02.setText(client.getAdresse2());
		txtCodePostal.setText(client.getCodePostal());
		txtVille.setText(client.getVille());
		mettreAJour();

		
	}

	private JButton getBtnAjouter() {
		if (btnAjouter == null) {
			btnAjouter = new JButton("Ajouter");
			btnAjouter.setBounds(266, 11, 89, 23);
		}
		return btnAjouter;
	}

	private JButton getBtnSupprimer() {
		if (btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer");
			btnSupprimer.setBounds(365, 11, 89, 23);
		}
		return btnSupprimer;
	}

	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
				}
			});
			btnValider.setBounds(547, 11, 89, 23);
		}
		return btnValider;
	}

	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.setBounds(646, 11, 89, 23);
		}
		return btnAnnuler;
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

	private JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField();
			txtPrenom.setColumns(10);
			txtPrenom.setBounds(86, 128, 170, 20);
		}
		return txtPrenom;
	}

	private JTextField getTxtAdresse1() {
		if (txtAdresse1 == null) {
			txtAdresse1 = new JTextField();
			txtAdresse1.setColumns(10);
			txtAdresse1.setBounds(86, 159, 170, 20);
		}
		return txtAdresse1;
	}

	private JTextField getTxtAdresse02() {
		if (txtAdresse02 == null) {
			txtAdresse02 = new JTextField();
			txtAdresse02.setColumns(10);
			txtAdresse02.setBounds(86, 190, 170, 20);
		}
		return txtAdresse02;
	}

	private JTextField getTxtCodePostal() {
		if (txtCodePostal == null) {
			txtCodePostal = new JTextField();
			txtCodePostal.setColumns(10);
			txtCodePostal.setBounds(86, 221, 170, 20);
		}
		return txtCodePostal;
	}

	private JTextField getTxtVille() {
		if (txtVille == null) {
			txtVille = new JTextField();
			txtVille.setColumns(10);
			txtVille.setBounds(86, 252, 170, 20);
		}
		return txtVille;
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

	private JLabel getLblPrenom() {
		if (lblPrenom == null) {
			lblPrenom = new JLabel("Pr\u00E9nom");
			lblPrenom.setBounds(10, 131, 46, 14);
		}
		return lblPrenom;
	}

	private JLabel getLblAdresse() {
		if (lblAdresse == null) {
			lblAdresse = new JLabel("Adresse");
			lblAdresse.setBounds(10, 162, 46, 14);
		}
		return lblAdresse;
	}

	private JLabel getLblCodePostal() {
		if (lblCodePostal == null) {
			lblCodePostal = new JLabel("Code postal");
			lblCodePostal.setBounds(10, 224, 66, 14);
		}
		return lblCodePostal;
	}

	private JLabel getLblVille() {
		if (lblVille == null) {
			lblVille = new JLabel("Ville");
			lblVille.setBounds(10, 255, 46, 14);
		}
		return lblVille;
	}

	private JButton getBtnEditer() {
		if (btnEditer == null) {
			btnEditer = new JButton("Editer");
			btnEditer.setBounds(646, 304, 89, 23);
		}
		return btnEditer;
	}

	private JButton getBtnSupprimer_1() {
		if (btnSupprimer_1 == null) {
			btnSupprimer_1 = new JButton("Supprimer");
			btnSupprimer_1.setBounds(547, 304, 89, 23);
		}
		return btnSupprimer_1;
	}

	private JButton getBtnAjouter_1() {
		if (btnAjouter_1 == null) {
			btnAjouter_1 = new JButton("Ajouter");
			btnAjouter_1.setBounds(448, 304, 89, 23);
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
			client = clientManager.getClientByCode(Integer.parseInt(txtCode.getText()));
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
			scrollPane.setBounds(266, 45, 469, 248);
		}
		return scrollPane;
	}

}
