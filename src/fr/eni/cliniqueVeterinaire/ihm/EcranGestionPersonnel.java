package fr.eni.cliniqueVeterinaire.ihm;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bo.Personnel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class EcranGestionPersonnel extends JFrame {

	static JFrame frmGestionDuPersonnel;
	private JButton btnAjouter;
	private JButton btnSuppr;
	private JButton btnReinit;
	private JScrollPane scrollPane;

	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	JTable list;
	private ModelePersonnel modelPersonnel;

	public EcranGestionPersonnel() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Gestion du Personnel");
		setBounds(500, 200, 541, 415);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnAjouter());
		getContentPane().add(getBtnSuppr());
		getContentPane().add(getBtnReinit());
		getContentPane().add(getList());
		getContentPane().add(getScrollPane());
	}

	private JButton getBtnAjouter() {
		if (btnAjouter == null) {
			btnAjouter = new JButton("Ajouter");
			btnAjouter.setIcon(new ImageIcon("resources\\vet\\addEmploye.png"));
			btnAjouter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							EcranAjoutPersonnel frame = new EcranAjoutPersonnel(EcranGestionPersonnel.this);
							frame.setVisible(true);
						}
					});

				}
			});
			btnAjouter.setBounds(10, 11, 117, 28);
		}
		return btnAjouter;
	}

	private JButton getBtnSuppr() {
		if (btnSuppr == null) {
			btnSuppr = new JButton("Supprimer");
			btnSuppr.setIcon(new ImageIcon("resources\\vet\\deleteEmploye.png"));
			btnSuppr.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					try {

						if (list.getSelectedRow() == -1)
							JOptionPane.showMessageDialog(null, "Selectionnez un personnel");
						else {
							String nom = (String) list.getValueAt(list.getSelectedRow(), 0);
							Personnel personnel = personnelManager.getPersonnelByNom(nom);
							int result = JOptionPane.showConfirmDialog(null,
									"Voulez-vous vraiment archiver " + personnel.getNom());
							if (result == 0) {
								JOptionPane.showMessageDialog(null, "Personnel archivé avec succès");
								personnelManager.deletePersonnel(personnel);
								mettreAJour();
							} else {
							}
						}
					} catch (BLLException e) {
						
						e.printStackTrace();
					}

				}
			});
			btnSuppr.setBounds(139, 11, 117, 28);

		}
		return btnSuppr;
	}

	private JButton getBtnReinit() {
		if (btnReinit == null) {
			btnReinit = new JButton(" Reinitialiser");
			btnReinit.setIcon(new ImageIcon("resources\\vet\\key16.png"));
			btnReinit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (list.getSelectedRow() == -1)
							JOptionPane.showMessageDialog(null, "Selectionnez un personnel");
						else {
							String nom = (String) list.getValueAt(list.getSelectedRow(), 0);
							Personnel personne = personnelManager.getPersonnelByNom(nom);
							SwingUtilities.invokeLater(new Runnable() {

								@Override
								public void run() {
									EcranInitPass frame = new EcranInitPass(personne);
									frame.setVisible(true);
								}
							});
						}

					} catch (BLLException e1) {
						
						e1.printStackTrace();
					}
				}
			});
			btnReinit.setBounds(268, 11, 117, 28);
		}
		return btnReinit;
	}

	public void mettreAJour() {
		getModelPersonnel().setData();
	}

	public JTable getList() {


		try {

			list = new JTable(getModelPersonnel());

			list.setBounds(10, 45, 414, 205);
		} catch (Exception e) {
		}

		
		return list;
	}

	private ModelePersonnel getModelPersonnel() {
		if (modelPersonnel == null) {
			try {
				modelPersonnel = new ModelePersonnel();
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		return modelPersonnel;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(list);
			scrollPane.setBounds(10, 45, 509, 325);
		}
		return scrollPane;
	}

}
