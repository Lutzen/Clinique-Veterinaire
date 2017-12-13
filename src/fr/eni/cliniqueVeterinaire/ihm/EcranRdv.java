package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;

import fr.eni.cliniqueVeterinaire.bll.AnimalManager;
import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bll.RdvManager;
import fr.eni.cliniqueVeterinaire.bo.Animal;
import fr.eni.cliniqueVeterinaire.bo.Client;
import fr.eni.cliniqueVeterinaire.bo.Personnel;
import fr.eni.cliniqueVeterinaire.bo.Rdv;

import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import java.awt.SystemColor;

public class EcranRdv extends JFrame {
	private JLabel lblClient;
	private JLabel lblAnimal;
	private JComboBox cBAnimaux;
	private JButton btnAjoutClient;
	private JButton btnAjoutAnimal;
	private JLabel lblVtrinaire;
	private JComboBox cBVetos;
	private JLabel lblDate;
	private JLabel lblHeure;
	private JComboBox cBHeures;
	private JLabel lblH;
	private JComboBox cBMinutes;
	private JButton btnValider;
	private JButton btnSupprimer;
	private JTable table;
	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	private AnimalManager animalManager = AnimalManager.getInstance();
	private ClientManager clientManager = ClientManager.getInstance();
	private RdvManager rdvManager = RdvManager.getInstance();
	private ModeleRdv modeleRdv;
	private Personnel personnel;
	private Animal animal;
	private JScrollPane scrollPane;
	private JDateChooser dateChooser;
	private JPanel panel;
	private JPanel panel_1;
	private JPanel panel_2;
	private JPanel panel_3;
	private JComboBox cBClients;

	/**
	 * Create the application.
	 */
	public EcranRdv() {

		setTitle("Prise de rendez-vous");
		setBounds(450, 200, 647, 590);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getCBClients());
		getContentPane().add(getPanel());
		getContentPane().add(getPanel_1());
		getContentPane().add(getPanel_2());
		getContentPane().add(getPanel_3());
		getContentPane().add(getBtnValider());
		getContentPane().add(getBtnSupprimer());
		getContentPane().add(getTable());
		getContentPane().add(getScrollPane());

	}

	private JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel(" Client");
			lblClient.setBounds(6, 6, 35, 16);
			lblClient.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		return lblClient;
	}

	private JLabel getLblAnimal() {
		if (lblAnimal == null) {
			lblAnimal = new JLabel(" Animal");
			lblAnimal.setBounds(6, 6, 42, 16);
			lblAnimal.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		return lblAnimal;
	}

	private JComboBox getCBAnimaux() {
		if (cBAnimaux == null) {
			cBAnimaux = new JComboBox(new String[] {});
			cBAnimaux.setBounds(6, 34, 150, 26);
			refreshCBAnimaux();

			cBAnimaux.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {

				}
			});

		}
		return cBAnimaux;
	}

	public void refreshCBAnimaux() {
		Client client;
		try {
			cBAnimaux.removeAllItems();
			client = clientManager.getClientByName((String) cBClients.getSelectedItem());
			List<Animal> animaux = animalManager.getAnimalList(client.getCodeClient());
			for (int i = 0; i < animaux.size(); i++) {
				cBAnimaux.addItem(animaux.get(i).getNomAnimal());
			}
		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private JButton getBtnAjoutClient() {
		if (btnAjoutClient == null) {
			btnAjoutClient = new JButton("");
			btnAjoutClient.setToolTipText("Ajouter un client");
			btnAjoutClient.setBounds(168, 19, 40, 40);
			btnAjoutClient.setBorderPainted(false);
			btnAjoutClient.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							EcranClient frame;
							try {
								frame = new EcranClient(EcranRdv.this);
								frame.setVisible(true);
							} catch (IHMException e) {
								e.printStackTrace();

							}

						}
					});
				}
			});

			btnAjoutClient.setIcon(new ImageIcon("resources\\vet\\addAnimal32.png"));
		}
		return btnAjoutClient;
	}

	private JButton getBtnAjoutAnimal() {
		if (btnAjoutAnimal == null) {
			btnAjoutAnimal = new JButton("");
			btnAjoutAnimal.setToolTipText("Ajouter un animal");
			btnAjoutAnimal.setBounds(168, 20, 40, 40);
			btnAjoutAnimal.setBorderPainted(false);
			btnAjoutAnimal.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							EcranAnimaux frame = new EcranAnimaux(cBClients.getSelectedItem().toString(),
									EcranRdv.this);
							frame.setVisible(true);
						}
					});
				}
			});
			btnAjoutAnimal.setIcon(new ImageIcon("resources\\vet\\addAnimal32.png"));
		}
		return btnAjoutAnimal;
	}

	private JLabel getLblVtrinaire() {
		if (lblVtrinaire == null) {
			lblVtrinaire = new JLabel(" V\u00E9t\u00E9rinaire");
			lblVtrinaire.setBounds(6, 6, 82, 14);
			lblVtrinaire.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		return lblVtrinaire;
	}

	private JComboBox getCBVetos() {
		if (cBVetos == null) {
			try {
				List<Personnel> personnel = personnelManager.getPersonnelByRole("vet");
				cBVetos = new JComboBox(new String[] {});
				cBVetos.setBounds(17, 53, 151, 26);
				for (int i = 0; i < personnel.size(); i++) {
					cBVetos.addItem(personnel.get(i).getNom());
					cBVetos.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							mettreAJour();

						}
					});

				}

			} catch (BLLException e) {

				e.printStackTrace();
			}

		}
		return cBVetos;
	}

	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel(" Date");
			lblDate.setBounds(6, 6, 41, 16);
			lblDate.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		return lblDate;
	}

	private JLabel getLblHeure() {
		if (lblHeure == null) {
			lblHeure = new JLabel(" Heure");
			lblHeure.setBounds(6, 72, 46, 14);
			lblHeure.setFont(new Font("SansSerif", Font.BOLD, 12));
		}
		return lblHeure;
	}

	private JComboBox getCBHeures() {
		if (cBHeures == null) {
			cBHeures = new JComboBox(new Integer[] {});
			cBHeures.setBounds(6, 98, 56, 26);
			for (int i = 6; i <= 20; i++) {
				cBHeures.addItem(i);
				cBHeures.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {

					}
				});
			}
		}
		return cBHeures;
	}

	private JLabel getLblH() {
		if (lblH == null) {
			lblH = new JLabel("h");
			lblH.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblH.setBounds(66, 104, 20, 14);
		}
		return lblH;
	}

	private JComboBox getCBMinutes() {
		if (cBMinutes == null) {
			cBMinutes = new JComboBox(new String[] { "00", "15", "30", "45" });
			cBMinutes.setBounds(74, 98, 56, 26);
		}
		return cBMinutes;
	}

	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");

			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					String heure = String.valueOf(cBHeures.getSelectedItem() + ":" + cBMinutes.getSelectedItem());
					for (int i = 0; i < table.getRowCount(); i++) {
						if (heure.equals(table.getValueAt(i, 0))) {
							JOptionPane.showMessageDialog(null, "Selectionnez un autre créneau horaire");
							return;
						}
					}
					Personnel personne;
					try {

						personne = personnelManager.getPersonnelByNom((String) cBVetos.getSelectedItem());

						Client client = clientManager.getClientByName((String) cBClients.getSelectedItem());
						List<Animal> animaux = animalManager.getAnimalList(client.getCodeClient());
						for (Animal animalList : animaux) {
							if (animalList.getNomAnimal().equals((String) cBAnimaux.getSelectedItem()))
								;
							animal = animalList;
						}
						String dateText = dateToString(dateChooser.getDate()) + " " + heure + ":00";
						Date dateRdv = stringToDate(dateText);
						System.out.println(dateRdv);
						Rdv rdv = new Rdv(personne.getCodePers(), dateRdv, animal.getCodeAnimal());
						rdvManager.rdvDAO.insert(rdv);
						mettreAJour();

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			});

			btnValider.setBounds(437, 517, 89, 26);

		}

		return btnValider;
	}

	private JButton getBtnSupprimer() {
		if (btnSupprimer == null) {
			btnSupprimer = new JButton("Supprimer");

			btnSupprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (table.getSelectedRow() == -1)
							JOptionPane.showMessageDialog(null, "Selectionnez un Rdv");
						else {
							int result = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment supprimer ?");
							if (result == 0) {
								JOptionPane.showMessageDialog(null, "Rdv supprimé avec succès");
								Personnel personne = personnelManager
										.getPersonnelByNom((String) cBVetos.getSelectedItem());
								String heure = (String) table.getValueAt(table.getSelectedRow(), 0);
								String dateText = dateToString(dateChooser.getDate()) + " " + heure + ":00";
								Date dateRdv = stringToDate(dateText);
								int row = table.getSelectedRow();
								Client client = clientManager.getClientByName((String) table.getValueAt(row, 1));
								List<Animal> animaux = animalManager.getAnimalList(client.getCodeClient());
								for (Animal animalList : animaux) {
									if (animalList.getNomAnimal().equals((String) cBAnimaux.getSelectedItem()))
										;
									animal = animalList;
								}
								Rdv rdv = new Rdv(personne.getCodePers(), dateRdv, animal.getCodeAnimal());
								rdvManager.rdvDAO.delete(rdv);
								mettreAJour();
							} else {
							}

						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}

				}

			});

			btnSupprimer.setBounds(536, 517, 89, 26);
		}
		return btnSupprimer;
	}

	private JTable getTable() {
		if (table == null) {

			try {
				table = new JTable(getModeleRdv());
				cBVetos.setSelectedIndex(0);
				table.setBounds(10, 133, 561, 318);
			} catch (Exception e) {
			}

		}
		return table;
	}

	public void mettreAJour() {

		try {
			personnel = personnelManager.getPersonnelByNom((String) cBVetos.getSelectedItem());
			modeleRdv.setData(personnel.getCodePers(), dateToString(dateChooser.getDate()));

		} catch (BLLException e) {

			e.printStackTrace();
		}
	}

	private ModeleRdv getModeleRdv() {
		if (modeleRdv == null) {
			try {
				personnel = personnelManager.getPersonnelByNom((String) cBVetos.getSelectedItem());

				System.out.println(personnel);
				modeleRdv = new ModeleRdv(personnel.getCodePers());
			} catch (BLLException e) {
				e.printStackTrace();
			}

		}
		return modeleRdv;
	}

	private String dateToString(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String startDateString = dateFormat.format(date);
		return startDateString;
	}

	private Date stringToDate(String dateText) throws ParseException {
		DateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date newDate = dateformat.parse(dateText);
		return newDate;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 159, 615, 346);
		}
		return scrollPane;
	}

	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser(new Date());
			dateChooser.setBounds(6, 34, 138, 26);
			dateChooser.setDate(new Date());
			dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					Date date = dateChooser.getDate();
					System.out.println("date: " + date);
					mettreAJour();

				}
			});
		}

		return dateChooser;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(SystemColor.inactiveCaption);
			panel.setForeground(SystemColor.activeCaption);
			panel.setBounds(10, 11, 214, 65);
			panel.setLayout(null);
			panel.add(getLblClient());
			panel.add(getBtnAjoutClient());
			panel.add(getCBClients());
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(SystemColor.inactiveCaption);
			panel_1.setBounds(10, 82, 214, 64);
			panel_1.setLayout(null);
			panel_1.add(getLblAnimal());
			panel_1.add(getCBAnimaux());
			panel_1.add(getBtnAjoutAnimal());
		}
		return panel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(SystemColor.inactiveCaption);
			panel_2.setBounds(229, 11, 187, 135);
			panel_2.setLayout(null);
			panel_2.add(getCBVetos());
			panel_2.add(getLblVtrinaire());
		}
		return panel_2;
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(SystemColor.inactiveCaption);
			panel_3.setBounds(421, 12, 204, 135);
			panel_3.setLayout(null);
			panel_3.add(getLblDate());
			panel_3.add(getDateChooser());
			panel_3.add(getLblHeure());
			panel_3.add(getCBHeures());
			panel_3.add(getLblH());
			panel_3.add(getCBMinutes());
		}
		return panel_3;
	}

	private JComboBox getCBClients() {
		if (cBClients == null) {
			cBClients = new JComboBox(new String[] {});
			RefreshCBClients();
			cBClients.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					if (cBClients.getSelectedItem() != null)
						refreshCBAnimaux();
				}
			});
		}
		cBClients.setBounds(6, 33, 150, 26);
		return cBClients;
	}

	public void RefreshCBClients() {
		List<Client> clients;
		try {

			cBClients.removeAllItems();
			clients = clientManager.getClientList();
			for (int i = 0; i < clients.size(); i++)
				cBClients.addItem(clients.get(i).getNomClient());

		} catch (BLLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
