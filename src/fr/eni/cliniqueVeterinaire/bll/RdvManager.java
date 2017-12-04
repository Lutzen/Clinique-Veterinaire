package fr.eni.cliniqueVeterinaire.bll;

import java.sql.Date;
import java.util.List;

import fr.eni.cliniqueVeterinaire.bo.Rdv;
import fr.eni.cliniqueVeterinaire.dal.DAOFactory;
import fr.eni.cliniqueVeterinaire.dal.RdvDAO;

public class RdvManager {

		private static RdvManager rdvManager = null;
		public RdvDAO rdvDAO = DAOFactory.getRdvDAO();

		private RdvManager() {

		}

		public static RdvManager getInstance() {
		
				if (rdvManager == null)
					rdvManager = new RdvManager();

				return rdvManager;
		

		}

		public List<Rdv> getAgenda(int codeVeto) throws BLLException {
			try {
				return rdvDAO.selectById(codeVeto);
			} catch (Exception e) {
				throw new BLLException("getAgenda", e);
			}

		}

		public int addRdv(Rdv rdv) throws BLLException {
			try {
				return rdvDAO.insert(rdv);
			} catch (Exception e) {
				throw new BLLException("addRdv", e);
			}

		}

		public void updateRdv(int codeVeto, Date dateRdv, int codeAnimal, Date newDate) throws BLLException {
			try {
				rdvDAO.update(codeVeto, dateRdv, newDate);

			} catch (Exception e) {
				throw new BLLException("updateArticle", e);
			}

		}

		public void deleteRdv(int codeVeto,Date dateRdv,int codeAnimal) throws BLLException {
			try {
				rdvDAO.delete(codeVeto,dateRdv,codeAnimal);
			} catch (Exception e) {
				throw new BLLException("deleteRdv", e);
			}

		}

//		public void validerArticle(Article a) throws BLLException {
//			try {
//			
//				if (a instanceof Stylo) {
//					if (empty(((Stylo) a).getCouleur()))
//						throw new BLLException("Couleur manquante");
//				}
//				if (a instanceof Ramette) {
//					if (((Ramette) a).getGrammage() <= 0)
//						throw new BLLException("Grammage manquant");
//				}
//				if(empty(a.getMarque())) 
//					throw new BLLException("Marque manquante");
//				if(empty(a.getDesignation())) 
//					throw new BLLException("Designation manquante");
//				if(empty(a.getReference())) 
//					throw new BLLException("Reference manquante");
//				if(a.getPrixUnitaire() <= 0) 
//					throw new BLLException("Prix unitaire manquant");
//				if(a.getQteStock() <= 0) 
//					throw new BLLException("Quantite stock manquante");
//				
//				
//			} catch (Exception e) {
//				throw new BLLException("validerArticle", e);
//
//			}
//
//		}

	
		
		public static boolean empty( String s ) {
			  // Null-safe, short-circuit evaluation.
			  return s == null || s.trim().isEmpty();
			}

	}

