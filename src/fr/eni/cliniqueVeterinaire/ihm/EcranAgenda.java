package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.border.BevelBorder;

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.PersonnelManager;
import fr.eni.cliniqueVeterinaire.bll.RdvManager;
import fr.eni.cliniqueVeterinaire.bo.Personnel;
import fr.eni.cliniqueVeterinaire.bo.Rdv;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingUtilities;
import com.toedter.calendar.JDateChooser;

public class EcranAgenda extends JFrame {

	private JLabel lblVeto;
	private JLabel lblDate;
	private JButton btnDossierMedical;
	private JComboBox comboBoxVeto;
	private PersonnelManager personnelManager = PersonnelManager.getInstance();
	private RdvManager rdvManager = RdvManager.getInstance();
	private JTable list;
	//private JList list;
	private ModeleRdv modeleRdv;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public EcranAgenda() {

		setTitle("Agenda");
		setBounds(100, 100, 454, 390);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getLblVeto());
		getContentPane().add(getLblDate());
		getContentPane().add(getBtnDossierMedical());
		getContentPane().add(getComboBoxVeto());
		
//		JScrollPane scrollPane = new JScrollPane();
//		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//		scrollPane.setBounds(10, 39, 418, 271);
//		getContentPane().add(scrollPane);
//		scrollPane.setRowHeaderView(getList());
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(250, 8, 124, 20);
		getContentPane().add(dateChooser);
		
	}

	
	private JComboBox getComboBoxVeto() {
		if (comboBoxVeto == null) {
			try {
				List<Personnel> personnel =personnelManager.getPersonnelByRole("vet");
				comboBoxVeto= new JComboBox(new String[]{});
				for (int i = 0; i < personnel.size(); i++) {
					comboBoxVeto.addItem(personnel.get(i).getNom());

				}
				comboBoxVeto.setBounds(79, 8, 97, 20);
				
			} catch (BLLException e) {
				
				e.printStackTrace();
			}
			
		}
		return comboBoxVeto;
	}
	
//	private JList getList() {
//		if (list == null) {
//			List<Rdv> rdv = rdvManager.getAgenda(2);
//			list = new JList(new String[]{});
//			for (int i = 0; i < rdv.size(); i++) {
//				list.addItem(rdv.get(i).getDateRdv());
//		}
//		return list;
//	}

	private JLabel getLblVeto() {
		if (lblVeto == null) {
			lblVeto = new JLabel("Vétérinaire");
			lblVeto.setBounds(10, 11, 59, 14);
		}
		return lblVeto;
	}
	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("Date");
			lblDate.setBounds(208, 11, 32, 14);
		}
		return lblDate;
	}
	private JButton getBtnDossierMedical() {
		if (btnDossierMedical == null) {
			btnDossierMedical = new JButton("Dossier Medical");
			btnDossierMedical.addActionListener(new ActionListener(){	@Override
			public void actionPerformed(ActionEvent e) {
				SwingUtilities.invokeLater(new Runnable() {
					
					@Override
					public void run() {
						EcranDossierMedical frame = new EcranDossierMedical();
						frame.setVisible(true);
					}
				});
			}
			});
			
			
			btnDossierMedical.setIcon(new ImageIcon(EcranAgenda.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
			btnDossierMedical.setBounds(277, 317, 151, 23);
		}
		return btnDossierMedical;
	}
	
	
	public void mettreAJour() {
		getModelRdv().setData();
	}
	
	
	public JTable getList() {
		// if (list == null) {

		try {

			list = new JTable(getModelRdv());

			list.setBounds(10, 39, 418, 271);
		} catch (Exception e) {
		}

		// }
		return list;
	}

	private ModeleRdv getModelRdv()  {
		if (modeleRdv == null) {
			try {
				modeleRdv = new ModeleRdv();
			} catch (BLLException e) {
				e.printStackTrace();
			}
		}
		return modeleRdv;
	}

}
