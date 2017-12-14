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
		
		public List<Rdv> getAgendaDate(int codeVeto,String date) throws BLLException {
			try {
				return rdvDAO.selectByIdDate(codeVeto, date);
			} catch (Exception e) {
				throw new BLLException("getAgendaDate", e);
			}

		}

		public void addRdv(Rdv rdv) throws BLLException {
			try {
				rdvDAO.insert(rdv);
			} catch (Exception e) {
				throw new BLLException("addRdv", e);
			}

		}

		public void updateRdv(Rdv rdv, Rdv newRdv) throws BLLException {
			try {
				rdvDAO.update(rdv, newRdv);

			} catch (Exception e) {
				throw new BLLException("updateArticle", e);
			}

		}

		public void deleteRdv(Rdv rdv) throws BLLException {
			try {
				rdvDAO.delete(rdv);
			} catch (Exception e) {
				throw new BLLException("deleteRdv", e);
			}

		}



	}

