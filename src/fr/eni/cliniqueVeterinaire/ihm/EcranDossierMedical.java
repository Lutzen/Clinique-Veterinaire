package fr.eni.cliniqueVeterinaire.ihm;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;

import fr.eni.cliniqueVeterinaire.bll.AnimalManager;
import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Personnel;

import javax.swing.JComboBox;

public class EcranDossierMedical extends JFrame {

	private JButton btnValider;
	private JButton btnAnnuler;
	private JLabel lblClient;
	private JLabel lblAnimal;
	private JTextField textCodeAnimal;
	private JTextField textNomAnimal;
	private JTextField textSexe;
	private JTextField textCouleur;
	private JTextField textRace;
	private JTextField textEspece;
	private JLabel lblAntcdentsconsultations;
	private JTextPane textAntecedents;
	private ClientManager clientManager = ClientManager.getInstance();
	private AnimalManager animalManager = AnimalManager.getInstance();
	private Client client;
	private JLabel lblNomClient;
	private Animal animal;
	private JLabel lblNewLabel;
	private JLabel lblSexe;
	private JLabel lblNewLabel_1;
	private JLabel lblEspce;
	private JLabel lblNewLabel_2;

	public EcranDossierMedical(Client clientTemp, String nomAnimal) {
		this.client = clientTemp;
		setTitle("Dossier Medical");
		setBounds(100, 100, 741, 482);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnValider());
		getContentPane().add(getBtnAnnuler());
		getContentPane().add(getLblClient());
		getContentPane().add(getLblAnimal());
		getContentPane().add(getTextCodeAnimal());
		getContentPane().add(getTextNomAnimal());
		getContentPane().add(getTextSexe());
		getContentPane().add(getTextCouleur());
		getContentPane().add(getTextRace());
		getContentPane().add(getTextEspece());
		getContentPane().add(getLblAntcdentsconsultations());
		getContentPane().add(getTextAntecedents());
		getContentPane().add(getLblNomClient());

		try {
			List<Animal> animaux = animalManager.getAnimalList(client.getCodeClient());
			for (Animal animalTemp : animaux) {
				if (animalTemp.getNomAnimal().equals(nomAnimal))
					animal = animalTemp;
			}
			lblNomClient.setText(client.getNomClient());
			textCodeAnimal.setText(String.valueOf(animal.getCodeAnimal()));
			textNomAnimal.setText(animal.getNomAnimal());
			textCouleur.setText(animal.getCouleur());
			textEspece.setText(animal.getEspece());
			textRace.setText(animal.getRace());
			textSexe.setText(animal.getSexe());
			textAntecedents.setText(animal.getAntecedents());
			getContentPane().add(getLblNewLabel());
			getContentPane().add(getLblSexe());
			getContentPane().add(getLblNewLabel_1());
			getContentPane().add(getLblEspce());
			getContentPane().add(getLblNewLabel_2());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						animal.setAntecedents(textAntecedents.getText());
						animalManager.updateAnimal(animal);
						JOptionPane.showMessageDialog(null, "Dossier médical modifié avec succès");
						dispose();
					} catch (BLLException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnValider.setBounds(527, 11, 89, 23);
		}
		return btnValider;
	}

	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					textAntecedents.setText(animal.getAntecedents());
				}
			});
			btnAnnuler.setBounds(626, 11, 89, 23);
		}
		return btnAnnuler;
	}

	private JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Client :");
			lblClient.setBounds(10, 54, 67, 14);
		}
		return lblClient;
	}

	private JLabel getLblAnimal() {
		if (lblAnimal == null) {
			lblAnimal = new JLabel("Code animal :");
			lblAnimal.setBounds(10, 93, 81, 14);
		}
		return lblAnimal;
	}

	private JTextField getTextCodeAnimal() {
		if (textCodeAnimal == null) {
			textCodeAnimal = new JTextField();
			textCodeAnimal.setEditable(false);
			textCodeAnimal.setBounds(87, 90, 133, 20);
			textCodeAnimal.setColumns(10);
		}
		return textCodeAnimal;
	}

	private JTextField getTextNomAnimal() {
		if (textNomAnimal == null) {
			textNomAnimal = new JTextField();
			textNomAnimal.setEditable(false);
			textNomAnimal.setBounds(87, 118, 133, 20);
			textNomAnimal.setColumns(10);
		}
		return textNomAnimal;
	}

	private JTextField getTextSexe() {
		if (textSexe == null) {
			textSexe = new JTextField();
			textSexe.setEditable(false);
			textSexe.setBounds(87, 149, 133, 20);
			textSexe.setColumns(10);
		}
		return textSexe;
	}

	private JTextField getTextCouleur() {
		if (textCouleur == null) {
			textCouleur = new JTextField();
			textCouleur.setEditable(false);
			textCouleur.setBounds(87, 180, 133, 20);
			textCouleur.setColumns(10);
		}
		return textCouleur;
	}

	private JTextField getTextRace() {
		if (textRace == null) {
			textRace = new JTextField();
			textRace.setEditable(false);
			textRace.setBounds(87, 242, 133, 20);
			textRace.setColumns(10);
		}
		return textRace;
	}

	private JTextField getTextEspece() {
		if (textEspece == null) {
			textEspece = new JTextField();
			textEspece.setEditable(false);
			textEspece.setBounds(87, 211, 133, 20);
			textEspece.setColumns(10);
		}
		return textEspece;
	}

	private JLabel getLblAntcdentsconsultations() {
		if (lblAntcdentsconsultations == null) {
			lblAntcdentsconsultations = new JLabel("Ant\u00E9c\u00E9dents/consultations");
			lblAntcdentsconsultations.setBounds(247, 54, 193, 14);
		}
		return lblAntcdentsconsultations;
	}

	private JTextPane getTextAntecedents() {
		if (textAntecedents == null) {
			textAntecedents = new JTextPane();
			textAntecedents.setBounds(230, 79, 485, 353);
		}
		return textAntecedents;
	}

	private JLabel getLblNomClient() {
		if (lblNomClient == null) {
			lblNomClient = new JLabel("");
			lblNomClient.setBounds(87, 54, 133, 14);
		}
		return lblNomClient;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Nom");
			lblNewLabel.setBounds(10, 121, 67, 14);
		}
		return lblNewLabel;
	}

	private JLabel getLblSexe() {
		if (lblSexe == null) {
			lblSexe = new JLabel("Sexe");
			lblSexe.setBounds(10, 152, 67, 14);
		}
		return lblSexe;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Couleur");
			lblNewLabel_1.setBounds(10, 183, 67, 14);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblEspce() {
		if (lblEspce == null) {
			lblEspce = new JLabel("Esp\u00E8ce");
			lblEspce.setBounds(10, 214, 67, 14);
		}
		return lblEspce;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Race");
			lblNewLabel_2.setBounds(10, 245, 67, 14);
		}
		return lblNewLabel_2;
	}
}
