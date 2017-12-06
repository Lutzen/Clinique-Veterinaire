package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bo.Personnel;

import com.jgoodies.forms.layout.FormSpecs;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JSeparator;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class EcranGestionPersonnel {

	private JFrame frmGestionDuPersonnel;
	private JButton btnAjouter;
	private JButton btnSuppr;
	private JButton btnReinit;

	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	private JTable list;

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
					EcranAjoutPersonnel.main();
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

	// private JList getList() {
	// if (list == null) {
	// DefaultListModel model = new DefaultListModel();
	// list = new JList(model);
	// try {
	// for (Personnel personnel : personnelManager.getPersonnelList()) {
	// model.addElement(personnel.getNom() + "\t"+ personnel.getRole());
	//
	//
	// }
	// } catch (BLLException e) {
	//
	// e.printStackTrace();
	// }
	// list.setBounds(10, 45, 414, 205);
	//
	// }
	// return list;
	//
	// }

	private JTable getList() {
		if (list == null) {

			try {

				list = new JTable(new TablePersonnel(personnelManager.getPersonnelList()));
				list.setBounds(10, 45, 414, 205);
			} catch (Exception e) {
			}

		}
		return list;
	}

}
