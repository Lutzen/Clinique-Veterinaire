package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
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
import javax.swing.JPanel;
import java.awt.SystemColor;

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
	private EcranRdv ecranRdv;
	private JPanel panel;
	private JPanel panel_1;

	public EcranClient(EcranRdv ecran) throws IHMException {
		ecranRdv = ecran;
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 12));
		setTitle("CLIENTS");
		setBounds(500, 300, 856, 439);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getTable());
		getContentPane().add(getScrollPane());
		getContentPane().add(getLblAnimaux());
		getContentPane().add(getPanel());
		getContentPane().add(getPanel_1());

	}

	private JButton getBtnRechercher() {
		if (btnRechercher == null) {
			btnRechercher = new JButton("Rechercher");
			btnRechercher.setBounds(139, 10, 148, 40);
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
			btnAjouterClient.setBounds(195, 342, 40, 40);
			btnAjouterClient.setToolTipText("Ajouter un client");
			btnAjouterClient.setIcon(new ImageIcon("resources\\vet\\addAnimal32.png"));
			btnAjouterClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					try {
						if (txtNom.getText().isEmpty() || txtPrenom.getText().isEmpty()
								|| txtVille.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Remplissez tout les champs!");
						} else {
							Client client = new Client();
							client.setAdresse1(txtAdresse1.getText());
							client.setAdresse2(txtAdresse02.getText());
							client.setCodePostal(txtCodePostal.getText());
							client.setNomClient(txtNom.getText());
							client.setPrenomClient(getTxtPrenom().getText());
							client.setVille(txtVille.getText());
							clientManager.addClient(client);
							JOptionPane.showMessageDialog(null, "Client ajouté avec succès");
							client = clientManager.getClientByName(client.getNomClient());
							lblCodeClient.setText(String.valueOf(client.getCodeClient()));
							ecranRdv.RefreshCBClients();
							mettreAJour();
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}

			});
		}
		return btnAjouterClient;
	}

	private JButton getBtnSupprimerClient() {
		if (btnSupprimerClient == null) {
			btnSupprimerClient = new JButton("");
			btnSupprimerClient.setBounds(247, 342, 40, 40);
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
								client = new Client();
								recupClient(client);
								mettreAJour();
								ecranRdv.RefreshCBClients();
								JOptionPane.showMessageDialog(null, "Client supprimé avec succès");
							} else {
							}

						} else {
							JOptionPane.showMessageDialog(null, "Aucun client selectionné");
						}

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
		}
		return btnSupprimerClient;
	}

	private JButton getBtnEditerClient() {
		if (btnEditerClient == null) {
			btnEditerClient = new JButton("");
			btnEditerClient.setBounds(299, 342, 40, 40);
			btnEditerClient.setToolTipText("Editer un client");
			btnEditerClient.setIcon(new ImageIcon("resources\\vet\\edit32.png"));
			btnEditerClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (lblCodeClient.getText() != "" & lblCodeClient.getText() != "0" & txtNom.getText().isEmpty()
								& txtPrenom.getText().isEmpty() & txtVille.getText().isEmpty()) {
							Client client = new Client();
							client.setCodeClient(Integer.parseInt(lblCodeClient.getText()));
							client.setNomClient(txtNom.getText());
							client.setPrenomClient(txtPrenom.getText());
							client.setVille(txtVille.getText());
							client.setAdresse1(txtAdresse1.getText());
							client.setAdresse2(txtAdresse02.getText());
							client.setCodePostal(txtCodePostal.getText());
							clientManager.updateClient(client);
							JOptionPane.showMessageDialog(null, "Client modifié avec succès");

						} else {
							JOptionPane.showMessageDialog(null, "Aucun client selectionné");
						}

					} catch (BLLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}
			});
		}
		return btnEditerClient;
	}

	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.setBounds(98, 342, 85, 40);
			btnAnnuler.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						recupClient(client);
					} catch (IHMException e1) {
						e1.printStackTrace();
					}
				}
			});
		}
		return btnAnnuler;
	}

	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
			txtNom.setBounds(94, 102, 245, 28);

			txtNom.addKeyListener(new KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if (txtNom.getText().length() >= 20)
						e.consume();
				}
			});

			txtNom.setColumns(10);
		}
		return txtNom;
	}

	private JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField();
			txtPrenom.setBounds(94, 142, 245, 28);

			txtPrenom.addKeyListener(new KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if (txtPrenom.getText().length() >= 20)
						e.consume();
				}
			});

			txtPrenom.setColumns(10);
		}
		return txtPrenom;
	}

	private JTextField getTxtAdresse1() {
		if (txtAdresse1 == null) {
			txtAdresse1 = new JTextField();
			txtAdresse1.setBounds(94, 182, 245, 28);

			txtAdresse1.addKeyListener(new KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if (txtAdresse1.getText().length() >= 30)
						e.consume();
				}
			});

			txtAdresse1.setColumns(10);
		}
		return txtAdresse1;
	}

	private JTextField getTxtAdresse02() {
		if (txtAdresse02 == null) {
			txtAdresse02 = new JTextField();
			txtAdresse02.setBounds(94, 222, 245, 28);

			txtAdresse02.addKeyListener(new KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if (txtAdresse02.getText().length() >= 30)
						e.consume();
				}
			});

			txtAdresse02.setColumns(10);
		}
		return txtAdresse02;
	}

	private JTextField getTxtCodePostal() {
		if (txtCodePostal == null) {
			txtCodePostal = new JTextField();
			txtCodePostal.setBounds(94, 262, 245, 28);

			txtCodePostal.addKeyListener(new KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if (txtCodePostal.getText().length() >= 6)
						e.consume();
				}
			});

			txtCodePostal.setColumns(10);
		}
		return txtCodePostal;
	}

	private JTextField getTxtVille() {
		if (txtVille == null) {
			txtVille = new JTextField();
			txtVille.setBounds(94, 302, 245, 28);

			txtVille.addKeyListener(new KeyAdapter() {
				public void keyTyped(java.awt.event.KeyEvent e) {
					if (txtVille.getText().length() >= 25)
						e.consume();
				}
			});

			txtVille.setColumns(10);
		}
		return txtVille;
	}

	private JLabel getLblCode() {
		if (lblCode == null) {
			lblCode = new JLabel("Code");
			lblCode.setBounds(6, 65, 46, 14);
			lblCode.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		return lblCode;
	}

	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
			lblNom.setBounds(6, 102, 46, 28);
			lblNom.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		return lblNom;
	}

	private JLabel getLblPrenom() {
		if (lblPrenom == null) {
			lblPrenom = new JLabel("Pr\u00E9nom");
			lblPrenom.setBounds(6, 142, 46, 28);
			lblPrenom.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		return lblPrenom;
	}

	private JLabel getLblAdresse() {
		if (lblAdresse == null) {
			lblAdresse = new JLabel("Adresse");
			lblAdresse.setBounds(6, 182, 62, 28);
			lblAdresse.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		return lblAdresse;
	}

	private JLabel getLblCodePostal() {
		if (lblCodePostal == null) {
			lblCodePostal = new JLabel("Code postal");
			lblCodePostal.setBounds(6, 262, 75, 28);
			lblCodePostal.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		return lblCodePostal;
	}

	private JLabel getLblVille() {
		if (lblVille == null) {
			lblVille = new JLabel("Ville");
			lblVille.setBounds(6, 302, 46, 28);
			lblVille.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		return lblVille;
	}

	private JButton getBtnEditerAnimal() {
		if (btnEditerAnimal == null) {
			btnEditerAnimal = new JButton("");
			btnEditerAnimal.setBounds(425, 342, 40, 40);
			btnEditerAnimal.setToolTipText("Editer un animal");
			btnEditerAnimal.setIcon(new ImageIcon("resources\\vet\\edit32.png"));
			btnEditerAnimal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							if (txtNom.getText().isEmpty() || table.getSelectedRow() == -1)
								JOptionPane.showMessageDialog(null, "Aucun animal/client selectionné");
							else {
								EcranAnimaux frame = new EcranAnimaux(txtNom.getText(),
										(int) table.getValueAt(table.getSelectedRow(), 0), EcranClient.this);
								frame.setVisible(true);
							}
						}
					});

				}
			});
		}
		return btnEditerAnimal;
	}

	private JButton getBtnSupprimerAnimal() {
		if (btnSupprimerAnimal == null) {
			btnSupprimerAnimal = new JButton("");
			btnSupprimerAnimal.setBounds(373, 342, 40, 40);
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
								JOptionPane.showMessageDialog(null, "Animal supprimé avec succès");
								mettreAJour();
							} else {
							}
						} else {
							JOptionPane.showMessageDialog(null, "Aucun animal selectionné");
						}

					} catch (Exception e1) {
						e1.printStackTrace();

					}

				}
			});
		}
		return btnSupprimerAnimal;
	}

	private JButton getBtnAjouterAnimal() {
		if (btnAjouterAnimal == null) {
			btnAjouterAnimal = new JButton("");
			btnAjouterAnimal.setBounds(321, 342, 40, 40);
			btnAjouterAnimal.setToolTipText("Ajouter un animal");
			btnAjouterAnimal.setIcon(new ImageIcon("resources\\vet\\addAnimal32.png"));
			btnAjouterAnimal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							if (txtNom.getText().isEmpty())
								JOptionPane.showMessageDialog(null, "Aucun client selectionné");
							else {
								EcranAnimaux frame = new EcranAnimaux(txtNom.getText(), EcranClient.this);
								frame.setVisible(true);
							}
						}
					});
				}

			});
		}
		return btnAjouterAnimal;
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
			if (Integer.parseInt(lblCodeClient.getText()) > 0) {
				client = clientManager.getClientByCode(Integer.parseInt(lblCodeClient.getText()));
				modeleAnimaux.setData(client.getCodeClient());
			} else
				modeleAnimaux.setData(0);

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
			scrollPane.setBounds(367, 70, 450, 270);
		}
		return scrollPane;
	}

	private JLabel getLblAnimaux() {
		if (lblAnimaux == null) {
			lblAnimaux = new JLabel("Animaux");
			lblAnimaux.setFont(new Font("Andalus", Font.BOLD, 25));
			lblAnimaux.setBounds(374, 10, 191, 28);
		}
		return lblAnimaux;
	}

	private JLabel getLblCodeClient() {
		if (lblCodeClient == null) {
			lblCodeClient = new JLabel("Nouveau Client");
			lblCodeClient.setBounds(98, 62, 95, 20);
		}
		return lblCodeClient;
	}

	private JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel("Client");
			lblClient.setBounds(25, 16, 102, 27);
			lblClient.setFont(new Font("Consolas", Font.BOLD, 25));
			lblClient.setBackground(Color.LIGHT_GRAY);
		}
		return lblClient;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(SystemColor.inactiveCaption);
			panel.setBounds(10, 6, 345, 388);
			panel.setLayout(null);
			panel.add(getLblClient());
			panel.add(getBtnEditerClient());
			panel.add(getBtnSupprimerClient());
			panel.add(getBtnAjouterClient());
			panel.add(getBtnAnnuler());
			panel.add(getTxtVille());
			panel.add(getTxtCodePostal());
			panel.add(getTxtAdresse02());
			panel.add(getTxtAdresse1());
			panel.add(getTxtPrenom());
			panel.add(getTxtNom());
			panel.add(getLblVille());
			panel.add(getLblCodePostal());
			panel.add(getLblAdresse());
			panel.add(getLblPrenom());
			panel.add(getLblNom());
			panel.add(getLblCode());
			panel.add(getLblCodeClient());
			panel.add(getBtnRechercher());
		}
		return panel;
	}
	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(new Color(230, 230, 250));
			panel_1.setBounds(363, 6, 471, 388);
			panel_1.setLayout(null);
			panel_1.add(getBtnEditerAnimal());
			panel_1.add(getBtnSupprimerAnimal());
			panel_1.add(getBtnAjouterAnimal());
		}
		return panel_1;
	}
}
