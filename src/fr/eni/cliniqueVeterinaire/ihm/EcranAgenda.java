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
import java.awt.event.ActionEvent;

public class EcranAgenda {

	private JFrame frmAgenda;
	private JLabel lblNewLabel;
	private JList list;
	private JLabel lblDate;
	private JFormattedTextField formattedTextField;
	private JTextArea textArea;
	private JButton btnDossierMedical;

	/**
	 * Launch the application.
	 */
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranAgenda window = new EcranAgenda();
					window.frmAgenda.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public EcranAgenda() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmAgenda = new JFrame();
		frmAgenda.setTitle("Agenda");
		frmAgenda.setBounds(100, 100, 454, 390);
		frmAgenda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmAgenda.getContentPane().setLayout(null);
		frmAgenda.getContentPane().add(getLblNewLabel());
		frmAgenda.getContentPane().add(getList());
		frmAgenda.getContentPane().add(getLblDate());
		frmAgenda.getContentPane().add(getFormattedTextField());
		frmAgenda.getContentPane().add(getTextArea());
		frmAgenda.getContentPane().add(getBtnDossierMedical());
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("V\u00E9t\u00E9rinaire");
			lblNewLabel.setBounds(10, 11, 59, 14);
		}
		return lblNewLabel;
	}
	private JList getList() {
		if (list == null) {
			list = new JList();
			list.setBounds(79, 10, 107, 14);
		}
		return list;
	}
	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("Date");
			lblDate.setBounds(196, 11, 32, 14);
		}
		return lblDate;
	}
	private JFormattedTextField getFormattedTextField() {
		if (formattedTextField == null) {
			formattedTextField = new JFormattedTextField();
			formattedTextField.setToolTipText("JJ/MM/AAAA");
			formattedTextField.setText("  /  /    ");
			formattedTextField.setBounds(238, 8, 46, 20);
		}
		return formattedTextField;
	}
	private JTextArea getTextArea() {
		if (textArea == null) {
			textArea = new JTextArea();
			textArea.setBounds(10, 38, 418, 272);
		}
		return textArea;
	}
	private JButton getBtnDossierMedical() {
		if (btnDossierMedical == null) {
			btnDossierMedical = new JButton("Dossier Medical");
			btnDossierMedical.addActionListener(new ActionListener(){	@Override
			public void actionPerformed(ActionEvent e) {
				EcranDossierMedical.main();
			}
			});
			
			
			btnDossierMedical.setIcon(new ImageIcon(EcranAgenda.class.getResource("/javax/swing/plaf/metal/icons/ocean/directory.gif")));
			btnDossierMedical.setBounds(277, 317, 151, 23);
		}
		return btnDossierMedical;
	}



}
