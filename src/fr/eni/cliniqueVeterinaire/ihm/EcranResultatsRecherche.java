package fr.eni.cliniqueVeterinaire.ihm;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


import fr.eni.cliniqueVeterinaire.bll.BLLException;
import fr.eni.cliniqueVeterinaire.bll.ClientManager;
import fr.eni.cliniqueVeterinaire.bo.Client;

import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class EcranResultatsRecherche extends JFrame {

	private JTextField txtRechercher;
	private JButton btnRechercher;
	private JTable table;
	private ClientManager clientManager = ClientManager.getInstance();
	private JScrollPane scrollPane;
	private Client client;
	private ModeleClient modeleClient;
	private List<Client> liste;
	private EcranClient ecranClient;

	public EcranResultatsRecherche(EcranClient ecranClient) throws IHMException {
		this.ecranClient = ecranClient;
		setTitle("Resultats de la recherche");
		setBounds(700, 300, 475, 380);
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
			txtRechercher.setBounds(10, 11, 252, 28);
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
			btnRechercher.setBounds(274, 11, 100, 28);
		}
		return btnRechercher;
	}
	
	


	private JTable getTable() throws IHMException {
		if (table == null) {
			try {
				table = new JTable(getModeleClient());
				liste = clientManager.getClientList();
				table.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent event) {
						int row = table.convertRowIndexToModel(table.rowAtPoint(event.getPoint()));
						client = liste.get(row);
						try {
							ecranClient.recupClient(client);
						} catch (IHMException e) {
							e.printStackTrace();
						}
						dispose();
							
					}
				});
			
				
				
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new IHMException("Erreur à l'appel getTable ", e);

			}
			table.setBounds(10, 45, 414, 205);
		}
		return table;
	}

	public void mettreAJour() throws IHMException {

	liste = modeleClient.setData(txtRechercher.getText());
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
			scrollPane.addContainerListener(new ContainerAdapter() {
			
			});
			scrollPane.setBounds(10, 52, 437, 276);
		}
		return scrollPane;
	}
	
	

}
