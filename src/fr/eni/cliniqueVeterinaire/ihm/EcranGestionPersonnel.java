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
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setTitle("Gestion du Personnel");
		setBounds(100, 100, 450, 300);
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
			btnAjouter.setBounds(10, 11, 89, 23);
		}
		return btnAjouter;
	}

	private JButton getBtnSuppr() {
		if (btnSuppr == null) {
			btnSuppr = new JButton("Supprimer");
			btnSuppr.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent arg0) {
					try {
						if (list.getSelectedRow() == -1)
							JOptionPane.showMessageDialog(null, "Selectionnez un personnel");
						else {
							JOptionPane.showMessageDialog(null, "Personnel supprimé avec succès");
							String nom = (String) list.getValueAt(list.getSelectedRow(), 0);
							Personnel personnel = personnelManager.getPersonnelByNom(nom);
							personnelManager.deletePersonnel(personnel);
							mettreAJour();
						}
					} catch (BLLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			});
			btnSuppr.setBounds(109, 11, 89, 23);

		}
		return btnSuppr;
	}

	private JButton getBtnReinit() {
		if (btnReinit == null) {
			btnReinit = new JButton("Reinitialiser");
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
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			});
			btnReinit.setBounds(208, 11, 89, 23);
		}
		return btnReinit;
	}

	public void mettreAJour() {
		getModelPersonnel().setData();
	}

	public JTable getList() {
		// if (list == null) {

		try {

			list = new JTable(getModelPersonnel());

			list.setBounds(10, 45, 414, 205);
		} catch (Exception e) {
		}

		// }
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
			scrollPane.setBounds(10, 45, 414, 205);
		}
		return scrollPane;
	}

}
