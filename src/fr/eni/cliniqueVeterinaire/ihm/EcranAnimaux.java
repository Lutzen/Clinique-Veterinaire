package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.sun.corba.se.impl.logging.OMGSystemException;

import fr.eni.cliniqueVeterinaire.bll.AnimalManager;
import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bll.RacesManager;
import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Races;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EcranAnimaux extends JFrame {

	private JFrame frmAnimaux;
	private JButton btnAnnuler;
	private JButton btnValider;
	private JLabel lblClient;
	private JLabel lblClientNom;
	private JTextField txtNom;
	private JTextField txtCouleur;
	private JLabel lblNom;
	private JLabel lblCouleur;
	private JComboBox cBSexe;
	private JLabel lblEspce;
	private JComboBox cBEspece;
	private JLabel lblRace;
	private JComboBox cBRace;
	private JTextField txtTatouage;
	private JLabel lblTatouage;
	private AnimalManager animalManager = AnimalManager.getInstance();
	private ClientManager clientManager = ClientManager.getInstance();
	private RacesManager racesManager = RacesManager.getInstance();
	private String nomClient;
	private EcranRdv ecranRdv;
	private EcranClient ecranClient;

	public EcranAnimaux(String nomClient, EcranRdv ecran) {
		this.nomClient = nomClient;
		ecranRdv = (EcranRdv) ecran;
		setTitle("Animaux");
		setBounds(100, 100, 496, 223);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnAnnuler());
		getContentPane().add(getBtnValider());
		getContentPane().add(getLblClient());
		getContentPane().add(getLblClientNom());
		getContentPane().add(getTxtNom());
		getContentPane().add(getTxtCouleur());
		getContentPane().add(getLblNom());
		getContentPane().add(getLblCouleur());
		getContentPane().add(getCBSexe());
		getContentPane().add(getLblEspce());
		getContentPane().add(getCBEspece());
		getContentPane().add(getLblRace());
		getContentPane().add(getCBRace());
		getContentPane().add(getTxtTatouage());
		getContentPane().add(getLblTatouage());
	}

	public EcranAnimaux(String nomClient, EcranClient ecran) {
		this.nomClient = nomClient;
		ecranClient = (EcranClient) ecran;
		setTitle("Animaux");
		setBounds(100, 100, 496, 223);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnAnnuler());
		getContentPane().add(getBtnValider());
		getContentPane().add(getLblClient());
		getContentPane().add(getLblClientNom());
		getContentPane().add(getTxtNom());
		getContentPane().add(getTxtCouleur());
		getContentPane().add(getLblNom());
		getContentPane().add(getLblCouleur());
		getContentPane().add(getCBSexe());
		getContentPane().add(getLblEspce());
		getContentPane().add(getCBEspece());
		getContentPane().add(getLblRace());
		getContentPane().add(getCBRace());
		getContentPane().add(getTxtTatouage());
		getContentPane().add(getLblTatouage());
	}

	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.setBounds(379, 11, 89, 23);
		}
		return btnAnnuler;
	}

	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (txtNom.getText().isEmpty())
						JOptionPane.showMessageDialog(null, "Remplissez les champs");
					else {
						Client client = new Client();
						Animal animal = new Animal();
						try {
							client = clientManager.getClientByName(lblClientNom.getText());
							animal.setCodeClient(client.getCodeClient());
							animal.setCouleur(txtCouleur.getText());
							animal.setNomAnimal(txtNom.getText());
							animal.setTatouage(txtTatouage.getText());
							animal.setEspece((String) cBEspece.getSelectedItem());
							animal.setRace((String) cBRace.getSelectedItem());
							if (cBSexe.getSelectedItem() == "Mâle")
								animal.setSexe("M");
							else
								animal.setSexe("F");
							animalManager.addAnimal(animal);
							if (ecranClient != null)
								ecranClient.mettreAJour();
							else
								ecranRdv.refreshCBAnimaux();
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			});
			btnValider.setBounds(264, 11, 89, 23);
		}
		return btnValider;
	}

	private JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Client :");
			lblClient.setBounds(10, 27, 46, 14);
		}
		return lblClient;
	}

	private JLabel getLblClientNom() {
		if (lblClientNom == null) {
			lblClientNom = new JLabel();
			lblClientNom.setText(nomClient);
			lblClientNom.setBounds(87, 24, 196, 20);

		}
		return lblClientNom;
	}

	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
			txtNom.setBounds(87, 61, 167, 20);
			txtNom.setColumns(10);
		}
		return txtNom;
	}

	private JTextField getTxtCouleur() {
		if (txtCouleur == null) {
			txtCouleur = new JTextField();
			txtCouleur.setBounds(87, 92, 167, 20);
			txtCouleur.setColumns(10);
		}
		return txtCouleur;
	}

	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
			lblNom.setBounds(10, 64, 46, 14);
		}
		return lblNom;
	}

	private JLabel getLblCouleur() {
		if (lblCouleur == null) {
			lblCouleur = new JLabel("Couleur");
			lblCouleur.setBounds(10, 95, 46, 14);
		}
		return lblCouleur;
	}

	private JComboBox getCBSexe() {
		if (cBSexe == null) {
			cBSexe = new JComboBox(new String[] { "Mâle", "Femelle" });
			cBSexe.setBounds(264, 61, 71, 20);
		}
		return cBSexe;
	}

	private JLabel getLblEspce() {
		if (lblEspce == null) {
			lblEspce = new JLabel("Esp\u00E8ce");
			lblEspce.setBounds(10, 157, 46, 14);
		}
		return lblEspce;
	}

	private JComboBox getCBEspece() {
		if (cBEspece == null) {
			List<Races> races;
			try {
				races = racesManager.getRacesList();

				cBEspece = new JComboBox(new String[] {});
				for (int i = 0; i < races.size(); i++) {
					cBEspece.addItem(races.get(i).getEspece());
					cBEspece.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							refreshCBRace();
						}

					});

					cBEspece.setBounds(87, 154, 167, 20);
				}
			} catch (BLLException e1) {
				e1.printStackTrace();
			}
		}
		return cBEspece;
	}

	private JLabel getLblRace() {
		if (lblRace == null) {
			lblRace = new JLabel("Race");
			lblRace.setBounds(264, 157, 46, 14);
		}
		return lblRace;
	}

	private JComboBox getCBRace() {
		if (cBRace == null) {
			cBRace = new JComboBox();
			cBRace.setBounds(303, 154, 167, 20);
			refreshCBRace();
		}
		return cBRace;
	}

	private void refreshCBRace() {
		try {
			cBRace.removeAllItems();
			List<Races> races = racesManager.getRacesByEspece((String) cBEspece.getSelectedItem());
			for (int i = 0; i < races.size(); i++) {
				cBRace.addItem(races.get(i).getRace());
			}
		} catch (BLLException e) {

			e.printStackTrace();
		}

	}

	private JTextField getTxtTatouage() {
		if (txtTatouage == null) {
			txtTatouage = new JTextField();
			txtTatouage.setColumns(10);
			txtTatouage.setBounds(87, 123, 167, 20);
		}
		return txtTatouage;
	}

	private JLabel getLblTatouage() {
		if (lblTatouage == null) {
			lblTatouage = new JLabel("Tatouage");
			lblTatouage.setBounds(10, 126, 46, 14);
		}
		return lblTatouage;
	}

}
