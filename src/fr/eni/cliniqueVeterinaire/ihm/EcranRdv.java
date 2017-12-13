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

public class EcranRdv extends JFrame {

	private JLabel lblPour;
	private JLabel lblClient;
	private JComboBox cBClients;
	private JLabel lblAnimal;
	private JComboBox cBAnimaux;
	private JButton btnAjoutClient;
	private JButton btnAjoutAnimal;
	private JLabel lblPar;
	private JLabel lblVtrinaire;
	private JComboBox cBVetos;
	private JLabel lblQuand;
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

	/**
	 * Create the application.
	 */
	public EcranRdv() {

		setTitle("Prise de rendez-vous");
		setBounds(100, 100, 596, 534);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblPour());
		getContentPane().add(getLblClient());
		getContentPane().add(getCBClients());
		getContentPane().add(getLblAnimal());
		getContentPane().add(getCBAnimaux());
		getContentPane().add(getBtnAjoutClient());
		getContentPane().add(getBtnAjoutAnimal());
		getContentPane().add(getLblPar());
		getContentPane().add(getLblVtrinaire());
		getContentPane().add(getCBVetos());
		getContentPane().add(getLblQuand());
		getContentPane().add(getLblDate());
		getContentPane().add(getLblHeure());
		getContentPane().add(getCBHeures());
		getContentPane().add(getLblH());
		getContentPane().add(getCBMinutes());
		getContentPane().add(getBtnValider());
		getContentPane().add(getBtnSupprimer());
		getContentPane().add(getDateChooser());
		getContentPane().add(getTable());
		getContentPane().add(getScrollPane());
		

	}

	private JLabel getLblPour() {
		if (lblPour == null) {
			lblPour = new JLabel("Pour :");
			lblPour.setBounds(10, 11, 46, 14);
		}
		return lblPour;
	}

	private JLabel getLblClient() {
		if (lblClient == null) {
			lblClient = new JLabel(" Client");
			lblClient.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblClient.setBounds(30, 36, 46, 14);
		}
		return lblClient;
	}

	private JComboBox getCBClients() {
		if (cBClients == null) {

			try {
				cBClients = new JComboBox();
				List<Client> clients = clientManager.getClientList();
				cBClients = new JComboBox(new String[] {});
				for (int i = 0; i < clients.size(); i++) {
					cBClients.addItem(clients.get(i).getNomClient());
					cBClients.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							refreshCBAnimaux();
						}
					});

				}
				cBClients.setBounds(30, 51, 131, 26);
			} catch (BLLException e) {

				e.printStackTrace();
			}
		}
		return cBClients;
	}

	private JLabel getLblAnimal() {
		if (lblAnimal == null) {
			lblAnimal = new JLabel(" Animal");
			lblAnimal.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblAnimal.setBounds(30, 82, 46, 14);
		}
		return lblAnimal;
	}

	private JComboBox getCBAnimaux() {
		if (cBAnimaux == null) {
			cBAnimaux = new JComboBox(new String[] {});
			refreshCBAnimaux();

			cBAnimaux.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {

				}
			});

			cBAnimaux.setBounds(30, 100, 131, 26);

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
			btnAjoutClient.setBorderPainted(false);
			btnAjoutClient.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							EcranClient frame;
							try {
								frame = new EcranClient();
								frame.setVisible(true);
							} catch (IHMException e) {
								e.printStackTrace();

							}

						}
					});
				}
			});
			// btnAjoutClient.setIcon(new
			// ImageIcon("C:\\Users\\aphommaline2017\\Desktop\\projet\\BonProjet\\Clinique-Veterinaire\\resources\\AddButton.png"));
			btnAjoutClient.setIcon(new ImageIcon("C:\\Users\\aphommaline2017\\Desktop\\projet\\BonProjet\\Clinique-Veterinaire\\resources\\vet\\addAnimal32.png"));
			btnAjoutClient.setBounds(171, 36, 40, 40);
		}
		return btnAjoutClient;
	}

	private JButton getBtnAjoutAnimal() {
		if (btnAjoutAnimal == null) {
			btnAjoutAnimal = new JButton("");
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
			btnAjoutAnimal.setIcon(new ImageIcon("C:\\Users\\aphommaline2017\\Desktop\\projet\\BonProjet\\Clinique-Veterinaire\\resources\\vet\\addAnimal32.png"));

			btnAjoutAnimal.setBounds(171, 82, 40, 40);
		}
		return btnAjoutAnimal;
	}

	private JLabel getLblPar() {
		if (lblPar == null) {
			lblPar = new JLabel("Par :");
			lblPar.setBounds(236, 11, 46, 14);
		}
		return lblPar;
	}

	private JLabel getLblVtrinaire() {
		if (lblVtrinaire == null) {
			lblVtrinaire = new JLabel(" V\u00E9t\u00E9rinaire");
			lblVtrinaire.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblVtrinaire.setBounds(256, 36, 82, 14);
		}
		return lblVtrinaire;
	}

	private JComboBox getCBVetos() {
		if (cBVetos == null) {
			try {
				List<Personnel> personnel = personnelManager.getPersonnelByRole("vet");
				cBVetos = new JComboBox(new String[] {});
				for (int i = 0; i < personnel.size(); i++) {
					cBVetos.addItem(personnel.get(i).getNom());
					cBVetos.addItemListener(new ItemListener() {
						public void itemStateChanged(ItemEvent e) {
							mettreAJour();

						}
					});

				}
				cBVetos.setBounds(256, 51, 131, 26);

			} catch (BLLException e) {

				e.printStackTrace();
			}

		}
		return cBVetos;
	}

	private JLabel getLblQuand() {
		if (lblQuand == null) {
			lblQuand = new JLabel("Quand :");
			lblQuand.setBounds(408, 11, 46, 14);
		}
		return lblQuand;
	}

	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel(" Date");
			lblDate.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblDate.setBounds(428, 36, 46, 14);
		}
		return lblDate;
	}

	private JLabel getLblHeure() {
		if (lblHeure == null) {
			lblHeure = new JLabel(" Heure");
			lblHeure.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblHeure.setBounds(428, 82, 46, 14);
		}
		return lblHeure;
	}

	private JComboBox getCBHeures() {
		if (cBHeures == null) {
			cBHeures = new JComboBox(new Integer[] {});
			for (int i = 6; i <= 20; i++) {
				cBHeures.addItem(i);
				cBHeures.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {

					}
				});
			}
			cBHeures.setBounds(428, 100, 46, 26);
		}
		return cBHeures;
	}

	private JLabel getLblH() {
		if (lblH == null) {
			lblH = new JLabel("h");
			lblH.setBounds(484, 103, 20, 14);
		}
		return lblH;
	}

	private JComboBox getCBMinutes() {
		if (cBMinutes == null) {
			cBMinutes = new JComboBox(new String[] { "00", "15", "30", "45" });
			cBMinutes.setBounds(500, 100, 46, 26);
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

			btnValider.setBounds(385, 459, 89, 26);

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
							JOptionPane.showMessageDialog(null, "Rdv supprimé avec succès");
							
							Personnel personne = personnelManager.getPersonnelByNom((String) cBVetos.getSelectedItem());
							System.out.println(personne);
							
							String heure = (String) table.getValueAt(table.getSelectedRow(), 0);
							String dateText = dateToString(dateChooser.getDate()) + " " + heure + ":00";
							Date dateRdv = stringToDate(dateText);
							System.out.println(dateRdv);
							
							
							int row = table.getSelectedRow();
							Client client = clientManager.getClientByName((String) table.getValueAt(row, 1));
							List<Animal> animaux = animalManager.getAnimalList(client.getCodeClient());
							for (Animal animalList : animaux) {
								if (animalList.getNomAnimal().equals((String) cBAnimaux.getSelectedItem()))
									;
								animal = animalList;
							}
							System.out.println(animal);
							
							Rdv rdv = new Rdv(personne.getCodePers(), dateRdv, animal.getCodeAnimal());
							System.out.println(rdv);
							rdvManager.rdvDAO.delete(rdv);
							mettreAJour();
						}
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				

				}

			});

			btnSupprimer.setBounds(482, 459, 89, 26);
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
			scrollPane.setBounds(10, 133, 561, 318);
		}
		return scrollPane;
	}

	private JDateChooser getDateChooser() {
		if (dateChooser == null) {
			dateChooser = new JDateChooser(new Date());
			dateChooser.setDate(new Date());
			dateChooser.addPropertyChangeListener(new PropertyChangeListener() {
				@Override
				public void propertyChange(PropertyChangeEvent evt) {
					Date date = dateChooser.getDate();
					System.out.println("date: " + date);
					mettreAJour();

				}
			});

			dateChooser.setBounds(428, 51, 138, 26);
		}

		return dateChooser;
	}
}
