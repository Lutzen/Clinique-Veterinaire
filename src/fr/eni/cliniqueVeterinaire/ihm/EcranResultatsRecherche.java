package fr.eni.cliniqueVeterinaire.ihm;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import com.sun.corba.se.impl.ior.iiop.JavaSerializationComponent;

import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bo.Client;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EcranResultatsRecherche extends JFrame {

	private JFrame frmResultatsDeLa;
	private JTextField txtRechercher;
	private JButton btnRechercher;
	private JTable table;
	private ClientManager clientManager = ClientManager.getInstance();
	private JScrollPane scrollPane;
	private Client client;
	private ModeleClient modeleClient;

	public EcranResultatsRecherche() throws IHMException {
		setTitle("Resultats de la recherche");
		setBounds(100, 100, 450, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		getContentPane().add(getTxtRechercher());
		getContentPane().add(getBtnRechercher());
		getContentPane().add(getTable());
		getContentPane().add(getScrollPane());
	}

	private JTextField getTxtRechercher() {
		if (txtRechercher == null) {
			txtRechercher = new JTextField();
			txtRechercher.setBounds(10, 11, 252, 20);
			txtRechercher.setColumns(10);
		}
		return txtRechercher;
	}

	private JButton getBtnRechercher()  {
		if (btnRechercher == null) {
			btnRechercher = new JButton("Rechercher");
			btnRechercher.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						mettreAJour();
					} catch (IHMException e1) {
						e1.printStackTrace();

					}
				}
			});
			btnRechercher.setBounds(272, 10, 89, 23);
		}
		return btnRechercher;
	}

	private JTable getTable() throws IHMException {
		if (table == null) {
			try {
				table = new JTable(getModeleClient());
			} catch (IHMException e) {
				e.printStackTrace();
				throw new IHMException("Erreur à l'appel getTable ", e);

			}
			table.setBounds(10, 45, 414, 205);
		}
		return table;
	}

	public void mettreAJour() throws IHMException {

		modeleClient.setData(btnRechercher.getText());
	}

	private ModeleClient getModeleClient() throws IHMException {
		if (modeleClient == null) {
			try {

				modeleClient = new ModeleClient();
			} catch (BLLException e) {

				e.printStackTrace();
				throw new IHMException("Erreur à l'appel getModeleClient de la table", e);

			}

		}
		return modeleClient;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane(table);
			scrollPane.setBounds(10, 45, 414, 205);
		}
		return scrollPane;
	}
}
