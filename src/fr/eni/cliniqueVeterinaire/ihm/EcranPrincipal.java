package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import java.awt.Cursor;
import java.awt.Frame;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

public class EcranPrincipal {

	private JFrame frmLaCliniqueDu;
	private JButton btnDeco;
	private JButton btnGestionRDV;
	private JButton btnAgenda;
	private JButton btnGestionPersonnel;
	private JLabel label;
	private JLabel lblNewLabel;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EcranPrincipal window = new EcranPrincipal();
					window.frmLaCliniqueDu.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public EcranPrincipal() {
		initialize();
	}

	private void initialize() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		frmLaCliniqueDu = new JFrame();
		frmLaCliniqueDu.setExtendedState(Frame.MAXIMIZED_BOTH);
		frmLaCliniqueDu.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		frmLaCliniqueDu.setTitle("La Clinique du Bonheur");
		frmLaCliniqueDu.setBounds(100, 100, 450, 300);
		frmLaCliniqueDu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLaCliniqueDu.getContentPane().setLayout(null);
		frmLaCliniqueDu.getContentPane().add(getBtnDeco());
		frmLaCliniqueDu.getContentPane().add(getBtnGestionRDV());
		frmLaCliniqueDu.getContentPane().add(getBtnAgenda());
		frmLaCliniqueDu.getContentPane().add(getBtnGestionPersonnel());
		btnGestionPersonnel.setEnabled(false);
		btnGestionRDV.setEnabled(false);
		btnAgenda.setEnabled(false);
		btnDeco.setEnabled(false);
		frmLaCliniqueDu.getContentPane().add(getLabel());
		frmLaCliniqueDu.getContentPane().add(getLblNewLabel());
		EcranConnexion();
	}

	private void EcranConnexion() {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				EcranConnexion frame = new EcranConnexion(EcranPrincipal.this);
				frame.setVisible(true);
			}
		});
	}

	public void RetourConnexion(int role) {
		btnDeco.setEnabled(true);
		switch (role) {
		case 0:
			btnGestionPersonnel.setEnabled(true);
			break;
		case 1:
			btnGestionRDV.setEnabled(true);
			break;
		case 2:
			btnAgenda.setEnabled(true);
			break;

		}

	}

	private JButton getBtnDeco() {
		if (btnDeco == null) {
			btnDeco = new JButton("Deconnexion");
			btnDeco.setIcon(new ImageIcon("resources\\vet\\deconnexion.png"));
			btnDeco.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					btnGestionPersonnel.setEnabled(false);
					btnGestionRDV.setEnabled(false);
					btnAgenda.setEnabled(false);
					btnDeco.setEnabled(false);
					EcranConnexion();
				}
			});
			btnDeco.setFont(new Font("SansSerif", Font.BOLD, 28));
			btnDeco.setBounds(1287, 12, 291, 99);
		}
		return btnDeco;
	}

	private JButton getBtnGestionRDV() {
		if (btnGestionRDV == null) {
			btnGestionRDV = new JButton("Gestion des RDV");
			btnGestionRDV.setIcon(new ImageIcon("resources\\vet\\calendarRdv.png"));
			btnGestionRDV.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							EcranRdv frame = new EcranRdv();
							frame.setVisible(true);
						}
					});
				}
			});
			btnGestionRDV.setFont(new Font("SansSerif", Font.BOLD, 28));
			btnGestionRDV.setBounds(10, 11, 400, 100);
		}
		return btnGestionRDV;
	}

	private JButton getBtnAgenda() {
		if (btnAgenda == null) {
			btnAgenda = new JButton("Agenda");
			btnAgenda.setIcon(new ImageIcon("resources\\vet\\calendarAgenda.png"));
			btnAgenda.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							EcranAgenda frame = new EcranAgenda();
							frame.setVisible(true);
						}
					});
				}
			});
			btnAgenda.setFont(new Font("SansSerif", Font.BOLD, 28));
			btnAgenda.setBounds(433, 11, 400, 100);
		}
		return btnAgenda;
	}

	private JButton getBtnGestionPersonnel() {
		if (btnGestionPersonnel == null) {
			btnGestionPersonnel = new JButton("Gestion du Personnel");
			btnGestionPersonnel.setIcon(new ImageIcon("resources\\vet\\employees.png"));
			btnGestionPersonnel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SwingUtilities.invokeLater(new Runnable() {

						@Override
						public void run() {
							EcranGestionPersonnel frame = new EcranGestionPersonnel();
							frame.setVisible(true);
						}
					});
				}
			});
			btnGestionPersonnel.setFont(new Font("SansSerif", Font.BOLD, 28));
			btnGestionPersonnel.setBounds(858, 11, 400, 100);
		}
		return btnGestionPersonnel;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel("");
			label.setVerticalAlignment(SwingConstants.BOTTOM);
			label.setIcon(new ImageIcon("resources\\vet\\basDePage3.png"));
			label.setBounds(10, 476, 1555, 361);
		}
		return label;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel.setIcon(new ImageIcon("resources\\vet\\CroixAnimaux.png"));
			lblNewLabel.setBounds(688, 289, 225, 187);
		}
		return lblNewLabel;
	}
}
