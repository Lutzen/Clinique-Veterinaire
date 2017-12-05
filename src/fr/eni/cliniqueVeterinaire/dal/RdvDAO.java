package fr.eni.cliniqueVeterinaire.dal;


import java.util.List;


import fr.eni.cliniqueVeterinaire.bo.Rdv;

//import cliniqueVeterinaire.bo.Article;

public interface RdvDAO {


	List<Rdv> selectById(int codeVeto) throws DALException;

	void insert(Rdv rdv) throws DALException;

	void update(Rdv oldRdv,Rdv newRdv) throws DALException;

	void delete(Rdv rdv) throws DALException;

	
	
	
	
}
