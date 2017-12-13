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
import fr.eni.cliniqueVeterinaire.bo.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;

public class EcranClient extends JFrame {

	private JButton btnRechercher;
	private JButton btnAjouterClient;
	private JButton btnSupprimerClient;
	private JButton btnEditerClient;
	private JButton btnAnnuler;
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
	private JButton btnEditerAnimal;
	private JButton btnSupprimerAnimal;
	private JButton btnAjouterAnimal;
	private JTable table;
	private AnimalManager animalManager = AnimalManager.getInstance();
	private ClientManager clientManager = ClientManager.getInstance();
	private Client client;
	private JScrollPane scrollPane;
	private ModeleAnimaux modeleAnimaux;
	private JLabel lblAnimaux;
	private JLabel lblCodeClient;
	private JLabel lblClient;

	public EcranClient() throws IHMException {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setTitle("CLIENTS");
		setBounds(500, 300, 853, 407);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnRechercher());
		getContentPane().add(getBtnAjouterClient());
		getContentPane().add(getBtnSupprimerClient());
		getContentPane().add(getBtnEditerClient());
		getContentPane().add(getBtnAnnuler());
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
		getContentPane().add(getBtnEditerAnimal());
		getContentPane().add(getBtnSupprimerAnimal());
		getContentPane().add(getBtnAjouterAnimal());
		getContentPane().add(getTable());
		getContentPane().add(getScrollPane());
		getContentPane().add(getLblAnimaux());
		getContentPane().add(getLblCodeClient());
		getContentPane().add(getLblClient());

	}

	private JButton getBtnRechercher() {
		if (btnRechercher == null) {
			btnRechercher = new JButton("Rechercher");
			btnRechercher.setToolTipText("");
			btnRechercher.setIcon(new ImageIcon("resources\\vet\\searchClient32.png"));
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
			btnRechercher.setBounds(133, 8, 148, 40);

		}
		return btnRechercher;
	}

	public void recupClient(Client client) throws IHMException {
		this.client = client;
		lblCodeClient.setText(String.valueOf(client.getCodeClient()));
		txtNom.setText(client.getNomClient());
		txtPrenom.setText(client.getPrenomClient());
		txtAdresse1.setText(client.getAdresse1());
		txtAdresse02.setText(client.getAdresse2());
		txtCodePostal.setText(client.getCodePostal());
		txtVille.setText(client.getVille());
		if (client.getCodeClient() != 0)
			mettreAJour();

	}

	private JButton getBtnAjouterClient() {
		if (btnAjouterClient == null) {
			btnAjouterClient = new JButton("");
			btnAjouterClient.setToolTipText("Ajouter un client");
			btnAjouterClient.setIcon(new ImageIcon("resources\\vet\\addAnimal32.png"));
			btnAjouterClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							EcranAjoutClient frame = new EcranAjoutClient(EcranClient.this);
							frame.setVisible(true);
						}
					});
				}

			});
			btnAjouterClient.setBounds(189, 317, 40, 40);
		}
		return btnAjouterClient;
	}

	private JButton getBtnSupprimerClient() {
		if (btnSupprimerClient == null) {
			btnSupprimerClient = new JButton("");
			btnSupprimerClient.setToolTipText("Supprimer un client");
			btnSupprimerClient.setIcon(new ImageIcon("resources\\vet\\delete32.png"));
			btnSupprimerClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {

						if (lblCodeClient.getText() != "" & lblCodeClient.getText() != "0"
								& lblCodeClient.getText() != null) {
							int result = JOptionPane.showConfirmDialog(null,
									"Voulez-vous vraiment archiver ce client?");
							if (result == 0) {
								clientManager.deleteClient(Integer.parseInt(lblCodeClient.getText()));
								JOptionPane.showMessageDialog(null, "Client supprim� avec succ�s");
								Client client = new Client();
								recupClient(client);
							} else {
							}

						} else {
							JOptionPane.showMessageDialog(null, "Aucun client selectionn�");
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnSupprimerClient.setBounds(241, 317, 40, 40);
		}
		return btnSupprimerClient;
	}

	private JButton getBtnEditerClient() {
		if (btnEditerClient == null) {
			btnEditerClient = new JButton("");
			btnEditerClient.setToolTipText("Editer un client");
			btnEditerClient.setIcon(new ImageIcon("resources\\vet\\edit32.png"));
			btnEditerClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (lblCodeClient.getText() != "" & lblCodeClient.getText() != "0") {
							Client client = new Client();
							client.setCodeClient(Integer.parseInt(lblCodeClient.getText()));
							client.setNomClient(txtNom.getText());
							client.setPrenomClient(txtPrenom.getText());
							client.setVille(txtVille.getText());
							client.setAdresse1(txtAdresse1.getText());
							client.setAdresse2(txtAdresse02.getText());
							client.setCodePostal(txtCodePostal.getText());
							clientManager.updateClient(client);
							JOptionPane.showMessageDialog(null, "Client modifi� avec succ�s");

						} else {
							JOptionPane.showMessageDialog(null, "Aucun client selectionn�");
						}

					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
			btnEditerClient.setBounds(293, 317, 40, 40);
		}
		return btnEditerClient;
	}

	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						recupClient(client);
					} catch (IHMException e1) {
						e1.printStackTrace();
					}
				}
			});
			btnAnnuler.setBounds(88, 317, 85, 40);
		}
		return btnAnnuler;
	}

	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
			txtNom.setColumns(10);
			txtNom.setBounds(88, 77, 245, 28);
		}
		return txtNom;
	}

	private JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField();
			txtPrenom.setColumns(10);
			txtPrenom.setBounds(88, 117, 245, 28);
		}
		return txtPrenom;
	}

	private JTextField getTxtAdresse1() {
		if (txtAdresse1 == null) {
			txtAdresse1 = new JTextField();
			txtAdresse1.setColumns(10);
			txtAdresse1.setBounds(88, 157, 245, 28);
		}
		return txtAdresse1;
	}

	private JTextField getTxtAdresse02() {
		if (txtAdresse02 == null) {
			txtAdresse02 = new JTextField();
			txtAdresse02.setColumns(10);
			txtAdresse02.setBounds(88, 197, 245, 28);
		}
		return txtAdresse02;
	}

	private JTextField getTxtCodePostal() {
		if (txtCodePostal == null) {
			txtCodePostal = new JTextField();
			txtCodePostal.setColumns(10);
			txtCodePostal.setBounds(88, 237, 245, 28);
		}
		return txtCodePostal;
	}

	private JTextField getTxtVille() {
		if (txtVille == null) {
			txtVille = new JTextField();
			txtVille.setColumns(10);
			txtVille.setBounds(88, 277, 245, 28);
		}
		return txtVille;
	}

	private JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("Code");
			lblCode.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblCode.setBounds(10, 51, 46, 14);
		}
		return lblCode;
	}

	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
			lblNom.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblNom.setBounds(10, 77, 46, 28);
		}
		return lblNom;
	}

	private JLabel getLblPrenom() {
		if (lblPrenom == null) {
			lblPrenom = new JLabel("Pr\u00E9nom");
			lblPrenom.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblPrenom.setBounds(10, 117, 46, 28);
		}
		return lblPrenom;
	}

	private JLabel getLblAdresse() {
		if (lblAdresse == null) {
			lblAdresse = new JLabel("Adresse");
			lblAdresse.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblAdresse.setBounds(10, 157, 62, 28);
		}
		return lblAdresse;
	}

	private JLabel getLblCodePostal() {
		if (lblCodePostal == null) {
			lblCodePostal = new JLabel("Code postal");
			lblCodePostal.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblCodePostal.setBounds(10, 237, 75, 28);
		}
		return lblCodePostal;
	}

	private JLabel getLblVille() {
		if (lblVille == null) {
			lblVille = new JLabel("Ville");
			lblVille.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblVille.setBounds(10, 277, 46, 28);
		}
		return lblVille;
	}

	private JButton getBtnEditerAnimal() {
		if (btnEditerAnimal == null) {
			btnEditerAnimal = new JButton("");
			btnEditerAnimal.setToolTipText("Editer un animal");
			btnEditerAnimal.setIcon(new ImageIcon("resources\\vet\\edit32.png"));
			btnEditerAnimal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							if (txtNom.getText().isEmpty() || table.getSelectedRow() == -1)
								JOptionPane.showMessageDialog(null, "Aucun animal/client selectionn�");
							else {
								EcranAnimaux frame = new EcranAnimaux(txtNom.getText(),
										(int) table.getValueAt(table.getSelectedRow(), 0), EcranClient.this);
								frame.setVisible(true);
							}
						}
					});

				}
			});
			btnEditerAnimal.setBounds(783, 317, 40, 40);
		}
		return btnEditerAnimal;
	}

	private JButton getBtnSupprimerAnimal() {
		if (btnSupprimerAnimal == null) {
			btnSupprimerAnimal = new JButton("");
			btnSupprimerAnimal.setToolTipText("Supprimer un animal");
			btnSupprimerAnimal.setIcon(new ImageIcon("resources\\vet\\delete32.png"));
			btnSupprimerAnimal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (table.getRowCount() != 0 & table.getSelectedRow() != -1) {
							int row = table.convertRowIndexToModel(table.getSelectedRow());
							int codeAnimal = (int) table.getValueAt(row, 0);
							int result = JOptionPane.showConfirmDialog(null,
									"Voulez-vous vraiment archiver cet animal?");
							if (result == 0) {
								animalManager.deleteAnimal(codeAnimal);
								JOptionPane.showMessageDialog(null, "Animal supprim� avec succ�s");
								mettreAJour();
							} else {
							}
						} else {
							JOptionPane.showMessageDialog(null, "Aucun animal selectionn�");
						}

					} catch (Exception e1) {
						e1.printStackTrace();

					}

				}
			});
			btnSupprimerAnimal.setBounds(731, 317, 40, 40);
		}
		return btnSupprimerAnimal;
	}

	private JButton getBtnAjouterAnimal() {
		if (btnAjouterAnimal == null) {
			btnAjouterAnimal = new JButton("");
			btnAjouterAnimal.setToolTipText("Ajouter un animal");
			btnAjouterAnimal.setIcon(new ImageIcon("resources\\vet\\addAnimal32.png"));
			btnAjouterAnimal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							if (txtNom.getText().isEmpty())
								JOptionPane.showMessageDialog(null, "Aucun client selectionn�");
							else {
								EcranAnimaux frame = new EcranAnimaux(txtNom.getText(), EcranClient.this);
								frame.setVisible(true);
							}
						}
					});
				}

			});
			btnAjouterAnimal.setBounds(679, 317, 40, 40);
		}
		return btnAjouterAnimal;
	}

	private JTable getTable() throws IHMException {
		if (table == null) {

			try {
				table = new JTable(getModeleAnimaux());
				table.setBounds(10, 133, 561, 318);

			} catch (Exception e) {
				throw new IHMException("Erreur � la recup�ration de la table", e);
			}

		}
		return table;

	}

	public void mettreAJour() throws IHMException {

		try {
			client = clientManager.getClientByCode(Integer.parseInt(lblCodeClient.getText()));
			modeleAnimaux.setData(client.getCodeClient());

		} catch (BLLException e) {
			e.printStackTrace();
			throw new IHMException("Erreur � la mise � jour de la table", e);

		}
	}

	private ModeleAnimaux getModeleAnimaux() throws IHMException {
		if (modeleAnimaux == null) {
			try {

				modeleAnimaux = new ModeleAnimaux(0);
			} catch (BLLException e) {

				e.printStackTrace();
				throw new IHMException("Erreur � l'appel getModeleAnimaux de la table", e);

			}

		}
		return modeleAnimaux;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(367, 47, 456, 258);
		}
		return scrollPane;
	}

	private JLabel getLblAnimaux() {
		if (lblAnimaux == null) {
			lblAnimaux = new JLabel("Animaux");
			lblAnimaux.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 18));
			lblAnimaux.setBounds(378, 12, 191, 28);
		}
		return lblAnimaux;
	}

	private JLabel getLblCodeClient() {
		if (lblCodeClient == null) {
			lblCodeClient = new JLabel("");
			lblCodeClient.setBounds(88, 51, 95, 20);
		}
		return lblCodeClient;
	}

	private JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Client");
			lblClient.setFont(new Font("SansSerif", Font.BOLD | Font.ITALIC, 18));
			lblClient.setBackground(Color.LIGHT_GRAY);
			lblClient.setBounds(38, 12, 102, 27);
		}
		return lblClient;
	}
}
