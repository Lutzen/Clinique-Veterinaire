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
import java.awt.event.ActionListener;
import java.util.Iterator;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class EcranGestionPersonnel {

	private JFrame frmGestionDuPersonnel;
	private JButton btnAjouter;
	private JButton btnSuppr;
	private JButton btnReinit;

	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	private JList list;

	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranGestionPersonnel window = new EcranGestionPersonnel();
					window.frmGestionDuPersonnel.setVisible(true);
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
//					personnelManager.deletePersonnel();
				}

			});
			btnSuppr.setBounds(109, 11, 89, 23);
		}
		return btnSuppr;
	}

	private JButton getBtnReinit() {
		if (btnReinit == null) {
			btnReinit = new JButton("Reinitialiser");
			btnReinit.setBounds(208, 11, 89, 23);
		}
		return btnReinit;
	}

	
	private JList<Personnel> getList() {
		if (list == null) {
			DefaultListModel<Personnel> model = new DefaultListModel<Personnel>();
			list = new JList<Personnel>(model);
			try {
				for (Personnel personnel : personnelManager.getPersonnelList()) {
					model.addElement(personnel);

					
				}
			} catch (BLLException e) {

				e.printStackTrace();
			}
			list.setBounds(10, 45, 414, 205);
			
		}
		return list;

	}
}
