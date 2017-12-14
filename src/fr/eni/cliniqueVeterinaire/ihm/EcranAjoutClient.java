package fr.eni.cliniqueVeterinaire.ihm;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bo.Client;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Font;

public class EcranAjoutClient extends JFrame {

	private JFrame frmAjoutDunClient;
	private JButton btnAnnuler;
	private JButton btnValider;
	private JTextField txtNom;
	private JTextField txtPrenom;
	private JTextField txtAdresse;
	private JTextField txtAdresse2;
	private JTextField txtCodePostal;
	private JTextField txtVille;
	private JLabel lblNom;
	private JLabel lblPrnom;
	private JLabel lblAdresse;
	private JLabel lblCodePostal;
	private JLabel lblVille;
	private EcranClient ecranClient;
	private ClientManager clientManager = ClientManager.getInstance();

	public EcranAjoutClient(EcranClient ecran) {
		setResizable(false);
		ecranClient = ecran;
		setTitle("Ajout d'un client");
		setBounds(700, 300, 347, 343);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getBtnAnnuler());
		getContentPane().add(getBtnValider());
		getContentPane().add(getTxtNom());
		getContentPane().add(getTxtPrenom());
		getContentPane().add(getTxtAdresse());
		getContentPane().add(getTxtAdresse2());
		getContentPane().add(getTxtCodePostal());
		getContentPane().add(getTxtVille());
		getContentPane().add(getLblNom());
		getContentPane().add(getLblPrnom());
		getContentPane().add(getLblAdresse());
		getContentPane().add(getLblCodePostal());
		getContentPane().add(getLblVille());
	}

	private JButton getBtnAnnuler() {
		if (btnAnnuler == null) {
			btnAnnuler = new JButton("Annuler");
			btnAnnuler.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					txtNom.setText("");
					txtPrenom.setText("");
					txtAdresse.setText("");
					txtAdresse2.setText("");
					txtVille.setText("");
					txtCodePostal.setText("");
				}
			});
			btnAnnuler.setBounds(231, 265, 89, 29);
		}
		return btnAnnuler;
	}

	private JButton getBtnValider() {
		if (btnValider == null) {
			btnValider = new JButton("Valider");
			btnValider.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (txtNom.getText().isEmpty() & txtVille.getText().isEmpty()) {
							JOptionPane.showMessageDialog(null, "Remplissez tout les champs!");
						} else {
							Client client = new Client();
							client.setAdresse1(txtAdresse.getText());
							client.setAdresse2(txtAdresse2.getText());
							client.setCodePostal(txtCodePostal.getText());
							client.setNomClient(txtNom.getText());
							client.setPrenomClient(getTxtPrenom().getText());
							client.setVille(txtVille.getText());
							clientManager.addClient(client);
							JOptionPane.showMessageDialog(null, "Client ajouté avec succès");
							client = clientManager.getClientByName(client.getNomClient());
							ecranClient.recupClient(client);
							dispose();
						}

					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			});
			btnValider.setBounds(130, 265, 89, 29);
		}
		return btnValider;
	}

	private JTextField getTxtNom() {
		if (txtNom == null) {
			txtNom = new JTextField();
			
			txtNom.addKeyListener(new KeyAdapter() {
			    public void keyTyped(java.awt.event.KeyEvent e) { 
			        if (txtNom.getText().length() >= 20 )
			            e.consume(); 
			    }  
			});
			
			txtNom.setColumns(10);
			txtNom.setBounds(85, 13, 235, 28);
		}
		return txtNom;
	}

	private JTextField getTxtPrenom() {
		if (txtPrenom == null) {
			txtPrenom = new JTextField();
			
			txtPrenom.addKeyListener(new KeyAdapter() {
			    public void keyTyped(java.awt.event.KeyEvent e) { 
			        if (txtPrenom.getText().length() >= 20 )
			            e.consume(); 
			    }  
			});
			
			txtPrenom.setColumns(10);
			txtPrenom.setBounds(85, 54, 235, 28);
		}
		return txtPrenom;
	}

	private JTextField getTxtAdresse() {
		if (txtAdresse == null) {
			txtAdresse = new JTextField();
			
			txtAdresse.addKeyListener(new KeyAdapter() {
			    public void keyTyped(java.awt.event.KeyEvent e) { 
			        if (txtAdresse.getText().length() >= 30 )
			            e.consume(); 
			    }  
			});
			
			txtAdresse.setColumns(10);
			txtAdresse.setBounds(85, 95, 235, 28);
		}
		return txtAdresse;
	}

	private JTextField getTxtAdresse2() {
		if (txtAdresse2 == null) {
			txtAdresse2 = new JTextField();
			
			txtAdresse2.addKeyListener(new KeyAdapter() {
			    public void keyTyped(java.awt.event.KeyEvent e) { 
			        if (txtAdresse2.getText().length() >= 30 )
			            e.consume(); 
			    }  
			});
			
			txtAdresse2.setColumns(10);
			txtAdresse2.setBounds(85, 135, 235, 28);
		}
		return txtAdresse2;
	}

	private JTextField getTxtCodePostal() {
		if (txtCodePostal == null) {
			txtCodePostal = new JTextField();
			
			txtCodePostal.addKeyListener(new KeyAdapter() {
			    public void keyTyped(java.awt.event.KeyEvent e) { 
			        if (txtCodePostal.getText().length() >= 6 )
			            e.consume(); 
			    }  
			});
			
			
			txtCodePostal.setColumns(10);
			txtCodePostal.setBounds(85, 176, 235, 28);
		}
		return txtCodePostal;
	}

	private JTextField getTxtVille() {
		if (txtVille == null) {
			txtVille = new JTextField();
			
			txtVille.addKeyListener(new KeyAdapter() {
			    public void keyTyped(java.awt.event.KeyEvent e) { 
			        if (txtVille.getText().length() >= 25 )
			            e.consume(); 
			    }  
			});
			
			txtVille.setColumns(10);
			txtVille.setBounds(85, 217, 235, 28);
		}
		return txtVille;
	}

	private JLabel getLblNom() {
		if (lblNom == null) {
			lblNom = new JLabel("Nom");
			lblNom.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblNom.setBounds(10, 20, 46, 14);
		}
		return lblNom;
	}

	private JLabel getLblPrnom() {
		if (lblPrnom == null) {
			lblPrnom = new JLabel("Pr\u00E9nom");
			lblPrnom.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblPrnom.setBounds(10, 61, 46, 14);
		}
		return lblPrnom;
	}

	private JLabel getLblAdresse() {
		if (lblAdresse == null) {
			lblAdresse = new JLabel("Adresse");
			lblAdresse.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblAdresse.setBounds(10, 102, 63, 14);
		}
		return lblAdresse;
	}

	private JLabel getLblCodePostal() {
		if (lblCodePostal == null) {
			lblCodePostal = new JLabel("Code postal");
			lblCodePostal.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblCodePostal.setBounds(10, 183, 74, 14);
		}
		return lblCodePostal;
	}

	private JLabel getLblVille() {
		if (lblVille == null) {
			lblVille = new JLabel("Ville");
			lblVille.setFont(new Font("SansSerif", Font.BOLD, 12));
			lblVille.setBounds(10, 224, 46, 14);
		}
		return lblVille;
	}

}
