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

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
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
	private JTextPane textPane;
	private JComboBox comboBoxClient;
	private ClientManager clientManager = ClientManager.getInstance();

	/**
	 * Create the application.
	 * 
	 * @param client
	 */
	public EcranDossierMedical(Client client) {

		setTitle("Dossier Medical");
		setBounds(100, 100, 577, 360);
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
		getContentPane().add(getTextPane());
		getContentPane().add(getComboBoxClient());

	}
	// private JComboBox getComboBoxClient() {
	// if (comboBoxClient == null) {
	// try {
	// List<Client> client =clientManager.getClientList();
	// comboBoxClient= new JComboBox(new String[]{});
	// for (int i = 0; i < client.size(); i++) {
	// comboBoxClient.addItem(client.get(i).getNomClient());
	//
	// }
	// comboBoxClient.setBounds(54, 51, 133, 20);
	//
	// } catch (BLLException e) {
	//
	// e.printStackTrace();
	// }
	//
	// }
	// return comboBoxClient;
	// }

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

	protected static void do_btnAnnuler_actionPerformed(ActionEvent arg0) {
	}

	private JLabel getLblAnimal() {
		if (lblAnimal == null) {
			lblAnimal = new JLabel("Animal :");
			lblAnimal.setBounds(10, 93, 46, 14);
		}
		return lblAnimal;
	}

	private JTextField getTextCodeAnimal() {
		if (textCodeAnimal == null) {
			textCodeAnimal = new JTextField();
			textCodeAnimal.setBounds(54, 90, 133, 20);
			textCodeAnimal.setColumns(10);
		}
		return textCodeAnimal;
	}

	private JTextField getTextNomAnimal() {
		if (textNomAnimal == null) {
			textNomAnimal = new JTextField();
			textNomAnimal.setBounds(54, 118, 133, 20);
			textNomAnimal.setColumns(10);
		}
		return textNomAnimal;
	}

	private JTextField getTextSexe() {
		if (textSexe == null) {
			textSexe = new JTextField();
			textSexe.setBounds(54, 149, 133, 20);
			textSexe.setColumns(10);
		}
		return textSexe;
	}

	private JTextField getTextCouleur() {
		if (textCouleur == null) {
			textCouleur = new JTextField();
			textCouleur.setBounds(54, 180, 133, 20);
			textCouleur.setColumns(10);
		}
		return textCouleur;
	}

	private JTextField getTextRace() {
		if (textRace == null) {
			textRace = new JTextField();
			textRace.setBounds(54, 211, 133, 20);
			textRace.setColumns(10);
		}
		return textRace;
	}

	private JTextField getTextEspece() {
		if (textEspece == null) {
			textEspece = new JTextField();
			textEspece.setBounds(54, 242, 133, 20);
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

	private JTextPane getTextPane() {
		if (textPane == null) {
			textPane = new JTextPane();
			textPane.setBounds(217, 82, 334, 227);
		}
		return textPane;
	}

	private JComboBox getComboBoxClient() {
		if (comboBoxClient == null) {
			
			

				try {
					comboBoxClient = new JComboBox();
					List<Client> clients = clientManager.getClientList();
					comboBoxClient = new JComboBox(new String[] {});
					for (int i = 0; i < clients.size(); i++) {
						comboBoxClient.addItem(clients.get(i).getNomClient());
//						comboBoxClient.addItemListener(new ItemListener() {
//							public void itemStateChanged(ItemEvent e) {
//								refreshCBAnimaux();
//							}
//						});

					}
					comboBoxClient.setBounds(54, 51, 133, 20);			} catch (BLLException e) {

					e.printStackTrace();
				}
			}
			return comboBoxClient;
			
	
			
			
		
}}
