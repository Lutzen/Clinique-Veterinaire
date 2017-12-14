package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
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
import java.awt.Font;

public class EcranAnimaux extends JFrame {

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
	private Animal animal;
	private Client client;
	private JLabel lblSexe;

	
	/**
	 * @wbp.parser.constructor
	
	 */
	public EcranAnimaux(String nomClient, EcranRdv ecran) {
		this.nomClient = nomClient;
		ecranRdv = (EcranRdv) ecran;
		animal = new Animal();
		initialize();

	}

	public EcranAnimaux(String nomClient, EcranClient ecran) {
		this.nomClient = nomClient;
		ecranClient = (EcranClient) ecran;
		animal = new Animal();
		initialize();

	}

	public EcranAnimaux(String nomClient, int codeAnimal, EcranClient ecran) {
		ecranClient = (EcranClient) ecran;
		this.nomClient = nomClient;
		animal = new Animal();
		initialize();
		try {
			animal = animalManager.getAnimalByCode(codeAnimal);
			txtNom.setText(animal.getNomAnimal());
			txtCouleur.setText(animal.getCouleur());
			txtTatouage.setText(animal.getTatouage());
			cBEspece.setSelectedItem(animal.getEspece());
			cBRace.setSelectedItem(animal.getRace());
			if (animal.getSexe() == "M")
				cBSexe.setSelectedIndex(0);
			else
				cBSexe.setSelectedItem(1);
		} catch (BLLException e) {
			e.printStackTrace();
		}

	}

	private void initialize() {
		setTitle("Animaux");
		setBounds(850, 300, 313, 375);
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
		getContentPane().add(getLblSexe());
	}

	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			btnAnnuler.setBounds(198, 300, 89, 30);
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

						try {
							client = clientManager.getClientByName(nomClient);
							animal.setCodeClient(client.getCodeClient());
							animal.setCouleur(txtCouleur.getText());
							animal.setNomAnimal(txtNom.getText());
							animal.setTatouage(txtTatouage.getText());
							animal.setEspece((String) cBEspece.getSelectedItem());
							animal.setRace((String) cBRace.getSelectedItem());
							if (cBSexe.getSelectedItem() == "Mâle")
								animal.setSexe("M");
							if (cBSexe.getSelectedItem() == "Femelle")
								animal.setSexe("F");
							else
								animal.setSexe("H");

							if (animal.getCodeAnimal() == 0) {
								JOptionPane.showMessageDialog(null, "Animal ajouté avec succès");
								animalManager.addAnimal(animal);
							}

							else {
								JOptionPane.showMessageDialog(null, "Animal mis à jour avec succès");
								animalManager.updateAnimal(animal);
							}

							if (ecranClient != null)
								ecranClient.mettreAJour();
							else
								ecranRdv.refreshCBAnimaux();
							dispose();
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});
			btnValider.setBounds(97, 300, 89, 30);
		}
		return btnValider;
	}

	private JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Client :");
			lblClient.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblClient.setBounds(10, 31, 46, 14);
		}
		return lblClient;
	}

	private JLabel getLblClientNom() {
		if (lblClientNom == null) {
			lblClientNom = new JLabel();
			lblClientNom.setText(nomClient);
			lblClientNom.setBounds(87, 28, 150, 20);

		}
		return lblClientNom;
	}

	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
			
			txtNom.addKeyListener(new KeyAdapter() {
			    public void keyTyped(java.awt.event.KeyEvent e) { 
			        if (txtNom.getText().length() >= 30 )
			            e.consume(); 
			    }  
			});
			
			txtNom.setBounds(87, 61, 200, 30);
			txtNom.setColumns(10);
		}
		return txtNom;
	}

	private JTextField getTxtCouleur() {
		if (txtCouleur == null) {
			txtCouleur = new JTextField();
			
			txtCouleur.addKeyListener(new KeyAdapter() {
			    public void keyTyped(java.awt.event.KeyEvent e) { 
			        if (txtCouleur.getText().length() >= 20 )
			            e.consume(); 
			    }  
			});
			
			txtCouleur.setBounds(87, 92, 200, 30);
			txtCouleur.setColumns(10);
		}
		return txtCouleur;
	}

	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
			lblNom.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblNom.setBounds(10, 69, 46, 14);
		}
		return lblNom;
	}

	private JLabel getLblCouleur() {
		if (lblCouleur == null) {
			lblCouleur = new JLabel("Couleur");
			lblCouleur.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblCouleur.setBounds(10, 100, 46, 14);
		}
		return lblCouleur;
	}

	private JComboBox getCBSexe() {
		if (cBSexe == null) {
			cBSexe = new JComboBox(new String[] { "Mâle", "Femelle","Hermaphrodite" });
			cBSexe.setBounds(87, 247, 200, 30);
		}
		return cBSexe;
	}

	private JLabel getLblEspce() {
		if (lblEspce == null) {
			lblEspce = new JLabel("Esp\u00E8ce");
			lblEspce.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblEspce.setBounds(10, 171, 46, 14);
		}
		return lblEspce;
	}

	private JComboBox getCBEspece() {
		if (cBEspece == null) {
			List<Races> races;
			try {
				races = racesManager.getRacesList();

				cBEspece = new JComboBox(new String[] {});
				cBEspece.setBounds(87, 163, 200, 30);

				for (int i = 0; i < races.size(); i++) {
					cBEspece.addItem(races.get(i).getEspece());
					cBEspece.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							refreshCBRace();
						}

					});

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
			lblRace.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblRace.setBounds(10, 213, 46, 14);
		}
		return lblRace;
	}

	private JComboBox getCBRace() {
		if (cBRace == null) {
			cBRace = new JComboBox();
			cBRace.setBounds(87, 205, 200, 30);
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
			
			txtTatouage.addKeyListener(new KeyAdapter() {
			    public void keyTyped(java.awt.event.KeyEvent e) { 
			        if (txtTatouage.getText().length() >= 10 )
			            e.consume(); 
			    }  
			});
			
			txtTatouage.setColumns(10);
			txtTatouage.setBounds(87, 123, 200, 30);
		}
		return txtTatouage;
	}

	private JLabel getLblTatouage() {
		if (lblTatouage == null) {
			lblTatouage = new JLabel("Tatouage");
			lblTatouage.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblTatouage.setBounds(10, 131, 65, 14);
		}
		return lblTatouage;
	}
	private JLabel getLblSexe() {
		if (lblSexe == null) {
			lblSexe = new JLabel("Sexe");
			lblSexe.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblSexe.setBounds(10, 254, 65, 16);
		}
		return lblSexe;
	}
}
