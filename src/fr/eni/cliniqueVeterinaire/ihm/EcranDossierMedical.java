package fr.eni.cliniqueVeterinaire.ihm;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import fr.eni.cliniqueVeterinaire.bll.AnimalManager;
import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.Font;


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
		setResizable(false);
		getContentPane().setFont(new Font("Segoe Script", Font.PLAIN, 12));
		getContentPane().setForeground(new Color(255, 255, 255));
		this.client = clientTemp;
		setTitle("Dossier Medical");
		setBounds(500, 200, 741, 482);
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
			btnValider.setBounds(527, 11, 89, 30);
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
			btnAnnuler.setBounds(626, 11, 89, 30);
		}
		return btnAnnuler;
	}

	private JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Client :");
			lblClient.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblClient.setBounds(8, 38, 67, 14);
		}
		return lblClient;
	}

	private JLabel getLblAnimal() {
		if (lblAnimal == null) {
			lblAnimal = new JLabel("Code animal :");
			lblAnimal.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblAnimal.setBounds(8, 73, 81, 14);
		}
		return lblAnimal;
	}

	private JTextField getTextCodeAnimal() {
		if (textCodeAnimal == null) {
			textCodeAnimal = new JTextField();
			textCodeAnimal.setEditable(false);
			textCodeAnimal.setBounds(87, 66, 133, 30);
			textCodeAnimal.setColumns(10);
		}
		return textCodeAnimal;
	}

	private JTextField getTextNomAnimal() {
		if (textNomAnimal == null) {
			textNomAnimal = new JTextField();
			textNomAnimal.setEditable(false);
			textNomAnimal.setBounds(87, 109, 133, 30);
			textNomAnimal.setColumns(10);
		}
		return textNomAnimal;
	}

	private JTextField getTextSexe() {
		if (textSexe == null) {
			textSexe = new JTextField();
			textSexe.setEditable(false);
			textSexe.setBounds(87, 152, 133, 30);
			textSexe.setColumns(10);
		}
		return textSexe;
	}

	private JTextField getTextCouleur() {
		if (textCouleur == null) {
			textCouleur = new JTextField();
			textCouleur.setEditable(false);
			textCouleur.setBounds(87, 195, 133, 30);
			textCouleur.setColumns(10);
		}
		return textCouleur;
	}

	private JTextField getTextRace() {
		if (textRace == null) {
			textRace = new JTextField();
			textRace.setEditable(false);
			textRace.setBounds(87, 281, 133, 30);
			textRace.setColumns(10);
		}
		return textRace;
	}

	private JTextField getTextEspece() {
		if (textEspece == null) {
			textEspece = new JTextField();
			textEspece.setEditable(false);
			textEspece.setBounds(87, 238, 133, 30);
			textEspece.setColumns(10);
		}
		return textEspece;
	}

	private JLabel getLblAntcdentsconsultations() {
		if (lblAntcdentsconsultations == null) {
			lblAntcdentsconsultations = new JLabel("Ant\u00E9c\u00E9dents/consultations");
			lblAntcdentsconsultations.setHorizontalAlignment(SwingConstants.CENTER);
			lblAntcdentsconsultations.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 16));
			lblAntcdentsconsultations.setHorizontalTextPosition(SwingConstants.CENTER);
			lblAntcdentsconsultations.setBounds(226, 29, 214, 28);
		}
		return lblAntcdentsconsultations;
	}

	private JTextPane getTextAntecedents() {
		if (textAntecedents == null) {
			textAntecedents = new JTextPane();
			textAntecedents.setBounds(230, 66, 485, 366);
		}
		return textAntecedents;
	}

	private JLabel getLblNomClient() {
		if (lblNomClient == null) {
			lblNomClient = new JLabel("");
			lblNomClient.setBounds(87, 38, 133, 14);
		}
		return lblNomClient;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Nom");
			lblNewLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblNewLabel.setBounds(8, 117, 81, 14);
		}
		return lblNewLabel;
	}

	private JLabel getLblSexe() {
		if (lblSexe == null) {
			lblSexe = new JLabel("Sexe");
			lblSexe.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblSexe.setBounds(8, 160, 81, 14);
		}
		return lblSexe;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Couleur");
			lblNewLabel_1.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblNewLabel_1.setBounds(8, 203, 81, 14);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblEspce() {
		if (lblEspce == null) {
			lblEspce = new JLabel("Esp\u00E8ce");
			lblEspce.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblEspce.setBounds(8, 246, 81, 14);
		}
		return lblEspce;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Race");
			lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblNewLabel_2.setBounds(8, 289, 81, 14);
		}
		return lblNewLabel_2;
	}
}
