package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;
import javax.swing.JFrame;
import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bo.Personnel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EcranGestionPersonnel extends JFrame {

	static JFrame frmGestionDuPersonnel;
	private JButton btnAjouter;
	private JButton btnSuppr;
	private JButton btnReinit;

	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	JTable list;
	private ModelePersonnel modelPersonnel;

	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranGestionPersonnel window = new EcranGestionPersonnel();
					window.frmGestionDuPersonnel.setVisible(true);
					window.frmGestionDuPersonnel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EcranGestionPersonnel() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	private void initialize() {
		frmGestionDuPersonnel = new JFrame();
		frmGestionDuPersonnel.setTitle("Gestion du Personnel");
		frmGestionDuPersonnel.setBounds(100, 100, 450, 300);
		frmGestionDuPersonnel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmGestionDuPersonnel.getContentPane().setLayout(null);
		frmGestionDuPersonnel.getContentPane().add(getBtnAjouter());
		frmGestionDuPersonnel.getContentPane().add(getBtnSuppr());
		frmGestionDuPersonnel.getContentPane().add(getBtnReinit());
		frmGestionDuPersonnel.getContentPane().add(getList());
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
						String nom = (String) list.getValueAt(list.getSelectedRow(), 0);

						Personnel personnel = personnelManager.getPersonnelByNom(nom);
						personnelManager.deletePersonnel(personnel);

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
						String nom = (String) list.getValueAt(list.getSelectedRow(), 0);
						Personnel personnel = personnelManager.getPersonnelByNom(nom);
						EcranInitPass.main(personnel);

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

	public void mettreAJour() throws BLLException {
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

	private ModelePersonnel getModelPersonnel() throws BLLException {
		if (modelPersonnel == null) {
			modelPersonnel = new ModelePersonnel();
		}
		return modelPersonnel;
	}

}
